package com.test.project.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.test.project.data.Rooms;

public class RoomsData {
	/**
	 * 객실.txt를 불러와 저장할 static 컬렉션
	 */
	public static ArrayList<Rooms> roomsList;
	static {
		roomsList = new ArrayList<Rooms>();
	}
	
	public static void roomsDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.ROOMS));

			String line = null;
			
			//reader의 내용을 roomsList에 복사
			while((line = reader.readLine()) != null) {
				//System.out.println(line);
				
				//1■101■2025-06-18■플레아드 블랑 & 레지던스 호텔■예약승인대기중
				//ID,객실번호,날짜,호텔명,예약상태
				//객실일련번호■객실번호(방번호)■예약가능날짜■호텔명■예약상태값
				//int■int■Calendar■String■String
				String[] temp = line.split("■");

				//String을 Calendar 타입으로 변환하기 위한 과정
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(temp[2]);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				Rooms room = new Rooms(Integer.parseInt(temp[0])
								, Integer.parseInt(temp[1])
								, calendar
								, temp[3], temp[4]);
				roomsList.add(room);
			}
			//System.out.println(roomsList);
			reader.close();
			
		} catch (Exception e) {
			// 
			System.out.println("RoomsData.roomsDataLoad");
			e.printStackTrace();
		}
	}
	

	public static void saveRoomsData() { // rooms 상태값 변경 후 저장메서드
	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.ROOMS))) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (Rooms room : roomsList) {
				String dateStr = sdf.format(room.getAvailableDate().getTime());
				String line = String.format("%d■%d■%s■%s■%s", room.getRoomSerial(), room.getRoomNumber(), dateStr,
						room.getHotelName(), room.getReservationStat());
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("객실 정보 저장 중 오류 발생");
			e.printStackTrace();
		}
	}
	
//	public static void saveRoomsData(ArrayList<Rooms> roomsList) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.ROOMS))) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            for (Rooms room : roomsList) {
//                String dateStr = sdf.format(room.getAvailableDate().getTime());
//                String line = String.format("%d■%d■%s■%s■%s", room.getRoomSerial(), room.getRoomNumber(), dateStr,
//                        room.getHotelName(), room.getReservationStat());
//                writer.write(line);
//                writer.newLine();
//            }
//            System.out.println("객실 정보가 파일에 저장되었습니다.");
//        } catch (IOException e) {
//            System.out.println("객실 정보 저장 중 오류 발생");
//            e.printStackTrace();
//        }
//    }
	
	
}
