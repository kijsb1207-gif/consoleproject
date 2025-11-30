package com.test.project.service.review;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.test.project.StartApplication;
import com.test.project.data.HotelReviews;
import com.test.project.file.HotelReviewsData;
import com.test.project.menu.GeneralUserMenu;
import com.test.project.service.interfaces.ShowReviewManager;
import com.test.project.ui.UI;
import com.test.project.util.PageResult;
/**
 * 사용자 아이디로 작성한 리뷰를 출력하는 클래스
 * 
 * 사용자가 작성한 리뷰를 조회하고 출력하는 기능을 제공한다.
 */
public class ShowReview implements ShowReviewManager{
	 
	//사용자 아이디로 작성한 리뷰 출력
	/**
	 * 현재 로그인한 사용자의 아이디로 작성한 리뷰를 출력한다.
	 * 
	 * @return PageResult 객체로 다음 페이지 정보를 포함한다.
	 */
	public PageResult showUserReview() {
		PageResult result = new PageResult();
		GeneralUserMenu generalUserMenu = new GeneralUserMenu();
		String userId = StartApplication.nowLoginID;

    	List<HotelReviews> filteredReviews = new ArrayList<HotelReviews>();
        List<HotelReviews> reviews = HotelReviewsData.hotelReviewList;
    	
		for (HotelReviews review : reviews) {
			if (review.getReviewerId().equals(userId)) {
				filteredReviews.add(review);
			}
		}
		
		UI.title("작성한 리뷰 목록");
		UI.chartTitle("[별점]\t[리뷰 내용]\t\t[작성 날짜]\t[호텔 명]");
		
		
		filteredReviews.sort((HotelReviews o1, HotelReviews o2) -> o2.getReviewDate().compareTo(o1.getReviewDate()));
		if(filteredReviews.isEmpty()) {
			System.out.println("리뷰가 없습니다.");
		} else {
			for (HotelReviews review : filteredReviews) {
				
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = sdf.format(review.getReviewDate().getTime());
					//FIXME 표 예쁘게 출력되는거... 수정하면 좋을듯
                    System.out.printf(" %-7d%-20s%-15s%-40s\n"
                                , review.getStarRating()
                                , review.getReviewText()
                                , formattedDate
                                , review.getHotelName());
                    
                       
				} catch (IllegalArgumentException e) {
					System.out.println("ShowReview.showUserReview");
				}
			}
		}
		UI.singleLine2(75);
		System.out.println();
		
		
		result = generalUserMenu.showUserReviewMenu();
		return result;
		
	}
}