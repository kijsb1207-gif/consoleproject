package com.test.project.data;

import java.util.Calendar;
/**
 * 객실에 대한 클래스
 * 생성자, getter/setter, toString() 오버라이딩 메서드 포함
 */
public class Rooms {
	private int roomSerial; 			//객실일련번호
	private int roomNumber; 			//객실번호(방번호)
	private Calendar availableDate; 	//예약 가능한 날짜
	private String hotelName; 			//호텔명(참조)
	private String reservationStat;		//예약상태값
	
	/**
	 * Rooms 클래스의 생성자
	 * @param roomSerial 객실일련번호
	 * @param roomNumber 객실번호(방번호)
	 * @param availableDate 예약 가능한 날짜
	 * @param hotelName 호텔명(참조)
	 * @param reservationStat 예약상태값
	 */
	public Rooms(int roomSerial, int roomNumber, Calendar availableDate, String hotelName, String reservationStat) {
		this.roomSerial = roomSerial;
		this.roomNumber = roomNumber;
		this.availableDate = availableDate;
		this.hotelName = hotelName;
		this.reservationStat = reservationStat;
	}

	/**
	 * 객실 일련번호를 반환
	 * @return 객실 일련번호
	 */
	public int getRoomSerial() {
		return roomSerial;
	}
	
	/**
	 * 객실 일련번호를 설정
	 * @param roomSerial 객실 일련번호
	 */
	public void setRoomSerial(int roomSerial) {
		this.roomSerial = roomSerial;
	}

	/**
	 * 객실 번호를 반환
	 * @return 객실 번호
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	
	/**
	 * 객실 번호를 설정
	 * @param roomNumber 객실 번호
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * 예약 가능 날짜를 반환
	 * @return 예약 가능 날짜
	 */
	public Calendar getAvailableDate() {
		return availableDate;
	}

	/**
	 * 예약 가능 날짜를 설정
	 * @param availableDate 예약 가능 날짜
	 */
	public void setAvailableDate(Calendar availableDate) {
		this.availableDate = availableDate;
	}

	/**
	 * 호텔 이름을 반환
	 * @return 호텔 이름
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * 호텔 이름을 설정
	 * @param hotelName 호텔 이름
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	/**
	 * 예약 상태를 반환
	 * @return 예약 상태
	 */
	public String getReservationStat() {
		return reservationStat;
	}

	/**
	 * 객실 정보를 문자열로 반환
	 * @return 객실 정보 문자열
	 */
	public void setReservationStat(String reservationStat) {
		this.reservationStat = reservationStat;
	}

	
	/**
	 * Rooms 클래스의 toString() 오버라이딩
	 */
	@Override
	public String toString() {
		return String.format("Rooms [객실일련번호=%d, 객실번호(방번호)=%d , 예약가능날짜=%tF, 호텔명=%s, 예약상태값=%s]\n"
							, roomSerial, roomNumber, availableDate, hotelName, reservationStat);
		
	}


	
}
