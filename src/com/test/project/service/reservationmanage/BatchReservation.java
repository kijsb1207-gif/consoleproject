package com.test.project.service.reservationmanage;


import java.util.ArrayList;

import com.test.project.data.ReservationRequest;
import com.test.project.menu.MenuNameManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

/**
 * 예약일괄승인과 관련된 기능을 담은 클래스
 */
public class BatchReservation {

	/**
	 * 대기 중인 예약 요청을 일괄 승인하는 기능을 수행하는 메서드.
	 * 현재 로그인한 기업 회원의 '예약요청' 상태인 대기 중인 모든 요청을 일괄적으로 승인 처리합니다.
	 *
	 * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체
	 */
    public PageResult processBulkApproval() {
    	PageResult result = new PageResult();
    	
        UI.title("예약 일괄 승인");
        
        // 현재 로그인한 기업 회원의 예약 요청만 필터링
        ArrayList<ReservationRequest> filteredApprovalRequests = HotelReservationManagement.getFilteredReservationRequests(com.test.project.StartApplication.nowLoginID, "예약요청"); // StartApplication.nowLoginID 추가
        if (!HotelReservationRequestViewer.hasPendingRequests(filteredApprovalRequests, "예약요청")) { // 변경
            System.out.println("일괄 승인할 대기중인 예약 요청이 없습니다.");
            UI.pressEnterUI();
            result.nextPage = MenuNameManager.기업회원_예약승인;
        } else { // 일괄 승인할 요청이 있을 경우에만 다음 로직 실행
            System.out.println("현재 대기중인 예약 요청을 모두 승인하시겠습니까?");
            UI.menuDetail("승인", "뒤로가기");
            String choice = InputUtil.SCANNER.nextLine();
    
            if (choice.equals("1")) {
                editApproval(filteredApprovalRequests); // 필터링된 리스트를 전달
                UI.pressEnterUI();
                result.nextPage = MenuNameManager.기업회원_예약승인;
            } else if (choice.equals("2")) {
                System.out.println("일괄 승인을 취소하고 뒤로 돌아갑니다.");
                UI.pressEnterUI();
                result.nextPage = MenuNameManager.기업회원_예약승인;
            } else {
                System.out.println("잘못된 입력입니다.");
                UI.pressEnterUI();
                result.nextPage = MenuNameManager.기업회원_예약일괄승인;
            }
        }
        
        return result;
    }
    
    /**
     * 주어진 예약 요청 목록에서 '대기' 상태의 '예약요청'을 '승인'으로 변경하고,
     * 처리된 요청의 개수를 출력합니다.
     *
     * @param approvalRequests 승인 처리할 ReservationRequest 리스트
     */
	private void editApproval(ArrayList<ReservationRequest> approvalRequests) {
		int approvedCount = 0;
		for (ReservationRequest req : approvalRequests) {
		    if (req.getNowProcess().equals("대기") && req.getGeneralUserRequest().equals("예약요청")) {
		        HotelReservationManagement.updateReservationStatus(req.getRoomSerial(), "승인", "예약요청"); // "승인"을 넘겨 "예약 확정"으로 변경
		        approvedCount++;
		    }
		}
		if (approvedCount > 0) {
		    System.out.println(approvedCount + "건의 예약 요청이 일괄 승인되었습니다.");
		} else {
		    System.out.println("승인할 예약 요청이 없습니다. (모두 처리되었거나 대기 상태가 아님)");
		}
	}
}