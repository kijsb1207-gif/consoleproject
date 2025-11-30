package com.test.project.data;

import java.util.Calendar;

/**
 * 예약요청에 관련된 클래스
 * 생성자, getter/setter, toString() 오버라이딩 메서드 포함
 */
public class ReservationRequest {
	
	private String generalUserRequest; //고객요청사항
	private int roomSerial; 			//방일련번호
	private Calendar requestDate;		//고객수정요청날짜
	private String nowProcess;			//고객요청 처리상태
	private String businessUserId;		//기업회원아이디
	
	/**
	 * ReservationRequest 클래스의 생성자
	 * @param generalUserRequest 고객요청사항
	 * @param roomSerial 객실일련번호
	 * @param requestDate 고객수정요청날짜
	 * @param nowProcess 고객요청 처리상태
	 * @param businessUserId 기업회원아이디
	 */
	public ReservationRequest(String generalUserRequest, int roomSerial, Calendar requestDate, String nowProcess,
			String businessUserId) {
		this.generalUserRequest = generalUserRequest;
		this.roomSerial = roomSerial;
		this.requestDate = requestDate;
		this.nowProcess = nowProcess;
		this.businessUserId = businessUserId;
	}
	
	/**
	 * 고객요청사항에 대한 getter
	 * @return generalUserRequest 고객요청사항
	 */
	public String getGeneralUserRequest() {
		return generalUserRequest;
	}
	/**
	 * 고객요청사항에 대한 setter
	 * @param generalUserRequest 고객요청사항
	 */
	public void setGeneralUserRequest(String generalUserRequest) {
		this.generalUserRequest = generalUserRequest;
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
	 * 고객수정요청날짜에 대한 getter
	 * @return requestDate 고객수정요청날짜
	 */
	public Calendar getRequestDate() {
		return requestDate;
	}
	/**
	 * 고객수정요청날짜에 대한 setter
	 * @param requestDate 고객수정요청날짜
	 */
	public void setRequestDate(Calendar requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * 고객요청 처리상태에 대한 getter
	 * @return nowProcess 고객요청 처리상태
	 */
	public String getNowProcess() {
		return nowProcess;
	}
	/**
	 * 고객요청 처리상태에 대한 setter
	 * @param nowProcess 고객요청 처리상태
	 */
	public void setNowProcess(String nowProcess) {
		this.nowProcess = nowProcess;
	}
	/**
	 * 기업회원아이디에 대한 getter
	 * @return businessUserId 기업회원아이디
	 */
	public String getBusinessUserId() {
		return businessUserId;
	}
	/**
	 * 기업회원아이디에 대한 setter
	 * @param businessUserId 기업회원아이디
	 */
	public void setBusinessUserId(String businessUserId) {
		this.businessUserId = businessUserId;
	}
	
	/**
	 * ReservationRequest 클래스에 대한 toString() 오버라이딩
	 */
	@Override
	public String toString() {
		return String.format("Rooms [고객요청사항=%s, 객실일련번호=%d , 고객수정요청날짜=%tF, 고객요청처리상태=%s, 기업회원ID=%s]\n"
				, generalUserRequest, roomSerial, requestDate, nowProcess, businessUserId);
	}
	
}
