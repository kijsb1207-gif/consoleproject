package com.test.project.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.test.project.data.Hotels;

public class HotelsData {
	
	public static ArrayList<Hotels> hotelsList;
	static {
		hotelsList = new ArrayList<Hotels>();
	}
	
	/**
	 * 호텔.txt의 데이터를 불러와 ArrayList에 저장
	 */
	public static void hotelsDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.HOTELS)); 

			String line = null;
			//reader의 내용을 hotelsList에 복사
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				//호텔명■광역시■시군구■숙박인원■1박가격■기업회원ID
				//String■String■String■int■int■String
				//가야 H 에비뉴 평화광장 호텔■세종■광주시■4■721465■mra13
				String[] temp = line.split("■");
				Hotels hotel = new Hotels(temp[0], temp[1], temp[2]
						, Integer.parseInt(temp[3])
						, Integer.parseInt(temp[4])
						, temp[5]);
				hotelsList.add(hotel);
			}
			//System.out.println(hotelsList);
			reader.close();
			
			
		} catch (Exception e) {
			// 
			System.out.println("HotelsData.hotelsDataload");
			e.printStackTrace();
		}
		
		
	}

	/**
	 * HotelsData.hotelsList에 있는 데이터를 파일에 저장합니다.
	 */
	public static void saveHotelsData(ArrayList<Hotels> hotelsList) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.HOTELS))) {
	        for (Hotels hotel : hotelsList) {
	            writer.write(String.format("%s■%s■%s■%d■%d■%s",
	                    hotel.getHotelName(),
	                    hotel.getAddressCity(),
	                    hotel.getAddressCounty(),
	                    hotel.getGuestCount(),
	                    hotel.getPrice(),
	                    hotel.getBusinessUserID()));
	            writer.newLine();
	        }
	        System.out.println("호텔 정보가 파일에 저장되었습니다.");
	    } catch (IOException e) {
	        System.out.println("호텔 정보 저장 중 오류 발생");
	        e.printStackTrace();
	    }
	}
	
}
