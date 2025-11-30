package com.test.project.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.test.project.data.History;

/**
 * 방문기록 데이터를 관리하는 클래스
 * 
 * 방문기록.txt 파일의 데이터를 불러와 ArrayList에 저장하고, 이를 관리한다.
 */
public class HistoryData {
	public static ArrayList<History> historyList;
	static {
		historyList = new ArrayList<History>();
	}
	
	/**
	 * 방문기록.txt의 데이터를 불러와 ArrayList에 저장
	 */
	public static void historyDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.HISTORY));

			String line = null;
			while ((line = reader.readLine()) != null) {
				//체크아웃여부■객실번호
				//false■1
				//boolean■int
				String[] temp = line.split("■");
				History history = new History(Boolean.parseBoolean(temp[0]), temp[1]);
				historyList.add(history);
			}
			//System.out.println(historyList);
			reader.close();
			
		} catch (Exception e) {
			// 
			System.out.println("HistoryData.historyDataLoad");
			e.printStackTrace();
		}
		
	}
}
