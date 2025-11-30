// HotelSearch.java
package com.test.project.service.hotelsearch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.test.project.StartApplication;
import com.test.project.data.GeneralUserRoomsMatch; // GeneralUserRoomsMatch 임포트 추가
import com.test.project.data.HotelReviews;
import com.test.project.data.Hotels;
import com.test.project.data.Rooms;
import com.test.project.file.FileManager;
import com.test.project.file.GeneralUserRoomsMatchData; // GeneralUserRoomsMatchData 임포트 추가
import com.test.project.file.HotelReviewsData;
import com.test.project.file.HotelsData;
import com.test.project.file.RoomsData;
import com.test.project.menu.GeneralUserMenu;
import com.test.project.menu.MenuNameManager;
import com.test.project.service.interfaces.HotelSearchManager;
import com.test.project.service.reservationcheck.GetBusinessId;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
import com.test.project.util.PrintUtil;

/**
 * 검색기능 관련 클래스
 */
public class HotelSearch implements HotelSearchManager {

	public List<Hotels> searchResultHotelsList = new ArrayList<>();
//	public List<Rooms> searchResultRoomsList = new ArrayList<>();
	GeneralUserMenu gUserMenu = new GeneralUserMenu();

	/**
	 * 호텔검색메뉴의 시작점.
	 * 사용자에게 검색을 원하는 지역(광역시), 지역(시군구), 시작 날짜(yyyymmdd),끝날짜(yyyymmdd), 숙박인원수를 입력받는다.
	 * 시작날짜, 끝날짜는 parseStringToCalendar(String userDateInput) 메서드를 통해 string에서 calendar로 parsing하였다.
	 * @return
	 */
	@Override
	public PageResult getUserInput() {
		PageResult result = new PageResult();
		String hotelName, city, county;
		Calendar startDate, endDate;
		int guestCount;

		UI.title("호텔 검색");
		Scanner scan = InputUtil.SCANNER;

		System.out.println("검색을 원하는 호텔을 입력해주세요.");
		while (true) {
			System.out.print("호텔명: ");
			hotelName = scan.nextLine();
			if (stringValidator(hotelName)) {
				break;
			}
			System.out.println("호텔명은 한글만 입력 가능합니다. 다시 입력해주세요.");
		}

		System.out.println("검색을 원하는 지역을 입력해주세요.");
		while (true) {
			System.out.print("지역(광역시): ");
			city = scan.nextLine();
			if (stringValidator(city)) {
				break;
			}
			System.out.println("지역명은 한글만 입력 가능합니다. 다시 입력해주세요.");
		}

		while (true) {
			System.out.print("지역(시군구): ");
			county = scan.nextLine();
			if (stringValidator(county)) {
				break;
			}
			System.out.println("지역명은 한글만 입력 가능합니다. 다시 입력해주세요.");
		}


		System.out.println("검색을 원하는 날짜를 입력해주세요.");

		startDate = parseValidDate("시작 날짜");

		while (true) {

			endDate = parseValidDate("끝 날짜");
			if(endDate.before(startDate)) {
				System.out.println("끝 날짜는 시작 날짜보다 이후여야 합니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}

		System.out.println("숙박 인원수를 입력해주세요.");
		while (true) {
			try {

				System.out.print("숙박 인원 수: ");
				guestCount = Integer.parseInt(scan.nextLine());

				if (guestCount < 1) {
					System.out.println("숙박 인원수는 최소 1명 이상입니다. 다시 입력해주세요.");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println("숫자만 입력 가능합니다.");
				//e.printStackTrace();
			}
		}
		//사용자 입력값을 SearchKeyword keyword 객체에 저장
		SearchKeyword keyword = new SearchKeyword(hotelName, city, county, startDate, endDate, guestCount);
		System.out.println("\n[사용자 입력 검색어]");
		System.out.printf(" - 호텔명: %s\n - 광역시: %s\n - 시군구: %s\n - 시작날짜: %tF\n - 끝날짜: %tF\n - 인원수: %d\n"
						, hotelName, city, county, startDate, endDate, guestCount);

		this.searchResultHotelsList = checkEmptyRoom(keyword);
		if (this.searchResultHotelsList.isEmpty()) {
			System.out.println("\n검색 결과와 일치하는 호텔이 없습니다.");
			System.out.println("다시 검색하시겠습니까?");
			UI.menuDetail("예", "아니오");

			while (true) {
				String retry = scan.nextLine();
				if (retry.equals("1")) {
					result.nextPage = MenuNameManager.호텔검색;
					UI.pressEnterUI();
					break;
				} else if (retry.equals("2")) {
					result.nextPage = MenuNameManager.일반회원메뉴;
					System.out.println("검색을 종료하고 일반회원 메뉴로 이동합니다.");
					break;
				} else {
					System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
				}
			}

		} else {
			System.out.println("\n검색 결과가 있습니다. 정렬 방식을 선택해주세요.");
			StartApplication.keyword = keyword;
			result.nextPage = MenuNameManager.검색결과정렬선택;
		}



		return result;
	}

	/**
	 * 호텔명, 광역시, 시군구에 대한 유효성검사
	 * @param input
	 * @return
	 */
	public boolean stringValidator(String input) {

		return input.matches("^[가-힣]+$");
	}

	/**
	 * String을 Calendar로 파싱 및 날짜값에 대한 유효성 검사
	 * @param label : 라벨, 사용자한테 보여줄 메뉴
	 * @return
	 */
	public Calendar parseValidDate(String label) {
		Scanner scan = InputUtil.SCANNER;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		while (true) {
			System.out.printf("%s(YYYYMMDD): ", label);
			String input = scan.nextLine();
			if(!input.matches("\\d{8}")) {
				System.out.println("입력은 숫자 8자리(YYYYMMDD)여야 합니다.");
				continue;
			}

			try {
				Date date = sdf.parse(input);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				return cal;
			} catch (Exception e) {
				System.out.println("유효하지 않은 날짜입니다. 다시 입력해주세요.");
			}
		}


	}


	/**
	 * 사용자가 입력한 조건에 맞는 예약 가능한 호텔들을 확인
	 */
	@Override
	public List<Hotels> checkEmptyRoom(SearchKeyword keyword) {
//		PageResult result = new PageResult();
//		List<Hotels> searchResultHotelsList = new ArrayList<>();
		List<Hotels> hotelsList = HotelsData.hotelsList;
		List<Rooms> roomsList = RoomsData.roomsList;
		boolean hasHotelName = keyword.getUserhotelName() != null && !keyword.getUserhotelName().isBlank();
		//UI.title("조건과 일치하는 호텔");
		
		this.searchResultHotelsList.clear(); //검색이 한번 다 끝나고 뒤로가기 누르면 결과 저장한 컬렉션을 초기화

		for (Hotels h : hotelsList) {
			//1차 조건: 사용자 입력값 중 호텔명, 광역시, 시군구, 숙박인원수를 확인
			//조건이 길어져서 조건을 미리 변수에 저장
			boolean isNameMatch = 		keyword.getUserhotelName() != null && h.getHotelName().contains(keyword.getUserhotelName());
			boolean isCityMatch = 		h.getAddressCity().contains(keyword.getUserAddressCity());
			boolean isCountyMatch =		h.getAddressCounty().contains(keyword.getUserAddressCounty());
			boolean isGuestCountMatch = (h.getGuestCount() >= keyword.getUserGuestCount());
//			boolean hasKeyword = keyword.getUserhotelName() != null && !keyword.getUserhotelName().isBlank();
			boolean isMatched;
			
			if (hasHotelName) {
			    // 이름으로 우선 검색
			    isMatched = isNameMatch;
			} else {
			    // 이름 미입력 시 지역+인원으로 검색
				isMatched = isCityMatch && isCountyMatch && isGuestCountMatch;
			}
			if (!isMatched) continue;

			//2차 조건: 사용자 입력값 중 날짜 데이터를 확인
			boolean hasAvailableRoom = false;
			for (Rooms r : roomsList) {
				boolean isSameHotel = r.getHotelName().equals(h.getHotelName());
				boolean isAvailable = r.getReservationStat().equals("예약가능");
				Calendar roomDate = r.getAvailableDate();
				//사용자가 입력한 날짜 범위 안에 해당 객실이 포함되는지를 확인
				//사용자 입력 시작일 <= 예약 가능 날짜 <= 사용자 입력 종료일
				boolean isDateInRange = !roomDate.before(keyword.getUserStartDate()) && !roomDate.after(keyword.getUserEndDate());

				if(isSameHotel && isAvailable && isDateInRange) {
					hasAvailableRoom = true;
					break;
				}
			}

			if (hasAvailableRoom) {
				this.searchResultHotelsList.add(h);

			}
		}

		return searchResultHotelsList;
	}

	/**
	 * 사용자로부터 객실을 상세조회할 호텔을 입력받아 표로 출력
	 * 여기서의 입력값을 검색 도중 리뷰보기 메뉴에 넘겨줘야 함
	 */
	@Override
	public PageResult getUserHotelName() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;

		String sortSelect = StartApplication.howToSort;
		sortSearchResultHotelsList(sortSelect);

		UI.title("호텔 검색 결과 보기");

		System.out.printf("%-20s | %-20s | %-10s | %-10s\n", "호텔명", "위치", "1박 가격", "별점 평균");
	    UI.singleLine2(90);
	    System.out.println();

		//호텔명, 위치, 1박가격, 별점...
		for (Hotels h : searchResultHotelsList) {
			String name = h.getHotelName();
			String location = h.getAddressCity() +" "+h.getAddressCounty();
			String price = String.format("%,d원", h.getPrice());

			double avgStar = avgStarRating(name);
			String rating = String.format("%.1f점", avgStar);

			System.out.printf("%-20s | %-20s | %-10s | %-10s\n", name, location, price, rating);
		}
		UI.singleLine2(90);
		System.out.println();
		//예외처리 필요
		System.out.print("객실을 상세조회할 호텔명: ");
		String input = scan.nextLine().trim();

		while (input.isEmpty() || !isHotelNameVaild(input)) {
			if(input.isEmpty()) {
				System.out.println("입력값이 비어있습니다. 다시 입력해주세요.");
			} else {
				System.out.println("입력한 호텔명이 올바르지 않습니다. 다시 입력해주세요.");
			}
			System.out.print("객실을 상세조회할 호텔명: ");
			input = scan.nextLine().trim();
		}
		result.userHotelName = input;
		result.nextPage = MenuNameManager.검색결과메뉴보기;
		
		return result;
	}

	/**
	 * 사용자가 선택한 정렬기준에 따라 정렬한다.
	 * @param sortSelect
	 */
	private void sortSearchResultHotelsList(String sortSelect) {
		PrintUtil u = new PrintUtil();
		switch (sortSelect) {
	        case "price": // 가격순 오름차순
	        	u.prt_log("sortWithPrice");
	        	searchResultHotelsList.sort((hotel1, hotel2) ->
	        		Integer.compare(hotel1.getPrice(), hotel2.getPrice()));
	            break;
	        case "ratings": // 별점순 내림차순
	        	u.prt_log("sortWithRatings");
	            searchResultHotelsList.sort((h1, h2) ->
	                Double.compare(avgStarRating(h2.getHotelName()), avgStarRating(h1.getHotelName())));
	            break;
	        default:
	            // 정렬 없음
		}

	}

	/**
	 * 입력된 호텔명이 검색 결과 리스트에 존재하는지 확인
	 * @param name
	 * @return
	 */
	private boolean isHotelNameVaild(String name) {
		for (Hotels h : searchResultHotelsList) {
			if (h.getHotelName().equals(name)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 호텔명과 일치하는 리뷰들의 평균 별점을 계산
	 * @param hotelName
	 * @return
	 */
	public double avgStarRating(String hotelName) {
		List<HotelReviews> reviewList = HotelReviewsData.hotelReviewList;
		int total = 0;
		int count = 0;

		for (HotelReviews review: reviewList) {
			if(review.getHotelName().equals(hotelName)) {
				total += review.getStarRating();
				count++;
			}
		}

		if (count == 0) {
			return 0.0;
		}

		return (double)total/count;
	}


	/**
	 * 호텔명과 일치하는 객실 검색 결과 출력하기
	 */
	@Override
	public PageResult getResultTable() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		String hotelName = StartApplication.hotelName;

//		PrintUtil u = new PrintUtil();
//		u.prt_log("호텔명: " + hotelName);

		UI.title("객실 검색 결과");

		System.out.printf("%-10s | %-20s \n", "객실일련번호", "예약 가능한 날짜");

		UI.singleLine2(40);
		System.out.println();


		for (Rooms r : RoomsData.roomsList) {
			boolean isValidName = r.getHotelName().equals(hotelName);
//			boolean isValidDate = (r.getAvailableDate() >= StartApplication.keyword.getUserStartDate()) && r.getAvailableDate() <= StartApplication.keyword.getUserEndDate();
			boolean isValidDate =
				    r.getAvailableDate().compareTo(StartApplication.keyword.getUserStartDate()) >= 0 &&
				    r.getAvailableDate().compareTo(StartApplication.keyword.getUserEndDate()) <= 0;

			if (isValidName&&isValidDate) {
				System.out.printf("%-10s | %-20tF \n", r.getRoomSerial(), r.getAvailableDate());
				UI.singleLine2(40);
				System.out.println();
			}

		}
		System.out.println("예약할 객실을 선택하세요.");
		System.out.print("예약할 객실의 일련번호: ");
		StartApplication.roomID = Integer.parseInt(scan.nextLine());

		result = gUserMenu.reservationResultMenu();

		return result;
	}

	/**
	 * 예약 진행, 예약 신청이 완료되면 메세지 출력 후 마이페이지-예약일정확인으로 이동
	 */
	@Override
	public PageResult makeReservation() {
		PageResult result = new PageResult();
		int roomID = StartApplication.roomID;

		//예약 신청을 하면 해당 객실번호를 예약상태값.txt에서 찾아서 변경해야함, 변경 후 저장
//		for (ReservationStatus res : ReservationStatusData.reservationStatList) {
		for (Rooms r : RoomsData.roomsList) {
			if (r.getRoomSerial() == roomID) {
				r.setReservationStat("예약승인대기중"); // 객실 상태를 '예약승인대기중'으로 변경
				break; // 해당 객실을 찾았으므로 루프 종료
			}
		}
		RoomsData.saveRoomsData(); // 변경된 객실 정보 저장


		String businessId = GetBusinessId.getBusinessID(String.valueOf(roomID)); // roomID를 String으로 변환
		// ReservationRequest 생성 및 파일에 기록
		String line = String.format("예약요청■%s■%tF■대기■%s", roomID, Calendar.getInstance(), businessId); // 요청 타입 "예약요청"으로 명확히 지정, 현재 날짜 사용

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.RESERVATIONREQUEST, true))) {
	        writer.write(line);
	        writer.newLine();
	        // writer.close(); // 여기서 close() 하지 않습니다. try-with-resources가 자동으로 닫아줍니다.
	        
	    } catch (IOException e) {
	        System.out.println("요청 저장 중 오류 발생");
	        e.printStackTrace();
	    }
	   
		// *************************************************************************
		// ⚠ 추가해야 하는 부분 시작 ⚠
		// GeneralUserRoomsMatch 데이터 추가 및 저장
		GeneralUserRoomsMatch userRoomMatch = new GeneralUserRoomsMatch(roomID, StartApplication.nowLoginID);
		GeneralUserRoomsMatchData.roomsMatchList.add(userRoomMatch); // ArrayList에 추가
		GeneralUserRoomsMatchData.saveRoomsMatchData(); // 파일에 저장 (아래에 saveRoomsMatchData 메서드 추가 필요)

		// *************************************************************************
		// ⚠ 추가해야 하는 부분 끝 ⚠

		//메세지 출력
		System.out.println("예약 신청이 완료되었습니다.");
		System.out.println("예약 현황은 마이페이지에서 확인해주세요.");
		UI.singleLine2(68);
		System.out.println("\n엔터를 누르면 마이페이지로 이동합니다.");
		UI.pressEnterUI();
		result.nextPage = MenuNameManager.일반회원_마이페이지;
		return result;
	}



}