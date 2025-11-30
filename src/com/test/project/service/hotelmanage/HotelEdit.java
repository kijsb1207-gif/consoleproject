package com.test.project.service.hotelmanage;

import com.test.project.data.Rooms;
import com.test.project.file.HotelsData;
import com.test.project.file.RoomsData;
import com.test.project.service.interfaces.HotelEditManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
import com.test.project.StartApplication;
import com.test.project.menu.MenuNameManager; // MenuNameManager 임포트

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

/**
 * 현재 로그인한 기업 회원이 등록한 호텔의 객실 정보를 수정하는 클래스입니다.
 * 객실 일련번호를 통해 특정 객실의 정보를 수정합니다.
 */
public class HotelEdit implements HotelEditManager {
	/**
     * 현재 로그인한 기업 회원의 ID를 사용하여 호텔 객실 수정 프로세스를 시작합니다.
     *
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    @Override
    public PageResult editHotel() {
        return editHotel(StartApplication.nowLoginID);
    }
    
    /**
     * 특정 기업 회원의 ID를 기반으로 호텔 객실 수정 프로세스를 실행합니다.
     * 해당 기업 회원이 등록한 객실 목록을 표시하고, 사용자로부터 수정할 객실의 일련번호를 입력받아
     * 해당 객실의 호텔명, 객실 번호, 예약 가능 날짜, 예약 상태 등을 수정할 수 있도록 합니다.
     *
     * @param businessUserID 객실을 수정할 기업 회원의 ID입니다.
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    public PageResult editHotel(String businessUserID) {
        //Scanner sc = InputUtil.SCANNER;
        PageResult result = new PageResult(); // PageResult 인스턴스 생성

        while (true) {
            UI.title(businessUserID + "님의 호텔 객실 정보 수정");

            // 현재 로그인한 기업 회원이 등록한 호텔의 객실만 필터링합니다.
            List<Rooms> ownedRooms = RoomsData.roomsList.stream()
                    .filter(room -> HotelsData.hotelsList.stream()
                                    .anyMatch(hotel -> hotel.getHotelName().equals(room.getHotelName()) &&
                                                       hotel.getBusinessUserID().equals(businessUserID)))
                    .collect(Collectors.toList());

            if (ownedRooms.isEmpty()) {
                System.out.println("수정할 등록된 객실이 없습니다.");
                UI.pressEnterUI();
                result.nextPage = MenuNameManager.기업회원_호텔관리; // 뒤로가기
                return result;
            }

            System.out.println("\n--- "+businessUserID+"님이 등록한 호텔의 객실 목록 ---");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("일련번호\t호텔명\t\t객실번호\t예약가능날짜\t\t예약상태");
            UI.singleLine2(60);
            System.out.println();
            for (Rooms room : ownedRooms) {
                System.out.printf("%d\t\t%s\t%d\t\t%s\t\t%s\n",
                        room.getRoomSerial(),
                        room.getHotelName(),
                        room.getRoomNumber(),
                        sdf.format(room.getAvailableDate().getTime()),
                        room.getReservationStat());
            }
            System.out.println("------------------------------------------------------------------");

            // 변경 시작
            System.out.println("수정할 객실의 일련번호를 입력해주세요. (0 입력 시 뒤로가기)");
            int roomSerialToEdit = InputUtil.getValidatedInteger("객실 일련번호: "); 
            // 변경 끝

            if (roomSerialToEdit == 0) {
                System.out.println("객실 정보 수정을 취소합니다.");
                result.nextPage = MenuNameManager.기업회원_호텔관리; // 뒤로가기
                return result;
            }

            Optional<Rooms> roomOpt = RoomsData.roomsList.stream() // 전체 RoomsData.roomsList에서 찾음
                                            .filter(r -> r.getRoomSerial() == roomSerialToEdit)
                                            .findFirst();

            if (roomOpt.isPresent()) {
                Rooms roomToEdit = roomOpt.get();

                // 해당 객실이 로그인한 기업 회원의 호텔에 속하는지 다시 확인
                boolean isOwnerOfHotel = HotelsData.hotelsList.stream()
                                            .anyMatch(h -> h.getHotelName().equals(roomToEdit.getHotelName()) &&
                                                           h.getBusinessUserID().equals(businessUserID));
                
                if (!isOwnerOfHotel) {
                    System.out.println("해당 객실은 귀하의 소유가 아닙니다. 수정할 수 없습니다.");
                    UI.pressEnterUI();
                    continue; // 다시 목록으로 돌아감
                }

                UI.title("객실 일련번호 " + roomSerialToEdit + " 수정");

                System.out.println("1. 호텔명 (현재: " + roomToEdit.getHotelName() + ")");
                System.out.println("2. 객실 번호 (현재: " + roomToEdit.getRoomNumber() + ")");
                System.out.println("3. 예약 가능 날짜 (현재: " + sdf.format(roomToEdit.getAvailableDate().getTime()) + ")");
                System.out.println("4. 예약 상태 (현재: " + roomToEdit.getReservationStat() + ")");
                // 변경 시작
                System.out.println("수정할 항목을 선택해주세요. (0 입력 시 뒤로가기)");
                int editChoice = InputUtil.getValidatedInteger("수정할 항목: "); //
                // 변경 끝

                switch (editChoice) {
                    case 1:
                        System.out.print("새로운 호텔명: ");
                        String newHotelName = InputUtil.getStringData();
                        // 변경하려는 호텔명이 현재 로그인한 기업 회원의 소유인지 확인
                        boolean newHotelIsOwner = HotelsData.hotelsList.stream()
                                            .anyMatch(h -> h.getHotelName().equals(newHotelName) &&
                                                           h.getBusinessUserID().equals(businessUserID));

                        if (newHotelIsOwner) {
                            roomToEdit.setHotelName(newHotelName);
                            System.out.println("호텔명이 수정되었습니다.");
                        } else {
                            System.out.println("해당 호텔은 귀하의 소유가 아니거나 존재하지 않는 호텔입니다. 수정할 수 없습니다.");
                        }
                        break;
                    case 2:
                        // 변경 시작
                        roomToEdit.setRoomNumber(InputUtil.getValidatedInteger("새로운 객실 번호: ")); //
                        // 변경 끝
                        System.out.println("객실 번호가 수정되었습니다.");
                        break;
                    case 3:
                        System.out.print("새로운 예약 가능 날짜 (YYYY-MM-DD): ");
                        String newDateStr = InputUtil.getStringData();
                        try {
                            Date date = sdf.parse(newDateStr);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            roomToEdit.setAvailableDate(calendar);
                            System.out.println("예약 가능 날짜가 수정되었습니다.");
                        } catch (Exception e) {
                            System.out.println("잘못된 날짜 형식입니다. 수정이 취소되었습니다.");
                        }
                        break;
                    case 4:
                        System.out.print("새로운 예약 상태 (예: 예약가능, 예약확정, 예약승인대기중 등): ");
                        roomToEdit.setReservationStat(InputUtil.getStringData());
                        System.out.println("예약 상태가 수정되었습니다.");
                        break;
                    case 0:
                        System.out.println("객실 정보 수정을 취소합니다.");
                        break;
                    default:
                        System.out.println("잘못된 선택입니다.");
                        break;
                }
                RoomsData.saveRoomsData();
//                HotelDataWriter.saveRoomsData(RoomsData.roomsList); // HotelDataWriter를 통해 저장
                System.out.println("변경 사항이 저장되었습니다.");
            } else {
                System.out.println("입력하신 일련번호의 객실을 찾을 수 없습니다. 다시 입력해주세요.");
            }
            UI.pressEnterUI();
        }
    }
}