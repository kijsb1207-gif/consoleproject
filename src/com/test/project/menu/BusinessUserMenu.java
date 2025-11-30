package com.test.project.menu;

import java.util.Scanner;

import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

/**
 * 기업회원으로 로그인 시 선택 가능한 메뉴들을 구현한 클래스
 */
public class BusinessUserMenu {
	
	/**
	 * 기업회원 메뉴 1.예약관리~4.로그아웃
	 * @return PageResult result
	 */
	public PageResult businessUserMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		UI.title("기업회원 메뉴");
		UI.menuDetail("예약 관리", "호텔 관리", "회원정보 수정", "로그아웃");
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) { //예약관리
			result.nextPage = MenuNameManager.기업회원_예약관리;
			
		} else if(result.userMenuSelect.equals("2")) { //호텔관리 
			result.nextPage = MenuNameManager.기업회원_호텔관리;
			
		} else if(result.userMenuSelect.equals("3")) { //회원정보수정 
			result.nextPage = MenuNameManager.기업회원_정보수정;
			
		} else if(result.userMenuSelect.equals("4")) { //로그아웃
			result.nextPage = MenuNameManager.시작화면;
			
		} else { //입력 잘못된 경우
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			result.nextPage = MenuNameManager.기업회원메뉴;
		}
		return result;
	}
	/**
	 * 예약관리메뉴
	 * @return PageResult result
	 */
	public PageResult reservationManageMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		UI.title("예약 관리 메뉴");
		UI.menuDetail("예약 승인", "예약 수정", "예약 취소", "뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) { //예약승인
			result.nextPage = MenuNameManager.기업회원_예약승인;
			
		} else if(result.userMenuSelect.equals("2")) { //예약수정 
			result.nextPage = MenuNameManager.기업회원_예약수정;
			
		} else if(result.userMenuSelect.equals("3")) { //예약취소 
			result.nextPage = MenuNameManager.기업회원_예약취소;
			
		} else if(result.userMenuSelect.equals("4")) { //뒤로가기
			result.nextPage = MenuNameManager.기업회원메뉴;
			
		} else { //입력 잘못된 경우
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			result.nextPage = MenuNameManager.기업회원_예약관리;
		}
		return result;
	}
	/**
	 * 예약승인메뉴
	 * @return
	 */
	public PageResult reservationApprovalMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		
		boolean loop = true;
		
		UI.menuDetail("예약 일괄 승인", "예약 개별 승인/거절", "뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		while (loop) {
			if(result.userMenuSelect.equals("1")) { //예약 일괄 승인
				result.nextPage = MenuNameManager.기업회원_예약일괄승인;
				loop = false;
			} else if(result.userMenuSelect.equals("2")) { //예약개별승인 
				result.nextPage = MenuNameManager.기업회원_예약개별승인;
				loop = false;
			} else if(result.userMenuSelect.equals("3")) { //뒤로가기
				result.nextPage = MenuNameManager.기업회원_예약관리;
				loop = false;
			} else { //입력 잘못된 경우
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			}
		}
		
		return result;
	}
	
	/**
	 * 호텔관리메뉴
	 * @return
	 */
	public PageResult hotelManageMenu() {
		PageResult result = new PageResult();
		Scanner scan = InputUtil.SCANNER;
		UI.title("호텔 관리 메뉴");
		UI.menuDetail("등록한 호텔, 객실 보기", "호텔 정보 등록", "호텔 정보 수정", "호텔 정보 삭제", "뒤로 가기");
		result.userMenuSelect = scan.nextLine();
		
		if(result.userMenuSelect.equals("1")) { //등록한 호텔, 객실보기
			result.nextPage = MenuNameManager.기업회원_등록호텔객실보기;
			
		} else if(result.userMenuSelect.equals("2")) { //호텔정보등록
			result.nextPage = MenuNameManager.기업회원_호텔정보등록;
			
		} else if(result.userMenuSelect.equals("3")) { //호텔정보수정
			result.nextPage = MenuNameManager.기업회원_호텔정보수정;
			
		} else if(result.userMenuSelect.equals("4")) { //호텔정보삭제
			result.nextPage = MenuNameManager.기업회원_호텔정보삭제;
			
		} else if(result.userMenuSelect.equals("5")) { //뒤로가기
			result.nextPage = MenuNameManager.기업회원메뉴;
		} else { //입력 잘못된 경우
			System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			result.nextPage = MenuNameManager.기업회원_호텔관리;
		}
		
		
		return result;
	}
}
