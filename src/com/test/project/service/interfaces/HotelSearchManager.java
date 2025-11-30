package com.test.project.service.interfaces;

import java.util.List;

import com.test.project.data.Hotels;
import com.test.project.service.hotelsearch.SearchKeyword;
import com.test.project.util.PageResult;

public interface HotelSearchManager {
	PageResult getUserInput();							//사용자 입력 받기
	List<Hotels> checkEmptyRoom(SearchKeyword keyword); //검색어에 맞는 호텔을 컬렉션에 저장
	PageResult getUserHotelName();						//사용자로부터 상세히 볼 호텔명 입력받기
	//PageResult sorting();
	PageResult getResultTable();
	PageResult makeReservation();
	
}