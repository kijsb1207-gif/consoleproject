package com.test.project.service.hotelsearch;

import java.util.Calendar;
/**
 * 사용자가 입력한 검색어를 저장하기 위한 클래스
 */
public class SearchKeyword {
	private String userhotelName;
	private String userAddressCity;
	private String userAddressCounty;
	private Calendar userStartDate;
	private Calendar userEndDate;
	private int userGuestCount;
	
	
	//생성자
	public SearchKeyword(String userhotelName, String userAddressCity, String userAddressCounty, Calendar userStartDate, Calendar userEndDate,
			int userGuestCount) {
		this.userhotelName = userhotelName;
		this.userAddressCity = userAddressCity;
		this.userAddressCounty = userAddressCounty;
		this.userStartDate = userStartDate;
		this.userEndDate = userEndDate;
		this.userGuestCount = userGuestCount;
	}

	//getter, setter
	public String getUserhotelName() {
		return userhotelName;
	}

	public void setUserhotelName(String userhotelName) {
		this.userhotelName = userhotelName;
	}
	
	public String getUserAddressCity() {
		return userAddressCity;
	}

	public void setUserAddressCity(String userAddressCity) {
		this.userAddressCity = userAddressCity;
	}

	public String getUserAddressCounty() {
		return userAddressCounty;
	}

	public void setUserAddressCounty(String userAddressCounty) {
		this.userAddressCounty = userAddressCounty;
	}

	public Calendar getUserStartDate() {
		return userStartDate;
	}

	public void setUserStartDate(Calendar userStartDate) {
		this.userStartDate = userStartDate;
	}

	public Calendar getUserEndDate() {
		return userEndDate;
	}

	public void setUserEndDate(Calendar userEndDate) {
		this.userEndDate = userEndDate;
	}

	public int getUserGuestCount() {
		return userGuestCount;
	}

	public void setUserGuestCount(int userGuestCount) {
		this.userGuestCount = userGuestCount;
	}

	@Override
	public String toString() {
		
		return String.format("SearchKeyword [호텔명=%s, 광역시=%s , 시군구=%s, 시작날짜=%tF, 끝날짜 = %tF, 수용인원=%s]\n"
				, userhotelName, userAddressCity, userAddressCounty, userStartDate, userEndDate , userGuestCount);
	}
	
	
}
