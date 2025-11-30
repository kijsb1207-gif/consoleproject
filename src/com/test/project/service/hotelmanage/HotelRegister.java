package com.test.project.service.hotelmanage;

import com.test.project.data.Hotels;
import com.test.project.data.Rooms;
import com.test.project.file.HotelsData;
import com.test.project.file.RoomsData;
import com.test.project.service.interfaces.HotelRegisterManger;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
import com.test.project.StartApplication;
import com.test.project.menu.MenuNameManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.OptionalInt;

/**
 * 새로운 호텔과 객실 정보를 등록하는 클래스입니다.
 * 현재 로그인한 기업 회원의 ID로 호텔이 등록됩니다.
 */
public class HotelRegister implements HotelRegisterManger {
	
	/**
     * 현재 로그인한 기업 회원의 ID를 사용하여 호텔 등록 프로세스를 시작합니다.
     *
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    @Override
    public PageResult registerHotel() {
        return registerHotel(StartApplication.nowLoginID);
    }
    
    /**
     * 특정 기업 회원의 ID를 기반으로 호텔 및 객실 등록 프로세스를 실행합니다.
     * 호텔명, 주소, 최대 숙박 인원, 1박 가격 등의 호텔 정보를 입력받아 등록하고,
     * 이어서 해당 호텔에 속하는 객실 정보를 추가로 등록할 수 있도록 안내합니다.
     *
     * @param businessUserID 호텔을 등록할 기업 회원의 ID입니다.
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    public PageResult registerHotel(String businessUserID) {
        PageResult result = new PageResult();
        UI.title("호텔 정보 등록");

        System.out.print("등록할 호텔명 (0 입력 시 뒤로가기): ");
        String hotelName = InputUtil.getStringData();

        if (hotelName.equals("0")) {
            System.out.println("호텔 정보 등록을 취소합니다.");
            result.nextPage = MenuNameManager.기업회원_호텔관리;
            return result;
        }

        // 중복 호텔명 체크 (로그인 ID와 관계없이 전체 호텔명 중복 체크)
        boolean isDuplicate = HotelsData.hotelsList.stream()
                .anyMatch(h -> h.getHotelName().equalsIgnoreCase(hotelName));
        if (isDuplicate) {
            System.out.println("이미 등록된 호텔명입니다. 다른 이름을 사용해주세요.");
            UI.pressEnterUI();
            result.nextPage = MenuNameManager.기업회원_호텔관리;
            return result;
        }

        System.out.print("광역시: ");
        String addressCity = InputUtil.getStringData();
        System.out.print("시/군/구: ");
        String addressCounty = InputUtil.getStringData();
        // 변경 시작
        //System.out.print("최대 숙박 인원: ");
        int guestCount = InputUtil.getValidatedInteger("최대 숙박 인원: "); //
        //System.out.print("1박 가격: ");
        int price = InputUtil.getValidatedInteger("1박 가격: "); //
        // 변경 끝

        Hotels newHotel = new Hotels(hotelName, addressCity, addressCounty, guestCount, price, businessUserID);
        HotelsData.hotelsList.add(newHotel);
        HotelsData.saveHotelsData(HotelsData.hotelsList);

        System.out.println("\n호텔 '" + hotelName + "'이 성공적으로 등록되었습니다.");
        UI.pressEnterUI();

        // 객실 정보 등록
        while (true) {
            UI.title(hotelName + " 호텔 객실 정보 등록");
            // 변경 시작
            //System.out.print("객실 번호 (0 입력 시 종료): ");
            System.out.println("객실 번호를 입력하세요. (0 입력 시 종료)");
            int roomNumber = InputUtil.getValidatedInteger("객실 번호: "); //
            // 변경 끝

            if (roomNumber == 0) {
                break;
            }
            System.out.println("예약 가능 날짜를 입력하세요. (0 입력 시 종료)");
            System.out.print("예약 가능 날짜 (YYYY-MM-DD): ");
            String dateStr = InputUtil.getStringData();

            if (dateStr.equals("0")) {
                break;
            }

            Calendar availableDate = Calendar.getInstance();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(dateStr);
                availableDate.setTime(date);
            } catch (Exception e) {
                System.out.println("잘못된 날짜 형식입니다. YYYY-MM-DD 형식으로 입력해주세요.");
                continue;
            }
            
            // 객실 일련번호 자동 증가 (현재 최대 일련번호 + 1)
            OptionalInt maxSerial = RoomsData.roomsList.stream()
                                            .mapToInt(Rooms::getRoomSerial)
                                            .max();
            int newRoomSerial = maxSerial.orElse(0) + 1;

            // 객실 상태를 "예약가능"으로 고정 [변경사항]
            Rooms newRoom = new Rooms(newRoomSerial, roomNumber, availableDate, hotelName, "예약가능");
            RoomsData.roomsList.add(newRoom);
            System.out.println("객실 (일련번호: " + newRoomSerial + ") 정보가 추가되었습니다.");
        }
        RoomsData.saveRoomsData();
//        HotelDataWriter.saveRoomsData(RoomsData.roomsList);
        System.out.println("모든 호텔 및 객실 정보 등록이 완료되었습니다.");
        UI.pressEnterUI();
        result.nextPage = MenuNameManager.기업회원_호텔관리;
        return result;
    }
}