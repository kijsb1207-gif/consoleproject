package com.test.project.util;

/**
 * 로그 출력용 PrintUtil 클래스
 */
public class PrintUtil {	
	public boolean bLog = false;

	/**
	 * 로그 출력용 메서드
	 * @param str 출력할 내용
	 */
	public void prt_log(String str) {
		if(bLog == true) {
			System.out.println("------> " + str);
		}
	}
	
	
}