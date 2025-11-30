package com.test.project.data;

import java.util.Calendar;

/**
 * 리뷰에 관련된 클래스
 * 생성자, getter/setter, toString() 오버라이딩 메서드 포함
 */
public class HotelReviews {
	private int starRating;
	private String reviewText;
	private Calendar reviewDate;
	private String reviewerId;
	private String hotelName;
	
	/**
	 * HotelReviews 클래스의 생성자
	 * @param starRating 별점
	 * @param reviewText 리뷰내용
	 * @param reviewDate 리뷰작성날짜
	 * @param reviewerId 리뷰작성자ID(일반회원)
	 * @param hotelName 호텔명
	 */
	public HotelReviews(int starRating, String reviewText, Calendar reviewDate, String reviewerId, String hotelName) {
		this.starRating = starRating;
		this.reviewText = reviewText;
		this.reviewDate = reviewDate;
		this.reviewerId = reviewerId;
		this.hotelName = hotelName;
	}
	
	/**
	 * 별점에 대한 getter
	 * @return starRating 별점
	 */
	public int getStarRating() {
		return starRating;
	}
	
	/**
	 * 별점에 대한 setter
	 * @param starRating 별점
	 */
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	
	/**
	 * 리뷰내용에 대한 getter
	 * @return reviewText 리뷰내용
	 */
	public String getReviewText() {
		return reviewText;
	}
	
	/**
	 * 리뷰내용에 대한 setter
	 * @param reviewText 리뷰내용
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	/**
	 * 리뷰작성날짜에 대한 getter
	 * @return reviewDate 리뷰작성날짜
	 */
	public Calendar getReviewDate() {
		return reviewDate;
	}
	
	/**
	 * 리뷰작성날짜에 대한 setter
	 * @param reviewDate 리뷰작성날짜
	 */
	public void setReviewDate(Calendar reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	/**
	 * 리뷰작성자에 대한 getter
	 * @return reviewerId 리뷰작성자ID(일반회원)
	 */
	public String getReviewerId() {
		return reviewerId;
	}
	
	/**
	 * 리뷰작성자에 대한 setter
	 * @param reviewerId 리뷰작성자ID(일반회원)
	 */
	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}
	
	/**
	 * 호텔명에 대한 getter
	 * @return hotelName 호텔명
	 */
	public String getHotelName() {
		return hotelName;
	}
	
	/**
	 * 호텔명에 대한 setter
	 * @param hotelName 호텔명
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	/**
	 * 리뷰에 대한 toString() 오버라이딩
	 */
	@Override
	public String toString() {
		return "HotelReviews [별점=" + starRating + ", 리뷰내용=" + reviewText + ", 리뷰작성날짜=" + reviewDate
				+ ", 작성자ID=" + reviewerId + ", 호텔명=" + hotelName + "]\n";
	}
	
}
