package com.test.project.service.review;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.project.StartApplication;
import com.test.project.data.HotelReviews;
import com.test.project.file.HotelReviewsData;
import com.test.project.menu.MenuNameManager;
import com.test.project.service.interfaces.ReviewDetailManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

/**
 * 리뷰 상세 보기 기능을 구현하는 클래스
 * 
 * 사용자가 특정 호텔의 리뷰를 상세히 조회하고 정렬할 수 있는 기능을 제공한다.
 */
public class ReviewDetail implements ReviewDetailManager{
	
	//private List<HotelReviews> reviews = new ArrayList<HotelReviews>();
	/**
	 * 필터링된 호텔 리뷰 리스트
	 * 
	 * 특정 호텔의 리뷰를 저장하는 리스트로, 검색 결과에 따라 업데이트된다.
	 */
	private List<HotelReviews> filteredReviews = new ArrayList<HotelReviews>(); //호텔 리뷰 데이터 가져오기>
	
	//검색 결과 보기 - 호텔 리뷰 보기
	/**
	 * * 호텔 리뷰 상세 보기 메서드
	 * 
	 * 사용자가 선택한 호텔의 리뷰를 상세히 조회한다.
	 * 
	 * @return PageResult 객체로 다음 페이지 정보를 포함한다.
	 */
	public PageResult showReviewDetail() {
		
		PageResult result = new PageResult();
		result.userHotelName = StartApplication.hotelName;
		//HotelSearch hotelSearch = new HotelSearch(); //getUserHotelName() 메서드를 사용하기 위해 객체 생성
		//String hotelName = hotelSearch.getUserHotelName();//호텔 검색 메서드를 실행해서 메서드의 return 값을 hotelName 변수에 저장
		
		String hotelName = result.userHotelName;	//테스트
		System.out.println("호텔명: " + hotelName); //hotelName을 출력
		
		//HotelReviewsData.hotelReviewDataLoad();
        List<HotelReviews> reviews = HotelReviewsData.hotelReviewList;
		
        //필터링
		filteredReviews.clear();
		for (HotelReviews review : reviews) {
			if (review.getHotelName().equals(hotelName)) {
				filteredReviews.add(review);
			}
		}
		
//	    double aveStarRating = 0;
//	    if (!filteredReviews.isEmpty()) {
//	        int totalStarRating = 0;
//	        for (HotelReviews review : filteredReviews) {
//	        	totalStarRating += review.getStarRating();
//	        }
//	        aveStarRating = totalStarRating / (double) filteredReviews.size();
//	    }
	    
		
		//출력
		UI.title("리뷰 상세 보기");
		UI.chartTitle("[별점]\t[리뷰 내용]\t\t[작성 날짜]");
		
		if(filteredReviews.isEmpty()) {
			System.out.println("리뷰가 없습니다.");
			result.nextPage = MenuNameManager.검색중리뷰상세보기;
		}
		
		for (HotelReviews review : filteredReviews) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = sdf.format(review.getReviewDate().getTime());
			System.out.printf(" %-7d%-18s%-15s\n"
                    , review.getStarRating()
					, review.getReviewText()
					, formattedDate);
		}
		
		System.out.println();
		UI.menuDetail("최신순 정렬", "별점순 정렬", "뒤로 가기", "프로그램 종료");
		
		result = searchReviewDetail();
		return result;
		
	}
	/**
	 * 리뷰 상세 보기에서 사용자가 선택한 옵션에 따라 리뷰를 정렬하고 출력하는 메서드
	 * 
	 * @return PageResult 객체로 다음 페이지 정보를 포함한다.
	 */
	public PageResult searchReviewDetail() {
		//리뷰 정렬, 메뉴
		PageResult result = new PageResult();
		
		boolean loop = true;
		
		while (loop){
			
			Scanner scan = InputUtil.SCANNER;
			result.userMenuSelect = scan.nextLine();
			
			//리뷰 정렬
			
			if (result.userMenuSelect.equals("1")) { //최신순 정렬
				System.out.println("\n[최신순 정렬]");
				filteredReviews.sort((HotelReviews o1, HotelReviews o2) -> o2.getReviewDate().compareTo(o1.getReviewDate()));
			} else if (result.userMenuSelect.equals("2")) {
				System.out.println("\n[별점순 정렬]");
				filteredReviews.sort((HotelReviews o1, HotelReviews o2) -> o2.getStarRating() - o1.getStarRating());
			} else if (result.userMenuSelect.equals("3")) {
				result.nextPage = MenuNameManager.검색결과보기; //뒤로가기
				break;
			} else if (result.userMenuSelect.equals("4")) {
				System.out.println("프로그램을 종료합니다.");
				result.nextPage = MenuNameManager.프로그램_종료; //종료
				break;
			} 
			else {
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요."); //동일 메뉴 다시실행
				result.nextPage = MenuNameManager.검색중리뷰상세보기;
			}
			
			UI.chartTitle("[별점]\t[리뷰 내용]\t\t[작성 날짜]");
			for (HotelReviews review : filteredReviews) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = sdf.format(review.getReviewDate().getTime());
				System.out.println(review.getStarRating()
						+ "\t" + review.getReviewText()
						+ "\t\t" + formattedDate);
			}
			
			
			
			UI.menuDetail("최신순 정렬", "별점순 정렬", "뒤로 가기", "프로그램 종료");
		}
		return result;
	}
		
		
}


