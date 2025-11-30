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

import com.test.project.data.HotelReviews;
/**
 * 호텔 리뷰 데이터를 관리하는 클래스
 * 
 * 호텔 리뷰 데이터를 파일에서 읽어와 ArrayList에 저장한다.
 * 새로운 리뷰를 파일에 저장하는 기능을 제공한다.
 */
public class HotelReviewsData {
	
	public static ArrayList<HotelReviews> hotelReviewList;
	static {
		hotelReviewList = new ArrayList<HotelReviews>();
	}
	
	/**
	 * 호텔리뷰.txt를 읽어와 arraylist에 복사함
	 */
	public static void hotelReviewDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.HOTELREVIEWS));

			String line = null;
			while ((line = reader.readLine()) != null) {
				//별점■리뷰 내용■작성날짜■작성자■호텔명
				//1■응대가 너무 느려요.■2024-08-08■kkk25■주식회사 신세계센트럴 오노마 호텔
				//int■String■String■String■String
				String[] temp = line.split("■");
				
				//String을 Calendar 타입으로 변환하기 위한 과정
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(temp[2]);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				HotelReviews hotelReviews = new HotelReviews(Integer.parseInt(temp[0])
									, temp[1], calendar, temp[3], temp[4]);
				hotelReviewList.add(hotelReviews);
			}
			//System.out.println(hotelReviewList);
			reader.close();
			
			
			
		} catch (Exception e) {
			// 
			System.out.println("HotelReviewsData.hotelReviewDataLoad");
			e.printStackTrace();
		}
	}
	
	/**
	 * 호텔 리뷰 데이터를 파일에 저장하는 메서드
	 * @param review 저장할 호텔 리뷰 객체
	 */
	public static void hotelReviewDataSave(HotelReviews review) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.HOTELREVIEWS));
//			FileWriter writer = new FileWriter (FileManager.HOTELREVIEWS, true);
//			for (HotelReviews r : hotelReviewList) {
			writer.write(String.format("%d■%s■%tF■%s■%s", 
					review.getStarRating(), review.getReviewText(), review.getReviewDate(), review.getReviewerId(), review.getHotelName()));
			writer.newLine();

			writer.close();
			
			System.out.println("\n리뷰를 정상적으로 저장했습니다.");
			
		} catch (IOException e) {
			System.out.println("리뷰 저장 실패");
		}
	}
}
