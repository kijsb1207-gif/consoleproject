package com.test.project.data;

/**
 * 일반회원과 객실을 매칭하는 데에 사용할 클래스
 * 생성자, getter/setter, toString() 오버라이딩 메서드 포함
 */
public class GeneralUserRoomsMatch {
	//회원객실매칭
	private int roomSerial; 			//객실일련번호
	private String generalUserId;		//일반회원아이디
	
	/**
	 * GeneralUserRoomsMatch 클래스의 생성자
	 * @param roomSerial 객실일련번호
	 * @param generalUserId 일반회원ID
	 */
	public GeneralUserRoomsMatch(int roomSerial, String generalUserId) {
		this.roomSerial = roomSerial;
		this.generalUserId = generalUserId;
	}
	
	/**
	 * 객실일련번호에 대한 getter
	 * @return roomSerial 객실일련번호
	 */
	public int getRoomSerial() {
		return roomSerial;
	}
	
	/**
	 * 객실일련번호에 대한 setter
	 * @param roomSerial 객실일련번호
	 */
	public void setRoomSerial(int roomSerial) {
		this.roomSerial = roomSerial;
	}
	
	/**
	 * 일반회원ID에 대한 getter
	 * @return generalUserID 일반회원ID
	 */
	public String getGeneralUserId() {
		return generalUserId;
	}
	
	/**
	 * 일반회원ID에 대한 setter
	 * @param generalUserId 일반회원ID
	 */
	public void setGeneralUserId(String generalUserId) {
		this.generalUserId = generalUserId;
	}
	
	/**
	 * 회원객실매칭에 대한 toString() 오버라이딩
	 */
	@Override
	public String toString() {
		return "GeneralUserRoomsMatch [객실일련번호=" + roomSerial + ", 일반회원ID=" + generalUserId + "]\n";
	}
	
}
