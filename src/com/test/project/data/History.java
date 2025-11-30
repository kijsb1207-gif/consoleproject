package com.test.project.data;

/**
 * 회원의 방문기록에 관련된 클래스
 * 생성자, getter/setter, toString() 오버라이딩 메서드 포함
 */
public class History {
	private boolean checkOut;
	private String roomSerial;
	
	/**
	 * History 클래스의 생성자
	 * @param checkOut 체크아웃여부
	 * @param roomSerial 객실일련번호
	 */
	public History(boolean checkOut, String roomSerial) {
		this.checkOut = checkOut;
		this.roomSerial = roomSerial;
	}
	
	/**
	 * 체크아웃여부에 대한 getter
	 * @return checkOut 체크아웃여부
	 */
	public boolean isCheckOut() {
		return checkOut;
	}
	
	/**
	 * 체크아웃여부에 대한 setter
	 * @param checkOut 체크아웃여부
	 */
	public void setCheckOut(boolean checkOut) {
		this.checkOut = checkOut;
	}

	/**
	 * 객실일련번호에 대한 getter
	 * @return roomSerial 객실일련번호
	 */
	public String getRoomSerial() {
		return roomSerial;
	}
	
	/**
	 * 객실일련번호에 대한 setter
	 * @param roomSerial 객실일련번호
	 */
	public void setRoomSerial(String roomSerial) {
		this.roomSerial = roomSerial;
	}

	/**
	 * 방문기록에 대한 toString() 오버라이딩
	 */
	@Override
	public String toString() {
		return "History [checkOut=" + checkOut + ", roomSerial=" + roomSerial + "]\n";
	}
	
	
}
