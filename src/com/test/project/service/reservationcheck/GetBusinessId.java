package com.test.project.service.reservationcheck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.test.project.file.FileManager;

/**
 * 호텔 정보를 기반으로 기업 ID를 조회하는 클래스
 */
public class GetBusinessId {
	/**
	 * 객실 일련번호로 호텔 이름을 조회
	 * @param roomid 객실 일련번호
	 * @return 호텔 이름
	 */
	public static String getHotelNameByRoomId(String roomid) {
		try (BufferedReader br = new BufferedReader(new FileReader(FileManager.ROOMS))) {
			String line;
			while((line = br.readLine())!=null) {
				String [] token = line.split("■");
				if(token[0].equals(roomid)) {
					return token[3];
				}		
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
		return null;
	}
	
	/**
	 * 호텔 이름으로 기업 ID를 조회
	 * @param hotelname 호텔 이름
	 * @return 기업 ID
	 */
	public static String getBusinessIdByHotelName(String hotelname) {
		try (BufferedReader br = new BufferedReader(new FileReader(FileManager.HOTELS))) {
			String line;
			while((line = br.readLine())!=null) {
				String [] token = line.split("■");
				if(token[0].equals(hotelname)) {
					return token[5];
				}
				
			}
		
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	
		return null;
	}
	
	/**
	 * 객실 ID로 기업 ID를 조회
	 * @param roomId 객실 ID
	 * @return 기업 ID
	 */
	public static String getBusinessID(String roomId) {
		String hotelName = getHotelNameByRoomId(roomId);
		String businessId = getBusinessIdByHotelName(hotelName);
		
		return businessId;
		
		
	}


}
