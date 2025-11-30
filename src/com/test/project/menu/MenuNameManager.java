package com.test.project.menu;

/**
 * pageResult 클래스 등에서 사용할 메뉴명(final 상수)를 담은 클래스
 */
public class MenuNameManager {
	public static final String 프로그램_종료 = "Exit";
	
	//1. 시작화면 메뉴
	public static final String 시작화면 = "StartMenu";
	public static final String 일반회원으로_로그인 = "GeneralUserLogin";
	public static final String 기업회원으로_로그인 = "BusinessUserLogin";
	public static final String 회원가입 = "SignInMenu";
	
	//2. 회원가입메뉴
	public static final String 일반회원가입 = "GeneralUserSignIn";
	public static final String 기업회원가입 = "BusinessUserSignIn";
	
	
	//3. 일반회원메뉴
	public static final String 일반회원메뉴 = "GeneralUserMenu";
	//일반회원메뉴 - 호텔검색
	public static final String 호텔검색 = "HotelSearchMenu";
	public static final String 검색결과정렬선택 = "SearchFilterMenu";
	public static final String 검색결과보기 = "SearchResultMenu";
	public static final String 검색결과메뉴보기 = "showSearchResultMenu"; //메뉴 입력 잘못했을 경우 메뉴만 다시 보여주도록
	public static final String 검색중리뷰상세보기 = "SearchReviewDetailMenu";
	
	public static final String 예약진행 = "MakeReservation";
	public static final String 예약진행메뉴보기 = "showReservationResultMenu"; //메뉴 입력 잘못했을 경우 메뉴만 다시 보여주도록
	public static final String 예약신청완료 = "ReservationComplete"; 
	//일반회원메뉴 - 마이페이지
	public static final String 일반회원_마이페이지 = "MyPageMenu";
	public static final String 일반회원_예약현황확인 = "GeneralUserReservationCheck";
	//일반회원메뉴 - 마이페이지 - 예약현황확인
	public static final String 일반회원_예약일정변경 = "GeneralUserReservationEdit";
	public static final String 일반회원_예약취소 = "GeneralUserReservationCancel";
	//일반회원메뉴 - 마이페이지 - 회원정보수정
	public static final String 일반회원_정보수정 = "GeneralUserInfoEdit";
	//일반회원메뉴 - 마이페이지 - 방문기록
	public static final String 일반회원_방문기록 = "GeneralUserHistory";
	public static final String 일반회원_리뷰작성하기 = "GeneralUserWriteReview";
	public static final String 일반회원_체크아웃여부체크 = "CheckCheckOut";
	public static final String 일반회원_작성한리뷰보기 = "GeneralUserReviewHistory";
	
	
	
	//4. 기업회원메뉴
	public static final String 기업회원메뉴 = "BusinessUserMenu";
	public static final String 기업회원_예약관리 = "BusinessReservationConfigMenu";
	public static final String 기업회원_호텔관리 = "BusinessHotelConfigMenu";
	//기업회원메뉴 - 회원정보수정
	public static final String 기업회원_정보수정 = "BusinessUserInfoEdit";
	//기업회원메뉴 - 예약관리메뉴
	public static final String 기업회원_예약승인 = "BusinessReservationApproveMenu";
	public static final String 기업회원_예약일괄승인= "BizReservationAllApproveMenu";
	public static final String 기업회원_예약개별승인= "BizReservationSingleApproveMenu";
	public static final String 기업회원_예약수정 = "BusinessReservationEditMenu";
	public static final String 기업회원_예약취소 = "BusinessReservationCancelMenu";
	//기업회원메뉴 - 호텔관리메뉴
	public static final String 기업회원_등록호텔객실보기 = "BusinessRegisteredHotelRooms";
	public static final String 기업회원_호텔정보등록 = "BusinessRegisterNewHotel";
	public static final String 기업회원_호텔정보수정 = "BusinessEditHotelInfo";
	public static final String 기업회원_호텔정보삭제 = "BusinessDeleteHotelInfo";
	
	
	
	
	
}
