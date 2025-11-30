package com.test.project;

import java.util.Scanner;

import com.test.project.file.BusinessUserData;
import com.test.project.file.GeneralUserData;
import com.test.project.file.GeneralUserRoomsMatchData;
import com.test.project.file.HistoryData;
import com.test.project.file.HotelReviewsData;
import com.test.project.file.HotelsData;
import com.test.project.file.ReservationRequestData;
import com.test.project.file.RoomsData;
import com.test.project.file.VisitHistoryData;
import com.test.project.menu.BusinessUserMenu;
import com.test.project.menu.GeneralUserMenu;
import com.test.project.menu.MenuNameManager;
import com.test.project.service.hotelmanage.HotelDelete;
import com.test.project.service.hotelmanage.HotelEdit;
import com.test.project.service.hotelmanage.HotelRegister;
import com.test.project.service.hotelmanage.RegisteredHotelInfo;
import com.test.project.service.hotelsearch.HotelSearch;
import com.test.project.service.hotelsearch.SearchKeyword;
import com.test.project.service.login.BusinessUserLogin;
import com.test.project.service.login.GeneralUserLogin;
import com.test.project.service.reservationcheck.ReservationCheck;
import com.test.project.service.reservationmanage.BatchReservation;
import com.test.project.service.reservationmanage.DeleteHistory;
import com.test.project.service.reservationmanage.EditHistory;
import com.test.project.service.reservationmanage.RequestHistoryMenu;
import com.test.project.service.reservationmanage.ReservationApproval;
import com.test.project.service.review.ReviewDetail;
import com.test.project.service.review.ShowReview;
import com.test.project.service.review.WriteReview;
import com.test.project.service.signin.BusinessUser;
import com.test.project.service.signin.GeneralUser;
import com.test.project.service.userhistory.UserHistory;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
import com.test.project.util.PrintUtil;

/**
 * 메인메서드 담고 있는 클래스
 */
public class StartApplication {
	/**
	 * 로그 출력용 PrintUtil 객체
	 */
	public static PrintUtil u = new PrintUtil();
	
	/**
	 * public static 전역변수, 사용자가 현재 로그인한 아이디를 저장
	 */
	public static String nowLoginID = "";
	/**
	 * public static 전역변수, 호텔명을 저장
	 */
	public static String hotelName = "";
	/**
	 * public static 전역변수, 객실일련번호를 저장
	 */
	public static int roomID = -1;
	/**
	 * public static 전역변수, 사용자의 검색어를 저장(SearchKeyword 객체)
	 */
	public static SearchKeyword keyword;
	/**
	 * public static 전역변수, 사용자가 선택한 정렬 방식을 저장
	 */
	public static String howToSort;
	
	/**
	 * 메인 메서드
	 * @param args
	 */
	public static void main(String[] args) {
		
		StartApplication.processMain();
		
		
	}
	
	/**
	 * 메뉴 분기에 대한 switch-case문을 담은 메서드
	 */
	public static void processMain() {
		
		dataLoad(); //데이터 로드(txt -> ArrayList에 복사)
		
		
		//객체 생성
		PageResult result = new PageResult();
		
		GeneralUserLogin pGeneralUserLogin = new GeneralUserLogin();		//일반회원으로 로그인
		BusinessUserLogin pBusinessUserLogin = new BusinessUserLogin();		//기업회원으로 로그인
		
		GeneralUser pGeneralAccountEdit = new GeneralUser();				//일반회원 회원정보수정
		
		GeneralUserMenu pGeneralUserMenu = new GeneralUserMenu();			//일반회원메뉴
		HotelSearch pHotelSearch = new HotelSearch();						//호텔검색
		
		ReservationCheck pReservationCheck = new ReservationCheck();		//예약 현황 확인
		
		UserHistory pUserHistory = new UserHistory();						//방문기록
		WriteReview pReview = new WriteReview();							//리뷰작성
		ShowReview pShowReview = new ShowReview();							//작성한 리뷰 보기 
		ReviewDetail reviewDetail = new ReviewDetail();
		
		BusinessUserMenu pBusinessUserMenu = new BusinessUserMenu();		//기업회원메뉴
		
		BusinessUser pBusinessAccountEdit = new BusinessUser();				//기업회원정보수정
		
		RegisteredHotelInfo pHotelInfo = new RegisteredHotelInfo();
		
		RequestHistoryMenu pRequestMenu = new RequestHistoryMenu();			//기업회원-예약승인
		BatchReservation pBatchApprove = new BatchReservation();			//기업회원 - 예약일괄승인
		ReservationApproval pReservationApproval = new ReservationApproval();//기업회원-예약개별승인
		
		EditHistory pEditHistory = new EditHistory();						//예약수정
		DeleteHistory pDeleteHistory = new DeleteHistory();					//예약취소
		HotelRegister pRegister = new HotelRegister();
		HotelEdit pEdit = new HotelEdit();
		HotelDelete pDelete = new HotelDelete();
		
		//최초 화면
		result.nextPage = MenuNameManager.시작화면; 
		
		//메뉴 분기에 대한 while문
		while(result.nextPage.equals(MenuNameManager.프로그램_종료) == false) {
			u.prt_log("page: " + result.nextPage);
			
			
			switch (result.nextPage) {
				//1. 시작화면 메뉴
				case MenuNameManager.시작화면:
					result = startMenuPage();
					break;
				case MenuNameManager.일반회원으로_로그인:
					result = pGeneralUserLogin.login();
					nowLoginID = result.id;
					break;
				case MenuNameManager.기업회원으로_로그인:
					result = pBusinessUserLogin.login();
					nowLoginID = result.id;
					break;
				case MenuNameManager.회원가입:
					result = signInMenuPage();
					break;
		
					
				//2. 회원가입 메뉴
				case MenuNameManager.일반회원가입:
					result = GeneralUserData.registerAndSaveUserData();
					break;
				case MenuNameManager.기업회원가입:
					result = BusinessUserData.registerAndSaveUserData();
					break;
		
					
				//3. 일반회원메뉴
				case MenuNameManager.일반회원메뉴:
					result = pGeneralUserMenu.generalUserMenu();
					break;
					
				//일반회원메뉴 - 호텔검색
				case MenuNameManager.호텔검색:
					result = pHotelSearch.getUserInput();
					break;
				case MenuNameManager.검색결과정렬선택:
					result = pGeneralUserMenu.hotelSearchSortMenu();
					break;
				case MenuNameManager.검색결과보기:
					result = pHotelSearch.getUserHotelName();
					hotelName = result.userHotelName;
					break;
				case MenuNameManager.검색결과메뉴보기:
					result = pGeneralUserMenu.hotelSearchResultMenu();
					break;
				case MenuNameManager.검색중리뷰상세보기:
					result = reviewDetail.showReviewDetail();
					break;
				case MenuNameManager.예약진행:
					result = pHotelSearch.getResultTable();
					break;
				case MenuNameManager.예약진행메뉴보기:
					result = pGeneralUserMenu.reservationResultMenu();
					break;
				case MenuNameManager.예약신청완료:
					result = pHotelSearch.makeReservation();
					break;
				
				//일반회원메뉴-마이페이지
				case MenuNameManager.일반회원_마이페이지:
					result = pGeneralUserMenu.myPageMenu();
					break;
					
				//일반회원메뉴-마이페이지-예약현황확인
				case MenuNameManager.일반회원_예약현황확인:
					result = pReservationCheck.showReservationHistory();
					break;
				case MenuNameManager.일반회원_예약일정변경:
					result = pReservationCheck.editReservationHistory();
					break;
				case MenuNameManager.일반회원_예약취소:
					result = pReservationCheck.cancellationReservationHistory();
					break;
					
				//일반회원메뉴 - 마이페이지 - 회원정보수정
				case MenuNameManager.일반회원_정보수정:
					result = pGeneralAccountEdit.accountEdit();
					nowLoginID = "";
					break;
					
				//일반회원메뉴-마이페이지-방문기록  
				case MenuNameManager.일반회원_방문기록:
					result = pUserHistory.showUserHistory();
					break;
				case MenuNameManager.일반회원_체크아웃여부체크:
					result = pGeneralUserMenu.checkOutMenu();
					break;
				case MenuNameManager.일반회원_리뷰작성하기:
					result = pReview.writeReview();
					break;
				case MenuNameManager.일반회원_작성한리뷰보기:
					result = pShowReview.showUserReview();
					break;
		
					
				//4. 기업회원메뉴
				case MenuNameManager.기업회원메뉴:
					result = pBusinessUserMenu.businessUserMenu();
					break;
				case MenuNameManager.기업회원_예약관리:
					result = pBusinessUserMenu.reservationManageMenu();
					break;
				case MenuNameManager.기업회원_호텔관리:
					result = pBusinessUserMenu.hotelManageMenu();
					break;
					
				//기업회원메뉴 - 정보수정
				case MenuNameManager.기업회원_정보수정:
					result = pBusinessAccountEdit.accountEdit();
					nowLoginID = "";
					break;
					
				//기업회원메뉴 - 예약관리메뉴
				case MenuNameManager.기업회원_예약승인:
					result = pRequestMenu.showApprovalRequest();
					break;
				case MenuNameManager.기업회원_예약일괄승인:
					result = pBatchApprove.processBulkApproval();
					break;
				case MenuNameManager.기업회원_예약개별승인:
					result = pReservationApproval.processIndividualApprovalRejection();
					break;
				case MenuNameManager.기업회원_예약수정:
					result = pEditHistory.start();
					break;
				case MenuNameManager.기업회원_예약취소:
					result = pDeleteHistory.start();
					break;
					
				//기업회원메뉴 - 호텔관리메뉴
				case MenuNameManager.기업회원_등록호텔객실보기:
					result = pHotelInfo.showHotelInfo();
					break;
				case MenuNameManager.기업회원_호텔정보등록:
					result = pRegister.registerHotel();
					break;
				case MenuNameManager.기업회원_호텔정보수정:
					result = pEdit.editHotel();
					break;
				case MenuNameManager.기업회원_호텔정보삭제:
					result = pDelete.hotelDelete();
					break;
		
				//잘못된 메뉴일 경우
				default:
					System.out.println("잘못된 메뉴입니다. 다시 입력하세요.");
					break;
			}
		}
	}

	/**
	 * dat 폴더에서 txt파일들을 모두 불러와 ArrayList에 복사하는 메서드
	 */
	public static void dataLoad() {
		BusinessUserData.businessUserDataLoad();				//기업회원.txt
		GeneralUserData.generalUserDataLoad();					//일반회원.txt
		HotelsData.hotelsDataLoad();							//호텔.txt
		RoomsData.roomsDataLoad();								//객실.txt
		GeneralUserRoomsMatchData.roomsMatchDataLoad();			//회원객실매칭.txt
		HistoryData.historyDataLoad();							//방문기록.txt
		VisitHistoryData.visitHistoryDataLoad();				//방문내역.txt
		HotelReviewsData.hotelReviewDataLoad();					//호텔리뷰.txt
		ReservationRequestData.reservationRequestDataLoad();	//예약요청.txt
		u.prt_log("데이터 로드 완료");
	}
	
	/**
	 * 시작 화면에 대한 UI를 출력하는 메서드
	 * @return PageResult result 이동할 페이지에 대한 정보를 담아 반환한다.
	 */
	public static PageResult startMenuPage() {
		PageResult result = new PageResult();
		
		Scanner scan = InputUtil.SCANNER;
		UI.splashTitle();
		UI.title("호텔 예약 시스템");
		UI.menuDetail("일반 회원 로그인", "기업 회원 로그인", "회원가입", "프로그램 종료");
		result.userMenuSelect = scan.nextLine();
		
		if (result.userMenuSelect.equals("1")) {
			result.nextPage = MenuNameManager.일반회원으로_로그인;
		} else if (result.userMenuSelect.equals("2")) {
			result.nextPage = MenuNameManager.기업회원으로_로그인;
		} else if (result.userMenuSelect.equals("3")) {
			result.nextPage = MenuNameManager.회원가입;
		} else if (result.userMenuSelect.equals("4")) {
			result.nextPage = MenuNameManager.프로그램_종료;
			System.out.println("프로그램을 종료합니다.");
		} else {
			result.nextPage = MenuNameManager.시작화면;
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
		
		return result; //이동할 페이지에 대한 정보를 담은 nextPage를 반환
	}
	
	/**
	 * 회원가입에 대한 UI
	 * @return PageResult result : 이동할 페이지에 대한 정보
	 */
	public static PageResult signInMenuPage() {
		PageResult result = new PageResult();
		
		UI.title("회원가입");
		UI.menuDetail("일반회원가입", "기업회원가입", "뒤로가기");
		Scanner scan = InputUtil.SCANNER;
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) {
			result.nextPage = MenuNameManager.일반회원가입;
		} else if(result.userMenuSelect.equals("2")) {
			result.nextPage = MenuNameManager.기업회원가입; 
		} else if(result.userMenuSelect.equals("3")) { //뒤로가기
			result.nextPage = MenuNameManager.시작화면; 
		} else { //메뉴 입력 잘못될경우 다시실행
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			result.nextPage = MenuNameManager.회원가입;
		}
		
		return result;
	}
	

}
