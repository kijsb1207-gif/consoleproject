package com.test.project.util;

/**
 * 프로젝트 내에서 공통으로 사용할 public 변수들을 저장하는 클래스
 */
public class PageResult {
	/**
	 * 이동할 메뉴
	 */
	public String nextPage;	
	/**
	 * //사용자가 입력한 메뉴
	 */
	public String userMenuSelect; 
	
	/**
	 * 현재 로그인한 id 확인용
	 */
	public String id;
	
	/**
	 * 사용자가 선택한 검색결과 정렬
	 */
	public String sortSelect;
	
	/**
	 * 사용자가 입력한 호텔명
	 */
	public String userHotelName;
	
}
