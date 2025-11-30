package com.test.project.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.test.project.data.VisitHistory;

/**
 * 방문내역 데이터를 관리하는 클래스
 * 
 * 방문내역.txt 파일의 데이터를 불러와 ArrayList에 저장하고, 이를 관리한다.
 */
public class VisitHistoryData {
	public static ArrayList<VisitHistory> visitHistoryList;
	static {
		visitHistoryList = new ArrayList<VisitHistory>();
	}

		/**
		 * 방문내역.txt의 데이터를 불러와 ArrayList에 저장
		 */
		public static void visitHistoryDataLoad() {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(FileManager.VISITHISTORY));

				String line = null;
				while ((line = reader.readLine()) != null) {
					
					String[] temp = line.split("■");
					
					//String을 Calendar 타입으로 변환하기 위한 과정
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Date date = sdf.parse(temp[3]);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					
					VisitHistory visithistory = new VisitHistory(
										temp[0]
										, Integer.parseInt(temp[1])
										, temp[2]
										, calendar);
					visitHistoryList.add(visithistory);
				}
				//System.out.println(visiHhistoryList);
				reader.close();
				
			} catch (Exception e) {
				// 
				System.out.println("VisitHistoryData.visitHistoryDataLoad");
				e.printStackTrace();
			}
			
		}
		
}


