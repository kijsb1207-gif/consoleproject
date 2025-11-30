package com.test.project.service.userhistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.test.project.StartApplication;
import com.test.project.data.VisitHistory;
import com.test.project.file.VisitHistoryData;
import com.test.project.menu.GeneralUserMenu;
import com.test.project.ui.UI;
import com.test.project.util.PageResult;
/**
 * 현재 로그인한 사용자의 방문기록을 출력하기 위한 클래스.
 * 
 * 사용자가 호텔을 예약하고 방문한 기록을 관리한다.
 */
public class UserHistory {
	
	/**
	 * 현재 로그인한 사용자의 방문 기록을 출력한다.
	 * 
	 * 사용자가 예약한 호텔의 방문 기록을 불러와서 출력한다.
	 */
	public PageResult showUserHistory() {
    	String generalUserLogin = StartApplication.nowLoginID;	//현재 로그인한 아이디
    	GeneralUserMenu generalUserMenu = new GeneralUserMenu();
    	PageResult result = new PageResult();
    	
    	//VisitHistoryData.visitHistoryDataLoad(); //프로그램 시작 때 한번에 불러와서 여기서는 생략
    	
        List<VisitHistory> visitHistorys = VisitHistoryData.visitHistoryList;
        List<VisitHistory> sortedHistory = new ArrayList<VisitHistory>(); //방문 데이터 가져오기 위한 리스트
        
        for (VisitHistory visitHistory : visitHistorys) { //현재 로그인한 아이디의 방문기록만을 저장
			if (visitHistory.getGeneralUserId().equals(generalUserLogin)) {
				sortedHistory.add(visitHistory);
			}
        }
        
        UI.title("방문 기록");
		
		if (sortedHistory.isEmpty()) {
			System.out.println("방문 기록이 없습니다.");
			result = generalUserMenu.showUserHistoryMenu_NoHistory();
		} else {
			printVisitHistory(sortedHistory);
			result = generalUserMenu.showUserHistoryMenu();
		}
		
		
		return result;
	}
	/**
	 * 정렬 및 출력
	 * @param sortedHistory 방문 기록 리스트
	 */
	private void printVisitHistory(List<VisitHistory> sortedHistory) {
		sortedHistory.sort((VisitHistory o1, VisitHistory o2) -> o1.getReservationDate().compareTo(o2.getReservationDate()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UI.chartTitle(String.format("%-9s\t%-12s%-30s", "[예약 번호]","[예약 날짜]","[호텔명]"));
		
		for (VisitHistory visitHistory : sortedHistory) {
			try {
				String reservationDate = sdf.format(visitHistory.getReservationDate().getTime());
				System.out.printf(" %-9s\t%-12s\t%-30s\n"
						, visitHistory.getRoomSerial()
						, reservationDate
						, visitHistory.getHotelName());
				//UI.singleLine2(75);
				
						
			} catch (Exception e) {
				// 
				System.out.println("UserHistory.showUserHistory");
				e.printStackTrace();
			}
		}
		UI.singleLine2(75);
	}
	
	/**
	 * 호텔명 잘못 입력했을 경우 출력하지 않기 위한 과정
	 * @param userId
	 * @return 방문한 호텔 리스트
	 */
	public List<String> getVisitedHotels(String userId) {
        List<String> visitedHotels = new ArrayList<>();
        VisitHistoryData.visitHistoryDataLoad();
        List<VisitHistory> visitHistorys = VisitHistoryData.visitHistoryList;
        
        for (VisitHistory visitHistory : visitHistorys) {
            if (visitHistory.getGeneralUserId().equals(userId)) {
                visitedHotels.add(visitHistory.getHotelName());
            }
        }
        return visitedHotels;
    }
    	
}
