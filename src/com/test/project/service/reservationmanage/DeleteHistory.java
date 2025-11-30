package com.test.project.service.reservationmanage;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.project.StartApplication;
import com.test.project.data.ReservationRequest;
import com.test.project.file.ReservationRequestData;
import com.test.project.menu.MenuNameManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

/**
 * 예약 취소 신청 내역을 관리하고 처리하는 클래스입니다.
 */
public class DeleteHistory {

	/**
     * 예약 취소 신청 내역을 처리하는 메서드.
     * 현재 로그인한 기업 회원의 '취소요청' 상태인 대기 중인 요청을 표시하고,
     * 사용자로부터 객실 일련번호를 입력받아 해당 요청을 승인하거나 거절합니다.
     *
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체
     */
    public PageResult start() {
    	PageResult result = new PageResult();
    	String businessUserId = StartApplication.nowLoginID;
    	Scanner scan = InputUtil.SCANNER;
        while (true) {
            UI.title("예약 취소 신청내역");
            
            // 현재 로그인한 기업 회원의 취소 요청만 필터링
            ArrayList<ReservationRequest> filteredCancellationRequests = HotelReservationManagement.getFilteredReservationRequests(businessUserId, "취소요청"); // 추가

            if (!HotelReservationRequestViewer.hasPendingRequests(filteredCancellationRequests, "취소요청")) { // 변경
                System.out.println("현재 처리할 취소 요청이 없습니다. 예약 관리 메뉴로 이동합니다.");
                result.nextPage = MenuNameManager.기업회원_예약관리;
                UI.pressEnterUI();
                break;
            }

            System.out.println("다음 취소 요청이 있습니다:");
            HotelReservationRequestViewer.displayPendingRequests(filteredCancellationRequests, "취소요청"); // 변경

            int roomSerial = InputUtil.getValidatedInteger("처리할 객실 일련번호를 입력하세요. (뒤로가기 -> 0): ");

            if (roomSerial == 0) {
                System.out.println("예약 취소 관리를 종료하고 뒤로 돌아갑니다.");
                result.nextPage = MenuNameManager.기업회원_예약관리;
                UI.pressEnterUI();
                break;
            }

            ReservationRequest targetRequest = null;
            // 필터링된 리스트에서 대상 요청 찾기
            for (ReservationRequest req : filteredCancellationRequests) { // 변경
                if (req.getRoomSerial() == roomSerial && req.getNowProcess().equals("대기") && req.getGeneralUserRequest().equals("취소요청")) {
                    targetRequest = req;
                    break;
                }
            }

            if (targetRequest != null) {
                System.out.printf("객실 일련번호 %d에 대한 취소 요청을 처리합니다.\n", roomSerial);
                UI.menuDetail("승인", "거절", "뒤로가기");
                String action = scan.nextLine();

                if (action.equals("1")) {
                    HotelReservationManagement.updateReservationStatus(roomSerial, "취소승인", "취소요청");
                    System.out.println("예약 취소 요청이 승인되었습니다. 해당 객실은 더 이상 사용 불가능합니다.");
                } else if (action.equals("2")) {
                    HotelReservationManagement.updateReservationStatus(roomSerial, "취소거절", "취소요청");
                    System.out.println("예약 취소 요청이 거절되었습니다.");
                } else if (action.equals("3")) {
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    UI.pressEnterUI();
                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                }
                UI.pressEnterUI();
                // 수정 후에도 현재 로그인 ID에 해당하는 요청만 다시 필터링
                ReservationRequestData.reservationRequestList = HotelReservationManagement.getFilteredReservationRequests(businessUserId, "취소요청"); // 이 라인은 필요에 따라 남겨둘 수 있으나, displayPendingRequests에 filteredList를 넘기면 이 부분은 없어도 무방함. 데이터 로딩 로직에 따라 다름.
            } else {
                System.out.println("해당 객실 일련번호에 대한 대기중인 취소 요청을 찾을 수 없습니다. (혹은 이미 처리됨)");
                UI.pressEnterUI();
            }
        }
        
        return result;
    }
}