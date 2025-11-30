package com.test.project.data;

import com.test.project.service.interfaces.UserManager;

/**
 * 사용자 정보에 대한 추상 클래스
 * 일반 회원 및 기업 회원의 공통 속성을 정의하며, 각 회원 타입은 이 클래스를 상속하여 구현
 * 생성자, getter/setter, toString() 오버라이딩 메서드 포함
 */
public abstract class User implements UserManager {

	private String userId;
	private String userPw;
	private String userName;
	private String phoneNumber;
	private String categoryCode; // 카테고리코드, general/business
	
	/**
	 * User 클래스(abstract)의 생성자
	 * @param userId 아이디
	 * @param userPw 비밀번호
	 * @param userName 이름
	 * @param phoneNumber 전화번호
	 * @param categoryCode 회원카테고리
	 */
	public User(String userId, String userPw, String userName, String phoneNumber, String categoryCode) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.categoryCode = categoryCode;
	}
	
	/**
     * User 클래스의 기본 생성자
     * 모든 필드를 빈 문자열로 초기화한다.
     */
	public User() {
		this.userId = "";
		this.userPw = "";
		this.userName = "";
		this.phoneNumber = "";
		this.categoryCode = "";
    }

	
	
	/**
	 * 사용자 ID를 반환
	 * @return 사용자 ID
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 사용자 ID를 설정
	 * @param userId 사용자 ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 사용자 비밀번호를 반환
	 * @return 사용자 비밀번호
	 */
	public String getUserPw() {
		return userPw;
	}

	/**
	 * 사용자 비밀번호를 설정
	 * @param userPw 사용자 비밀번호
	 */
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	/**
	 * 사용자 이름을 반환
	 * @return 사용자 이름
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 사용자 이름을 설정
	 * @param userName 사용자 이름
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 사용자 전화번호를 반환
	 * @return 사용자 전화번호
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * 사용자 전화번호를 설정
	 * @param phoneNumber 사용자 전화번호
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 사용자 카테고리 코드를 반환
	 * @return 사용자 카테고리 코드
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	
	/**
	 * 사용자 카테고리 코드를 설정
	 * @param categoryCode 사용자 카테고리 코드
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * User 클래스의 toString() 오버라이딩
	 */
	@Override
	public String toString() {
		return "User [아이디=" + userId + ", 비밀번호=" + userPw + ", 이름=" + userName + ", 전화번호="
				+ phoneNumber + ", 회원코드=" + categoryCode + "]\n";
	}

}
