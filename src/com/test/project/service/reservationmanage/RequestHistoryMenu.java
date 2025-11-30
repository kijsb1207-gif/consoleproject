package com.test.project.service.reservationmanage;


import java.util.ArrayList;

import com.test.project.StartApplication;
import com.test.project.data.ReservationRequest;
import com.test.project.menu.BusinessUserMenu;
import com.test.project.menu.MenuNameManager;
import com.test.project.ui.UI;
import com.test.project.util.PageResult;
/**
 * 기업 회원의 예약 승인 - 예약 신청 내역을 보여주는 클래스입니다.
 */
public class RequestHistoryMenu {
	/**
     * 현재 로그인한 기업 회원의 '예약요청' 내역을 표시하고,
     * 만약 처리할 예약 요청이 있다면 예약 승인 메뉴를 보여줍니다.
     *
     * @return 다음 페이지로의 전환 정보를 담은 PageResult 객체
     */
    public PageResult showApprovalRequest() {
    	
    	PageResult result = new PageResult();
    	BusinessUserMenu bizMenu = new BusinessUserMenu();
    	

        	
        UI.title("예약 신청 내역");
        
        // 현재 로그인한 기업 회원의 예약 요청만 필터링
        ArrayList<ReservationRequest> approvalRequests = HotelReservationManagement.getFilteredReservationRequests(StartApplication.nowLoginID, "예약요청");

        
        
        if (!HotelReservationRequestViewer.hasPendingRequests(approvalRequests, "예약요청")) {
            System.out.println("현재 처리할 예약 요청이 없습니다.");
            result.nextPage = MenuNameManager.기업회원_예약관리; // 처리할 요청이 없으면 메뉴를 나감
            UI.pressEnterUI();
        } else {
        	System.out.println("다음 예약 요청이 있습니다:");
            HotelReservationRequestViewer.displayPendingRequests(approvalRequests, "예약요청");
            result = bizMenu.reservationApprovalMenu();
        }
       
        return result;
    }
}