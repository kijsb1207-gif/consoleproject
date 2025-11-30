package com.test.project.service.review;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.test.project.StartApplication;
import com.test.project.data.HotelReviews;
import com.test.project.file.HotelReviewsData;
import com.test.project.menu.GeneralUserMenu;
import com.test.project.service.interfaces.WriteReviewManager;
import com.test.project.service.userhistory.UserHistory;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;
/**
 * 리뷰 작성 기능을 구현하는 클래스
 * 
 * 사용자가 호텔에 대한 리뷰를 작성하고 저장하는 기능을 제공한다.
 */
public class WriteReview implements WriteReviewManager{
	
	//리뷰 작성 후 리뷰.txt 파일에 저장
	/**
	 * 리뷰 작성 메서드
	 * 
	 * 사용자가 선택한 호텔에 대해 리뷰를 작성하고 저장한다.
	 * 
	 * @return PageResult 객체로 다음 페이지 정보를 포함한다.
	 */
	@Override
	public PageResult writeReview() {
		PageResult result = new PageResult();
		GeneralUserMenu pGeneralUserMenu = new GeneralUserMenu();
		UserHistory userHistory = new UserHistory();
		Scanner scan = InputUtil.SCANNER;
		boolean loop = true;
		boolean validHotel = false;
		
		String hotelName = "";
		int starRating = 0;
		String reviewText = "";
		
		
		String userId = StartApplication.nowLoginID;
//		String userId = "acg66";  // 테스트용
		UI.title("리뷰 작성하기");
		
		
		while (!validHotel) {
			System.out.print("호텔 선택(호텔명): ");
			hotelName = scan.nextLine();
			
//			
		    List<String> visitedHotels = userHistory.getVisitedHotels(userId);
		        
		    
		    if (!visitedHotels.contains(hotelName)) {
		    	System.out.println("이 호텔을 방문한 기록이 없습니다. 리뷰를 작성할 수 없습니다.");
		    	writeReview();
		    } else {
		    	validHotel = true;
            }
		}
		
		
		
		while (loop) {
			try {
				System.out.print("별점(1~5): ");
//				starRating = Integer.parseInt(scan.nextLine());
				starRating = Integer.parseInt(scan.nextLine());
				if(starRating >= 1 && starRating <= 5) {
					System.out.print("리뷰 내용: ");
					reviewText = scan.nextLine();
					loop = false;
				} else {
					System.out.println("별점은 1점부터 5점까지만 입력 가능합니다.");
				}
					
			} catch (Exception e) {
				System.out.println("별점은 숫자만 입력 가능합니다.");
			}
			
			
		}
		
		
			
		String reviewerId = StartApplication.nowLoginID;
				
		
		Calendar reviewDate = Calendar.getInstance();
		
		HotelReviews review = new HotelReviews(starRating, reviewText, reviewDate, reviewerId, hotelName);
		
		HotelReviewsData.hotelReviewList.add(review);
		HotelReviewsData.hotelReviewDataSave(review);
		

		result = pGeneralUserMenu.writeReviewMenu();
		
		return result;
		
	}
	
	
}