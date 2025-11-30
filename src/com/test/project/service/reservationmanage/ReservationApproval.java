package com.test.project.service.reservationmanage;

import com.test.project.StartApplication;
import com.test.project.data.ReservationRequest;
import com.test.project.file.ReservationRequestData;
import com.test.project.menu.MenuNameManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

import java.util.ArrayList;
/**
 * 기업 회원이 개별 예약 요청을 승인하거나 거절하는 기능을 담당하는 클래스이다.
 */
public class ReservationApproval {
	
	/**
     * 개별 예약 요청을 승인하거나 거절하는 기능을 수행하는 메서드.
     * 현재 로그인한 기업 회원의 '예약요청' 상태인 대기 중인 요청을 표시하고,
     * 사용자로부터 객실 일련번호를 입력받아 해당 요청을 승인 또는 거절 처리한다.
     *
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체
     */
    public PageResult processIndividualApprovalRejection() {
    	PageResult result= new PageResult();
    	String businessUserId = StartApplication.nowLoginID;
        
        while (true) {
            UI.title("예약 개별 승인/거절");

            // 현재 로그인한 기업 회원의 예약 요청만 필터링
            ArrayList<ReservationRequest> filteredApprovalRequests = HotelReservationManagement.getFilteredReservationRequests(businessUserId, "예약요청"); // 추가
            if (!HotelReservationRequestViewer.hasPendingRequests(filteredApprovalRequests, "예약요청")) { // 변경
                System.out.println("개별 처리할 대기중인 예약 요청이 없습니다. 예약 관리 메뉴로 이동합니다.");
                UI.pressEnterUI();
                result.nextPage = MenuNameManager.기업회원_예약관리;
                break;
            }
            
            System.out.println("다음 예약 요청이 있습니다.");
            HotelReservationRequestViewer.displayPendingRequests(filteredApprovalRequests, "예약요청"); // 변경

            int roomSerial = InputUtil.getValidatedInteger("처리할 객실 일련번호를 입력하세요 (뒤로가기 -> 0): ");

            if (roomSerial == 0) {
                System.out.println("개별 승인/거절을 취소하고 뒤로 돌아갑니다.");
                result.nextPage = MenuNameManager.기업회원_예약관리;
                UI.pressEnterUI();
                break;
            }

            ReservationRequest targetRequest = null;
            // 필터링된 리스트에서 대상 요청 찾기
            for (ReservationRequest req : filteredApprovalRequests) { // 변경
                if (req.getRoomSerial() == roomSerial && req.getNowProcess().equals("대기") && req.getGeneralUserRequest().equals("예약요청")) {
                    targetRequest = req;
                    break;
                }
            }

            if (targetRequest != null) {
                System.out.printf("객실 일련번호 %d에 대한 요청을 처리합니다.\n", roomSerial);
                UI.menuDetail("승인", "거절", "뒤로가기");
                String action = InputUtil.SCANNER.nextLine();

                if (action.equals("1")) {
                    HotelReservationManagement.updateReservationStatus(roomSerial, "승인", "예약요청"); // "예약확정"으로 내부적으로 변경됨
                    System.out.println("예약이 승인되었습니다.");
                    result.nextPage = MenuNameManager.기업회원_예약관리;
                    
                } else if (action.equals("2")) {
                    HotelReservationManagement.updateReservationStatus(roomSerial, "거절", "예약요청"); // "예약가능"으로 내부적으로 변경됨
                    System.out.println("예약이 거절되었습니다.");
                    result.nextPage = MenuNameManager.기업회원_예약관리;
                    
                } else if (action.equals("3")) {
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    result.nextPage = MenuNameManager.기업회원_예약관리;
                    
                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                }
                UI.pressEnterUI();
                // 수정 후에도 현재 로그인 ID에 해당하는 요청만 다시 필터링 (필요에 따라)
                ReservationRequestData.reservationRequestList = HotelReservationManagement.getFilteredReservationRequests(businessUserId, "예약요청"); 
            } else {
                System.out.println("해당 객실 일련번호에 대한 대기중인 예약 요청을 찾을 수 없습니다. (혹은 이미 처리됨)");
                System.out.println("예약 관리 메뉴로 이동합니다.");
                result.nextPage = MenuNameManager.기업회원_예약관리;
                UI.pressEnterUI();
                break;
            }
        }
        
        
		return result;
    }
}