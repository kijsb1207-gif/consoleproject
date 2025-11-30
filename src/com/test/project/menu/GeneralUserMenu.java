package com.test.project.menu;

import java.util.Scanner;

import com.test.project.StartApplication;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

/**
 * 일반회원으로 로그인 시 선택 가능한 메뉴들
 */
public class GeneralUserMenu {
	/**
	 * 일반회원 메뉴
	 * @return
	 */
	public PageResult generalUserMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.title("일반회원 메뉴");
		UI.menuDetail("호텔 검색", "마이페이지", "로그아웃");
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) { //호텔검색
			result.nextPage = MenuNameManager.호텔검색;
			
		} else if(result.userMenuSelect.equals("2")) { //마이페이지 
			result.nextPage = MenuNameManager.일반회원_마이페이지;
			
		} else if(result.userMenuSelect.equals("3")) { //로그아웃
			result.nextPage = MenuNameManager.시작화면;
			
		} else { //입력 잘못된 경우
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			result.nextPage = MenuNameManager.일반회원메뉴;
		}
		
		return result;
	}
	
	
	/**
	 * 호텔검색메뉴 - 검색 결과 정렬 선택
	 * @return 
	 */
	public PageResult hotelSearchSortMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.title("검색 결과 정렬 선택");
		UI.menuDetail("정렬 없음", "가격순 정렬", "별점순 정렬", "뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) { //필터 없음
			System.out.println("검색 결과를 정렬 없이 출력합니다.");
			result.nextPage = MenuNameManager.검색결과보기;
			StartApplication.howToSort = "none";
		} else if(result.userMenuSelect.equals("2")) { //가격순정렬
			System.out.println("검색 결과를 가격순으로 정렬하여 출력합니다.");
			result.nextPage = MenuNameManager.검색결과보기;
			StartApplication.howToSort = "price";
		} else if(result.userMenuSelect.equals("3")) { //별점순정렬
			System.out.println("검색 결과를 별점순으로 정렬하여 출력합니다.");
			StartApplication.howToSort = "ratings";
			result.nextPage = MenuNameManager.검색결과보기;
			
		} else if(result.userMenuSelect.equals("4")) { //뒤로가기
			result.nextPage = MenuNameManager.호텔검색;
			
		} else { //입력 잘못된 경우
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			result.nextPage = MenuNameManager.검색결과정렬선택;
		}
		return result;
	}
	
	/**
	 * 검색 결과 보기
	 * @return
	 */
	public PageResult hotelSearchResultMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.menuDetail("리뷰 상세보기", "예약 진행","뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		if (result.userMenuSelect.equals("1")) {
			result.nextPage = MenuNameManager.검색중리뷰상세보기;
			
		} else if (result.userMenuSelect.equals("2")) {
			result.nextPage = MenuNameManager.예약진행;
			
		} else if (result.userMenuSelect.equals("3")) {
			result.nextPage = MenuNameManager.검색결과정렬선택;
			
		}else { //입력 잘못된 경우
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			result.nextPage = MenuNameManager.검색결과메뉴보기;
		}
		
		return result;
	}
	
	/**
	 * 예약 진행 메뉴
	 * @return
	 */
	public PageResult reservationResultMenu () {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		UI.menuDetail("예약 신청", "뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		if (result.userMenuSelect.equals("1")) {
			result.nextPage = MenuNameManager.예약신청완료;
		} else if (result.userMenuSelect.equals("2")) { //호텔검색메뉴로 이동
			result.nextPage = MenuNameManager.호텔검색;
		} else {
			result.nextPage = MenuNameManager.예약진행메뉴보기;
		}
		
		return result;
	}
	
	
	/**
	 * 마이페이지 메뉴
	 * @return
	 */
	public PageResult myPageMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.title("마이페이지");
		UI.menuDetail("예약 현황 확인","회원정보 수정","방문 기록","뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) { //예약현황확인
			result.nextPage = MenuNameManager.일반회원_예약현황확인;
			
		} else if(result.userMenuSelect.equals("2")) { //회원정보수정
			result.nextPage = MenuNameManager.일반회원_정보수정;
			
		} else if(result.userMenuSelect.equals("3")) { //방문기록
			result.nextPage = MenuNameManager.일반회원_방문기록;
			
		} else if(result.userMenuSelect.equals("4")) { //뒤로가기
			result.nextPage = MenuNameManager.일반회원메뉴;
			
		} else { //입력 잘못된 경우
			result.nextPage = MenuNameManager.일반회원_마이페이지;
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 예약 현황 확인 메뉴
	 * @return
	 */
	public PageResult reservationCheckMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.menuDetail("예약 일정 변경", "예약 취소", "뒤로가기");
		
		result.userMenuSelect = scan.nextLine();
		
		if (result.userMenuSelect.equals("1")) {
			result.nextPage = MenuNameManager.일반회원_예약일정변경;
			
		} else if (result.userMenuSelect.equals("2")) {
			result.nextPage = MenuNameManager.일반회원_예약취소;
			
		} else if (result.userMenuSelect.equals("3")) { //뒤로가기
			result.nextPage = MenuNameManager.일반회원_마이페이지;
			
		} else { //메뉴 입력 잘못됐을경우
			result.nextPage = MenuNameManager.일반회원_예약현황확인;
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 예약 일정 변경 신청 메뉴
	 * @return
	 */
	public PageResult sendEditRequestMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.menuDetail("뒤로 가기", "프로그램 종료");
		
		result.userMenuSelect = scan.nextLine();
		
		while (true) {
			if (result.userMenuSelect.equals("1")) {
				result.nextPage = MenuNameManager.일반회원_예약현황확인;
				break;
			} else if (result.userMenuSelect.equals("2")) {
				result.nextPage = MenuNameManager.프로그램_종료;
				break;
			} else {
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			}
		}
		return result;
	}
	
	/**
	 * 예약 취소 신청 메뉴
	 * @return
	 */
	public PageResult sendCancelRequestMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.menuDetail("뒤로 가기", "프로그램 종료");
		
		result.userMenuSelect = scan.nextLine();
		
		while (true) {
			if (result.userMenuSelect.equals("1")) {
				result.nextPage = MenuNameManager.일반회원_예약현황확인;
				break;
			} else if (result.userMenuSelect.equals("2")) {
				result.nextPage = MenuNameManager.프로그램_종료;
				break;
			} else {
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			}
		}
		return result;
	}
	
	/**
	 * 방문 기록 메뉴 - 방문 기록이 있을 경우
	 * @return
	 */
	public PageResult showUserHistoryMenu() {
		
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
//		UI.title("방문 기록");
		System.out.println();
		UI.menuDetail("리뷰 작성하기", "작성한 리뷰 보기", "뒤로 가기");

		result.userMenuSelect = scan.nextLine();
		//FIXME 방문기록 여부도 조건을 넣자
		if(result.userMenuSelect.equals("1")) { //체크아웃여부체크
			result.nextPage = MenuNameManager.일반회원_체크아웃여부체크;
			
		} else if(result.userMenuSelect.equals("2")) { //작성한리뷰보기
			result.nextPage = MenuNameManager.일반회원_작성한리뷰보기;
			
		} else if(result.userMenuSelect.equals("3")) { //뒤로가기
			result.nextPage = MenuNameManager.일반회원_마이페이지;
			
		} else { //입력 잘못된 경우
			result.nextPage = MenuNameManager.일반회원_방문기록;
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 방문 기록 메뉴 - 방문 기록이 없을 경우
	 * @return
	 */
	public PageResult showUserHistoryMenu_NoHistory() {
		
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
//		UI.title("방문 기록");
		System.out.println();
		UI.menuDetail("작성한 리뷰 보기", "뒤로 가기");

		result.userMenuSelect = scan.nextLine();
		
//		if(result.userMenuSelect.equals("1")) { //체크아웃여부체크
//			result.nextPage = MenuNameManager.일반회원_체크아웃여부체크;
//			
//		} else
		if(result.userMenuSelect.equals("1")) { //작성한리뷰보기
			result.nextPage = MenuNameManager.일반회원_작성한리뷰보기;
			
		} else if(result.userMenuSelect.equals("2")) { //뒤로가기
			result.nextPage = MenuNameManager.일반회원_마이페이지;
			
		} else { //입력 잘못된 경우
			result.nextPage = MenuNameManager.일반회원_방문기록;
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
		
		return result;
	}
	
	/**
	 * 체크아웃 여부 체크 메뉴
	 * @return
	 */
	public PageResult checkOutMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.title("체크아웃 확인");
		System.out.println("호텔을 체크아웃 하셨습니까?");
		UI.menuDetail("예", "아니오", "뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) { //리뷰작성하기
			result.nextPage = MenuNameManager.일반회원_리뷰작성하기;
			
		} else if(result.userMenuSelect.equals("2")) { //리뷰작성불가 메세지 출력 및 뒤로가기
			System.out.println("체크아웃 하지 않은 호텔은 리뷰를 작성할 수 없습니다.\n방문 기록 메뉴로 이동합니다.");
			result.nextPage = MenuNameManager.일반회원_방문기록;
			
		} else if(result.userMenuSelect.equals("3")) { //뒤로가기
			result.nextPage = MenuNameManager.일반회원_방문기록;
			
		} else { //입력 잘못된 경우
			result.nextPage = MenuNameManager.일반회원_체크아웃여부체크;
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
		}
		
		return result;
	}
	
	
	/**
	 * 리뷰 작성하기 메뉴
	 * @return
	 */
	public PageResult writeReviewMenu() {

		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		boolean loop = true;
		UI.menuDetail("뒤로 가기", "프로그램 종료");
		
		while (loop) {
			result.userMenuSelect = scan.nextLine();
			if(result.userMenuSelect.equals("1")) { //뒤로가기
				result.nextPage = MenuNameManager.일반회원_방문기록;
				loop = false;
			} else if(result.userMenuSelect.equals("2")) { //프로그램종료
				System.out.println("프로그램을 종료합니다.");
				result.nextPage = MenuNameManager.프로그램_종료;
				loop = false;
			} else { //입력 잘못된 경우
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			}
		}
		
		return result;
	}
	
	/**
	 * 작성한 리뷰 보기
	 * @return
	 */
	public PageResult showUserReviewMenu() { //작성한 리뷰 보기

		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
//		UI.title("작성한 리뷰 보기");
		boolean loop = true;
		UI.menuDetail("뒤로 가기", "프로그램 종료");
		
		while (loop) {
			result.userMenuSelect = scan.nextLine();
			if(result.userMenuSelect.equals("1")) { //뒤로가기
				result.nextPage = MenuNameManager.일반회원_방문기록;
				loop = false;
			} else if(result.userMenuSelect.equals("2")) { //프로그램종료
				System.out.println("프로그램을 종료합니다.");
				result.nextPage = MenuNameManager.프로그램_종료;
				loop = false;
			} else { //입력 잘못된 경우
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			}
		}
		
		return result;
	}
	
}
