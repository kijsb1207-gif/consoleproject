package com.test.project.service.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.test.project.data.HotelReviews;
import com.test.project.util.PageResult;

public interface ReviewDetailManager {
	
	static List<HotelReviews> reviews = new ArrayList<>();
	
	PageResult showReviewDetail();
}
