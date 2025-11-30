package com.test.project.service.hotelmanage;

import com.test.project.data.Hotels;
import com.test.project.data.Rooms;
import com.test.project.file.HotelsData;
import com.test.project.file.RoomsData;
import com.test.project.service.interfaces.RegisteredHotelInfoManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
import com.test.project.StartApplication;
import com.test.project.menu.MenuNameManager; // MenuNameManager 임포트

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 현재 로그인한 기업 회원이 등록한 호텔과 객실 정보를 페이지네이션하여 보여주는 클래스입니다.
 */
public class RegisteredHotelInfo implements RegisteredHotelInfoManager {
	
	/**
	 * 페이지당 출력할 항목 수
	 */
    private static final int ITEMS_PER_PAGE = 10;
    
    /**
     * 현재 로그인한 기업 회원의 ID를 사용하여 등록된 호텔 정보를 표시하는 프로세스를 시작합니다.
     *
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    @Override
    public PageResult showHotelInfo() {
        return showHotelInfo(StartApplication.nowLoginID);
    }

    /**
     * 특정 기업 회원의 ID를 기반으로 등록된 호텔 및 객실 정보를 페이지네이션하여 표시합니다.
     * 사용자는 페이지를 이동하거나 특정 호텔의 상세 정보를 조회할 수 있습니다.
     *
     * @param businessUserID 호텔 정보를 조회할 기업 회원의 ID입니다.
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체입니다.
     */
    public PageResult showHotelInfo(String businessUserID) {
        Scanner sc = InputUtil.SCANNER;
        int currentPage = 1;
        PageResult result = new PageResult(); // PageResult 인스턴스 생성

        while (true) {
            UI.title(businessUserID + "님의 등록 호텔 및 객실 보기");

            // 현재 로그인한 기업 회원이 등록한 호텔만 필터링합니다.
            List<Hotels> ownedHotels = HotelsData.hotelsList.stream()
                    .filter(h -> h.getBusinessUserID().equals(businessUserID))
                    .collect(Collectors.toList());

            if (ownedHotels.isEmpty()) {
                System.out.println("등록된 호텔이 없습니다.");
                UI.pressEnterUI();
                result.nextPage = MenuNameManager.기업회원_호텔관리; // 뒤로가기
                return result; 
            }

            int totalPages = (int) Math.ceil((double) ownedHotels.size() / ITEMS_PER_PAGE);

            // 현재 페이지에 해당하는 호텔 목록을 가져옵니다.
            int startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
            int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, ownedHotels.size());
            List<Hotels> hotelsOnPage = ownedHotels.subList(startIndex, endIndex);
            
            System.out.println("\n--- "+ businessUserID +"님의 호텔 목록 (페이지 " + currentPage + "/" + totalPages + ") ---");
            for (int i = 0; i < hotelsOnPage.size(); i++) {
                Hotels hotel = hotelsOnPage.get(i);
                System.out.printf("%d. 호텔명: %s | 위치: %s %s | 숙박인원: %d인 | 1박가격: %d원\n",
                        startIndex + i + 1, hotel.getHotelName(), hotel.getAddressCity(), hotel.getAddressCounty(),
                        hotel.getGuestCount(), hotel.getPrice());
            }
            System.out.println("------------------------------------------------------------------");

            System.out.println("\n명령을 입력하세요. ");
            System.out.println("  '>': 다음 페이지, '<': 이전 페이지, '0': 뒤로가기, [호텔 번호]: 상세 보기");
            System.out.print("입력: ");
            String command = sc.nextLine().trim();

            if (command.equals(">")) {
                if (currentPage < totalPages) {
                    currentPage++;
                } else {
                    System.out.println("마지막 페이지입니다.");
                }
            } else if (command.equals("<")) {
                if (currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println("첫 페이지입니다.");
                }
            } else if (command.equals("0")) {
                System.out.println("호텔 관리 메뉴로 돌아갑니다.");
                result.nextPage = MenuNameManager.기업회원_호텔관리;
                return result; // 뒤로가기
            } else {
                try {
                    int hotelIndex = Integer.parseInt(command) - 1;
                    if (hotelIndex >= startIndex && hotelIndex < endIndex) {
                        Hotels selectedHotel = ownedHotels.get(hotelIndex); // 전체 리스트에서 선택된 호텔 가져오기
                        displayHotelDetails(selectedHotel);
                    } else {
                        System.out.println("유효하지 않은 호텔 번호입니다. 현재 페이지의 번호를 입력해주세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            }
            UI.pressEnterUI();
        }
    }
    
    /**
     * 선택된 호텔의 상세 정보와 해당 호텔에 등록된 객실 목록을 표시합니다.
     *
     * @param hotel 상세 정보를 표시할 Hotels 객체입니다.
     */
    private void displayHotelDetails(Hotels hotel) {
        UI.title(hotel.getHotelName() + " 상세 정보 및 객실");

        System.out.println("호텔명: " + hotel.getHotelName());
        System.out.println("위치: " + hotel.getAddressCity() + " " + hotel.getAddressCounty());
        System.out.println("숙박인원: " + hotel.getGuestCount() + "인");
        System.out.println("1박 가격: " + hotel.getPrice() + "원");
        System.out.println("기업회원 ID: " + hotel.getBusinessUserID());

        System.out.println("\n--- 해당 호텔의 객실 목록 ---");
        List<Rooms> hotelRooms = RoomsData.roomsList.stream()
                .filter(r -> r.getHotelName().equals(hotel.getHotelName()))
                .collect(Collectors.toList());

        if (hotelRooms.isEmpty()) {
            System.out.println("등록된 객실이 없습니다.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("일련번호\t객실번호\t예약가능날짜\t\t예약상태");
            UI.singleLine2(50);
            System.out.println();
            for (Rooms room : hotelRooms) {
                System.out.printf("%d\t\t%d\t\t%s\t\t%s\n",
                        room.getRoomSerial(),
                        room.getRoomNumber(),
                        sdf.format(room.getAvailableDate().getTime()),
                        room.getReservationStat());
            }
        }
    }
}