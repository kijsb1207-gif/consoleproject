package com.test.project.service.reservationmanage;

import com.test.project.data.ReservationRequest;

import java.util.ArrayList;

/** 
 * 예약 요청을 표 형태로 출력하기 위한 클래스
 */
public class HotelReservationRequestViewer {
	 /**
     * ReservationRequest 목록을 테이블 형태로 출력.
     * 특정 요청 타입(예: "예약요청", "수정요청", "취소요청")에 해당하는 '대기' 상태의 요청만 필터링하여 출력.
     *
     * @param requests 출력할 ReservationRequest 리스트
     * @param requestType 필터링할 요청 타입 (예: "예약요청", "수정요청", "취소요청")
     */
    public static void displayPendingRequests(ArrayList<ReservationRequest> requests, String requestType) {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-15s | %-15s | %-15s\n", "객실일련번호", "요청사항", "요청날짜", "처리상태", "기업회원ID");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        boolean hasRequestsToDisplay = false;
        for (ReservationRequest req : requests) {
            if (req.getNowProcess().equals("대기") && req.getGeneralUserRequest().equals(requestType)) {
                System.out.printf("        %-10d | %-15s | %tF       | %-15s | %-15s\n",
                        req.getRoomSerial(),
                        req.getGeneralUserRequest(),
                        req.getRequestDate(),
                        req.getNowProcess(),
                        req.getBusinessUserId());
                hasRequestsToDisplay = true;
            }
        }
        if (!hasRequestsToDisplay) {
            System.out.println("현재 대기중인 " + requestType + "이(가) 없습니다.");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * 주어진 요청 목록에 '대기' 상태의 특정 요청 타입이 있는지 확인.
     *
     * @param requests 확인할 ReservationRequest 리스트
     * @param requestType 확인할 요청 타입 (예: "예약요청", "수정요청", "취소요청")
     * @return 대기 상태의 해당 요청 타입이 있으면 true, 없으면 false
     */
    public static boolean hasPendingRequests(ArrayList<ReservationRequest> requests, String requestType) {
        for (ReservationRequest req : requests) {
            if (req.getNowProcess().equals("대기") && req.getGeneralUserRequest().equals(requestType)) {
                return true;
            }
        }
        return false;
    }
}