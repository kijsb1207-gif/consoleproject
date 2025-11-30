package com.test.project.service.reservationcheck;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.project.StartApplication;
import com.test.project.data.GeneralUserRoomsMatch;
import com.test.project.data.Rooms;
import com.test.project.file.FileManager;
import com.test.project.file.GeneralUserRoomsMatchData;
import com.test.project.file.RoomsData;
import com.test.project.menu.GeneralUserMenu;
import com.test.project.menu.MenuNameManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
/**
 * 예약 내역 확인, 수정, 취소 요청 기능이 있는 클래스
 */
public class ReservationCheck {

	/**
	 * 로그인한 아이디의 예약 내역을 출력
	 * @return
	 */
	public PageResult showReservationHistory() {
		PageResult result = new PageResult();
		GeneralUserMenu generalMenu = new GeneralUserMenu();
		
		String userId = StartApplication.nowLoginID;

		// 1. 내가 예약한 객실 ID 찾기
		List<Integer> myRoomIds = new ArrayList<>();
		for (GeneralUserRoomsMatch match : GeneralUserRoomsMatchData.roomsMatchList) {
			if (match.getGeneralUserId().equals(userId)) {
				myRoomIds.add(match.getRoomSerial());
			}
		}

		// 2. 해당 객실 ID의 상세정보 가져오기
		boolean found = false;
		for (Rooms room : RoomsData.roomsList) {
			if (myRoomIds.contains(room.getRoomSerial())) {
				System.out.printf("[%d][%d호] %s | 예약일자 : %tF | %s\n",room.getRoomSerial(), room.getRoomNumber(), room.getHotelName(),
						room.getAvailableDate(), room.getReservationStat());
				found = true;
			}
		}

		if (!found) {
			System.out.println("예약한 객실이 없습니다. 일반회원메뉴로 이동합니다.");
			result.nextPage = MenuNameManager.일반회원메뉴;
			UI.pressEnterUI();
		} else {
			result = generalMenu.reservationCheckMenu();
		}
		
		
		return result;
	}

	/**
	 * 예약일정변경
	 * @return
	 */
	public PageResult editReservationHistory() {
	    PageResult result = new PageResult();
	    GeneralUserMenu gMenu = new GeneralUserMenu();
	    Scanner scan = InputUtil.SCANNER;

	    String userId = StartApplication.nowLoginID;
	    List<Integer> myRoomIds = new ArrayList<>();
	    for (GeneralUserRoomsMatch match : GeneralUserRoomsMatchData.roomsMatchList) {
	        if (match.getGeneralUserId().equals(userId)) {
	            myRoomIds.add(match.getRoomSerial());
	        }
	    }

	    String roomId;
	    while (true) {
	        System.out.print("수정할 객실의 일련번호: ");
	        roomId = scan.nextLine();

	        try {
	            int parsedId = Integer.parseInt(roomId);
	            if (!myRoomIds.contains(parsedId)) {
	                System.out.println("본인이 예약한 객실이 아닙니다. 다시 입력해주세요.");
	                continue;
	            }
	            break; // 유효한 입력
	        } catch (NumberFormatException e) {
	            System.out.println("객실 일련번호는 숫자만 입력 가능합니다. 다시 입력해주세요.");
	        }
	    }

	    String newDate;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(false);
	    while (true) {
	        System.out.print("변경할 날짜를 입력하세요(예: 1994-11-04): ");
	        newDate = scan.nextLine();
	        try {
	            sdf.parse(newDate);
	            break;
	        } catch (ParseException e) {
	            System.out.println("날짜 형식이 올바르지 않습니다. 다시 입력해주세요.");
	        }
	    }

	    String businessId = GetBusinessId.getBusinessID(roomId);
	    if (businessId == null || businessId.isEmpty()) {
	        System.out.println("해당 객실의 기업 회원 정보를 찾을 수 없습니다.");
	        result.nextPage = MenuNameManager.일반회원_예약일정변경;
	        return result;
	    }

	    boolean updated = false;
	    for (Rooms room : RoomsData.roomsList) {
	        if (room.getRoomSerial() == Integer.parseInt(roomId)) {
	            room.setReservationStat("예약수정중");
	            updated = true;
	            break;
	        }
	    }

	    if (!updated) {
	        System.out.println("해당 객실을 찾을 수 없어 예약상태를 변경하지 못했습니다.");
	        result.nextPage = MenuNameManager.일반회원_예약일정변경;
	        return result;
	    }

	    RoomsData.saveRoomsData();

	    String line = String.format("수정요청■%s■%s■대기■%s", roomId, newDate, businessId);

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.RESERVATIONREQUEST, true))) {
	        writer.write(line);
	        writer.newLine();
	        writer.close();
	        System.out.println("수정 요청이 정상적으로 접수되었습니다.");
	        //result = gMenu.sendEditRequestMenu();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    result = gMenu.sendEditRequestMenu();
	    return result;
	}


	/**
	 * 예약 취소
	 * @return
	 */
	public PageResult cancellationReservationHistory() {
	    PageResult result = new PageResult();
	    GeneralUserMenu gMenu = new GeneralUserMenu();
	    Scanner scan = InputUtil.SCANNER;
	    UI.title("예약 취소");

	    String userId = StartApplication.nowLoginID;
	    List<Integer> myRoomIds = new ArrayList<>();
	    for (GeneralUserRoomsMatch match : GeneralUserRoomsMatchData.roomsMatchList) {
	        if (match.getGeneralUserId().equals(userId)) {
	            myRoomIds.add(match.getRoomSerial());
	        }
	    }

	    int roomSerial;
	    String roomId;
	    while (true) {
	        System.out.print("취소할 객실의 일련번호: ");
	        roomId = scan.nextLine();
	        try {
	            roomSerial = Integer.parseInt(roomId);
	            if (!myRoomIds.contains(roomSerial)) {
	                System.out.println("본인이 예약한 객실이 아닙니다. 다시 입력해주세요.");
	                result = gMenu.sendCancelRequestMenu();
	                continue;
	            }
	            break;
	        } catch (NumberFormatException e) {
	            System.out.println("객실 일련번호는 숫자만 입력 가능합니다. 다시 입력해주세요.");
	            result = gMenu.sendCancelRequestMenu();
	        }
	    }

	    String businessId = GetBusinessId.getBusinessID(roomId);
	    if (businessId == null || businessId.isEmpty()) {
	        System.out.println("해당 객실의 기업 회원 정보를 찾을 수 없습니다.");
	        result.nextPage = MenuNameManager.일반회원_마이페이지;
	        return result;
	    }

	    boolean updated = false;
	    for (Rooms room : RoomsData.roomsList) {
	        if (room.getRoomSerial() == roomSerial) {
	            room.setReservationStat("예약취소요청중");
	            updated = true;
	            break;
	        }
	    }

	    if (!updated) {
	        System.out.println("해당 객실을 찾을 수 없어 상태를 변경하지 못했습니다. 마이페이지로 이동합니다.");
	        UI.pressEnterUI();
	        result.nextPage = MenuNameManager.일반회원_마이페이지;
	        return result;
	    }

	    RoomsData.saveRoomsData(); //데이터 저장

	    String line = String.format("취소요청■%s■1970-01-01■대기■%s", roomId, businessId);

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.RESERVATIONREQUEST, true))) {
	        writer.write(line);
	        writer.newLine();
	        writer.close();
	        System.out.println("예약 취소 요청이 정상적으로 접수되었습니다.");
	    } catch (IOException e) {
	        System.out.println("요청 저장 중 오류 발생");
	        e.printStackTrace();
	    }
	    result = gMenu.sendCancelRequestMenu();
	    return result;
	}

}
