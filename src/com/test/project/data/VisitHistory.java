package com.test.project.data;

import java.util.Calendar;

public class VisitHistory {
	
	private String generalUserId;		//일반회원아이디
	private int roomSerial;				//객실일련번호
	private String hotelName;			//호텔 명
	private Calendar reservationDate;	//예약(체크인) 날짜
	
	public VisitHistory(String generalUserId, int roomSerial, String hotelName, Calendar reservationDate) {
		this.generalUserId = generalUserId;
		this.roomSerial = roomSerial;
		this.hotelName = hotelName;
		this.reservationDate = reservationDate;
	}

	public String getGeneralUserId() {
		return generalUserId;
	}

	public void setGeneralUserId(String generalUserId) {
		this.generalUserId = generalUserId;
	}

	public int getRoomSerial() {
		return roomSerial;
	}

	public void setRoomSerial(int roomSerial) {
		this.roomSerial = roomSerial;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Calendar getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Calendar reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public String toString() {
		return "History [generalUserId=" + generalUserId + ", roomSerial=" + roomSerial + ", hotelName=" + hotelName
				+ ", reservationDate=" + reservationDate + "]";
	}

	
	
}
