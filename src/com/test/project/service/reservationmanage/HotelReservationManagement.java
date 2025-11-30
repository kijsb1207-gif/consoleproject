package com.test.project.service.reservationmanage;

import com.test.project.data.Hotels;
import com.test.project.data.ReservationRequest;
import com.test.project.data.Rooms;
import com.test.project.file.HotelsData;
import com.test.project.file.RoomsData;
import com.test.project.file.ReservationRequestData;
import com.test.project.file.FileManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 호텔 예약 관련 데이터를 관리하고 처리하는 유틸리티 클래스입니다.
 * 호텔 이름 조회, 예약 요청 필터링, 예약 상태 업데이트, 파일 저장 등의 기능을 제공합니다.
 */
public class HotelReservationManagement {
	/**
	 * 주어진 기업 회원 ID에 해당하는 호텔 이름을 반환합니다.
	 *
	 * @param businessUserId 기업 회원 ID
	 * @return 해당 기업 회원 ID의 호텔 이름, 없으면 null
	 */
	public static String getHotelNameByBusinessId(String businessUserId) { // 매개변수 businessUserId 사용
	    for (Hotels hotel : HotelsData.hotelsList) {
	        if (hotel.getBusinessUserID().equals(businessUserId)) { // StartApplication.nowLoginID 대신 매개변수 사용
	            return hotel.getHotelName();
	        }
	    }
	    return null;
	}
	
	/**
     * 특정 기업 회원 ID와 요청 타입에 해당하는 대기 중인 예약 요청 목록을 필터링하여 반환합니다.
     *
     * @param businessUserId 필터링할 기업 회원 ID
     * @param requestType 필터링할 요청 타입 (예: "예약요청", "수정요청", "취소요청")
     * @return 필터링된 ReservationRequest 리스트
     */
    public static ArrayList<ReservationRequest> getFilteredReservationRequests(String businessUserId, String requestType) {
        ArrayList<ReservationRequest> filteredList = new ArrayList<>();
        String hotelName = getHotelNameByBusinessId(businessUserId);

        if (hotelName == null) {
            return filteredList;
        }

        for (ReservationRequest req : ReservationRequestData.reservationRequestList) {
            if (req.getBusinessUserId().equals(businessUserId) && req.getGeneralUserRequest().equals(requestType) && req.getNowProcess().equals("대기")) {
                for (Rooms room : RoomsData.roomsList) {
                    if (room.getRoomSerial() == req.getRoomSerial() && room.getHotelName().equals(hotelName)) {
                        filteredList.add(req);
                        break;
                    }
                }
            }
        }
        return filteredList;
    }

    /**
     * 예약 요청의 처리 상태를 업데이트하고 관련 객실의 예약 상태를 변경합니다.
     * 업데이트 후 변경된 내용을 파일에 저장합니다.
     *
     * @param roomSerial 처리할 객실의 일련번호
     * @param newProcessStatus 새로 설정할 처리 상태 (예: "승인", "거절", "취소승인", "취소거절")
     * @param generalUserRequestType 일반 사용자 요청 타입 (예: "예약요청", "수정요청", "취소요청")
     */
    public static void updateReservationStatus(int roomSerial, String newProcessStatus, String generalUserRequestType) {
        boolean updated = false;
        // ReservationRequestList 업데이트
        for (ReservationRequest req : ReservationRequestData.reservationRequestList) {
            if (req.getRoomSerial() == roomSerial && req.getGeneralUserRequest().equals(generalUserRequestType) && req.getNowProcess().equals("대기")) {
                req.setNowProcess(newProcessStatus);
                updated = true;
                break;
            }
        }

        // RoomsList 업데이트 (예약 승인 및 취소 시)
        if (updated) {
            for (Rooms room : RoomsData.roomsList) {
                if (room.getRoomSerial() == roomSerial) {
                    if (generalUserRequestType.equals("예약요청")) {
                        if (newProcessStatus.equals("승인")) {
                            room.setReservationStat("예약확정");
                        } else if (newProcessStatus.equals("거절")) {
                            room.setReservationStat("예약거절"); // 예약 요청 거절 시 '예약거절'
                            System.out.printf("객실 %d번은 예약 요청이 거절되어 '예약거절' 상태로 변경되었습니다.\n", roomSerial);
                        }
                    } else if (generalUserRequestType.equals("취소요청")) {
                        if (newProcessStatus.equals("취소승인")) {
                            room.setReservationStat("예약취소");
                        } else if (newProcessStatus.equals("취소거절")) {
                            room.setReservationStat("예약거절"); 
                            System.out.printf("객실 %d번의 취소 요청이 거절되어 '예약거절' 상태로 변경되었습니다.\n", roomSerial);
                        }
                    } else if (generalUserRequestType.equals("수정요청")) {
                        if (newProcessStatus.equals("거절")) {
                            room.setReservationStat("예약거절"); 
                            System.out.printf("객실 %d번의 수정 요청이 거절되어 '예약거절' 상태로 변경되었습니다.\n", roomSerial);
                        }
                    }
                    break;
                }
            }
            saveReservationRequestData();
            saveRoomsData();
        }
    }

    /**
     * 예약 수정 요청을 승인하고 객실의 예약 가능 날짜 및 예약 상태를 업데이트합니다.
     * 업데이트 후 변경된 내용을 파일에 저장합니다.
     *
     * @param roomSerial 수정 요청을 승인할 객실의 일련번호
     */
    public static void approveModification(int roomSerial) {
        boolean updated = false;
        Calendar requestedDate = null;

        for (ReservationRequest req : ReservationRequestData.reservationRequestList) {
            if (req.getRoomSerial() == roomSerial && req.getGeneralUserRequest().equals("수정요청") && req.getNowProcess().equals("대기")) {
                requestedDate = req.getRequestDate();
                req.setNowProcess("승인");
                updated = true;
                break;
            }
        }
        
        if (updated && requestedDate != null) {
            for (Rooms room : RoomsData.roomsList) {
                if (room.getRoomSerial() == roomSerial) {
                    room.setAvailableDate(requestedDate);
                    room.setReservationStat("예약확정");
                    break;
                }
            }
            saveReservationRequestData();
            saveRoomsData();
        }
    }

    /**
     * ReservationRequest 데이터를 파일에 저장합니다.
     * 현재 메모리에 로드된 예약 요청 목록을 파일에 덮어씁니다.
     */
    public static void saveReservationRequestData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.RESERVATIONREQUEST))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (ReservationRequest req : ReservationRequestData.reservationRequestList) {
                writer.write(req.getGeneralUserRequest() + "■"
                           + req.getRoomSerial() + "■"
                           + sdf.format(req.getRequestDate().getTime()) + "■"
                           + req.getNowProcess() + "■"
                           + req.getBusinessUserId() + "\n");
            }
        } catch (IOException e) {
            System.out.println("HotelReservationManagement.saveReservationRequestData");
            e.printStackTrace();
        }
    }

    /**
     * Rooms 데이터를 파일에 저장합니다.
     * 현재 메모리에 로드된 객실 목록을 파일에 덮어씁니다.
     */
    public static void saveRoomsData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.ROOMS))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Rooms room : RoomsData.roomsList) {
                writer.write(room.getRoomSerial() + "■"
                           + room.getRoomNumber() + "■"
                           + sdf.format(room.getAvailableDate().getTime()) + "■"
                           + room.getHotelName() + "■"
                           + room.getReservationStat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("HotelReservationManagement.saveRoomsData");
            e.printStackTrace();
        }
    }
    
    /**
     * 주어진 객실 일련번호에 해당하는 객실 정보를 찾아 반환합니다.
     *
     * @param roomSerial 찾을 객실의 일련번호
     * @return 해당 객실 일련번호의 Rooms 객체, 없으면 null
     */
    public static Rooms getRoomBySerial(int roomSerial) {
        for (Rooms room : RoomsData.roomsList) {
            if (room.getRoomSerial() == roomSerial) {
                return room;
            }
        }
        return null;
    }
}