package com.test.project.service.hotelmanage;

import com.test.project.data.Rooms;
import com.test.project.file.HotelsData;
import com.test.project.file.RoomsData;
import com.test.project.service.interfaces.HotelDeleteManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
import com.test.project.StartApplication;
import com.test.project.menu.MenuNameManager; // MenuNameManager 임포트

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 현재 로그인한 기업 회원이 등록한 호텔의 객실 정보를 삭제하는 클래스입니다.
 * 객실 일련번호를 통해 특정 객실 데이터를 삭제합니다.
 */
public class HotelDelete implements HotelDeleteManager {
	
	/**
     * 현재 로그인한 기업 회원의 ID를 사용하여 호텔 객실 삭제 프로세스를 시작합니다.
     *
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    @Override
    public PageResult hotelDelete() {
        return hotelDelete(StartApplication.nowLoginID);
    }
    
    /**
     * 특정 기업 회원의 ID를 기반으로 호텔 객실 삭제 프로세스를 실행합니다.
     * 해당 기업 회원이 등록한 객실 목록을 표시하고, 사용자로부터 삭제할 객실의 일련번호를 입력받아
     * 확인 후 객실 정보를 삭제합니다.
     *
     * @param businessUserID 객실을 삭제할 기업 회원의 ID입니다.
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    public PageResult hotelDelete(String businessUserID) {
        
        PageResult result = new PageResult(); // PageResult 인스턴스 생성

        while (true) {
            UI.title(businessUserID + "님의 호텔 객실 정보 삭제");

            // 현재 로그인한 기업 회원이 등록한 호텔의 객실만 필터링합니다.
            List<Rooms> ownedRooms = RoomsData.roomsList.stream()
                    .filter(room -> HotelsData.hotelsList.stream()
                                    .anyMatch(hotel -> hotel.getHotelName().equals(room.getHotelName()) &&
                                                       hotel.getBusinessUserID().equals(businessUserID)))
                    .collect(Collectors.toList());

            if (ownedRooms.isEmpty()) {
                System.out.println("삭제할 객실이 없습니다.");
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
            System.out.println("삭제할 객실의 일련번호를 입력해주세요. (0 입력 시 뒤로가기)");
            int roomSerialToDelete = InputUtil.getValidatedInteger("객실 일련번호: "); //
            // 변경 끝

            if (roomSerialToDelete == 0) {
                System.out.println("객실 정보 삭제를 취소합니다.");
                result.nextPage = MenuNameManager.기업회원_호텔관리; // 뒤로가기
                return result;
            }

            Optional<Rooms> roomToRemoveOpt = RoomsData.roomsList.stream() // 전체 RoomsData.roomsList에서 찾음
                                                    .filter(r -> r.getRoomSerial() == roomSerialToDelete)
                                                    .findFirst();

            if (roomToRemoveOpt.isPresent()) {
                Rooms roomToRemove = roomToRemoveOpt.get();

                // 해당 객실이 로그인한 기업 회원의 호텔에 속하는지 다시 확인
                boolean isOwnerOfHotel = HotelsData.hotelsList.stream()
                                            .anyMatch(h -> h.getHotelName().equals(roomToRemove.getHotelName()) &&
                                                           h.getBusinessUserID().equals(businessUserID));
                
                if (!isOwnerOfHotel) {
                    System.out.println("해당 객실은 귀하의 소유가 아닙니다. 삭제할 수 없습니다.");
                    UI.pressEnterUI();
                    continue; // 다시 목록으로 돌아감
                }

                System.out.print("정말로 객실 일련번호 '" + roomToRemove.getRoomSerial() + "' (호텔: " + roomToRemove.getHotelName() + ", 객실번호: " + roomToRemove.getRoomNumber() + ")를 삭제하시겠습니까? (Y/N): ");
                String confirm = InputUtil.getStringData().toUpperCase();

                if (confirm.equals("Y")) {
                    RoomsData.roomsList.remove(roomToRemove);
                    RoomsData.saveRoomsData();
                    //HotelDataWriter.saveRoomsData(RoomsData.roomsList); // HotelDataWriter를 통해 저장
                    System.out.println("객실 정보가 성공적으로 삭제되었습니다.");
                } else {
                    System.out.println("객실 삭제를 취소합니다.");
                }
            } else {
                System.out.println("입력하신 일련번호의 객실을 찾을 수 없습니다. 다시 입력해주세요.");
            }
            UI.pressEnterUI();
        }
    }
}