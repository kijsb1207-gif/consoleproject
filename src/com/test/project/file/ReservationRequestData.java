package com.test.project.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.test.project.data.ReservationRequest;

public class ReservationRequestData {
	public static ArrayList<ReservationRequest> reservationRequestList;
	static {
		reservationRequestList = new ArrayList<ReservationRequest>();
	}
	
	public static void reservationRequestDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.RESERVATIONREQUEST)); 

			String line = null;
			while((line = reader.readLine()) != null) {
				//요청타입,ID,날짜,상태,사용자ID
//				고객요청사항■방일련번호■고객수정요청날짜■처리상태■비즈니스아이디
//				예약요청■1■null■대기■woaks
				String[] temp = line.split("■");
				
				//calendar로 변환하기 위한 과정
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(temp[2]);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				ReservationRequest request 
					= new ReservationRequest(temp[0], Integer.parseInt(temp[1]), calendar, temp[3], temp[4]);
				reservationRequestList.add(request);
			}
//			System.out.println(reservationRequestList);
			reader.close();
		} catch (Exception e) {
			// 
			System.out.println("ReservationRequestData.reservationRequestDataLoad");
			e.printStackTrace();
		}
		
	}
	
}
