package com.test.project.data;

/**
 * 호텔에 관련된 클래스
 * 생성자, getter/setter, toString() 오버라이딩 메서드 포함
 */
public class Hotels {
	private String hotelName;		//호텔명
	private String addressCity;		//광역시
	private String addressCounty; 	//시군구
	private int guestCount;		//숙박인원
	private int price;				//1박 가격
	private String businessUserID;	//기업회원 ID
	
	/**
	 * Hotel 클래스의 생성자
	 * @param hotelName 호텔명
	 * @param addressCity 광역시
	 * @param addressCounty 시군구
	 * @param guestCount 숙박인원
	 * @param price 1박 가격
	 * @param businessUserID 기업회원 ID
	 */
	public Hotels(String hotelName, String addressCity, String addressCounty, int guestCount, int price,
			String businessUserID) {
		this.hotelName = hotelName;
		this.addressCity = addressCity;
		this.addressCounty = addressCounty;
		this.guestCount = guestCount;
		this.price = price;
		this.businessUserID = businessUserID;
	}
	
	/**
	 * 호텔명에 대한 getter
	 * @return hotelName 호텔명
	 */
	public String getHotelName() {
		return hotelName;
	}
	/**
	 * 호텔명에 대한 setter
	 * @param hotelName 호텔명
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	/**
	 * 광역시에 대한 getter
	 * @return addressCity 광역시
	 */
	public String getAddressCity() {
		return addressCity;
	}
	/**
	 * 광역시에 대한 setter
	 * @param addressCity 광역시
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	/**
	 * 시군구에 대한 getter
	 * @return addressCounty 시군구
	 */
	public String getAddressCounty() {
		return addressCounty;
	}
	/**
	 * 시군구에 대한 setter
	 * @param addressCounty 시군구
	 */
	public void setAddressCounty(String addressCounty) {
		this.addressCounty = addressCounty;
	}
	/**
	 * 숙박인원에 대한 getter
	 * @return guestCount 숙박인원
	 */
	public int getGuestCount() {
		return guestCount;
	}
	/**
	 * 숙박인원에 대한 setter
	 * @param guestCount 숙박인원
	 */
	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	/**
	 * 1박 가격에 대한 getter
	 * @return price 1박 가격
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * 1박 가격에 대한 setter
	 * @param price 1박 가격
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * 기업회원 ID에 대한 getter
	 * @return businessUserID 기업회원 ID
	 */
	public String getBusinessUserID() {
		return businessUserID;
	}
	/**
	 * 기업회원 ID에 대한 setter
	 * @param businessUserID 기업회원 ID
	 */
	public void setBusinessUserID(String businessUserID) {
		this.businessUserID = businessUserID;
	}

	/**
	 * Hotels 클래스에 대한 toString 오버라이딩
	 */
	@Override
	public String toString() {
		return "Hotels [호텔명=" + hotelName + ", 광역시=" + addressCity + ", 시군구=" + addressCounty
				+ ", 숙박인원=" + guestCount + ", 가격=" + price + ", 기업회원ID=" + businessUserID + "]\n";
	}
	
	
	
}
