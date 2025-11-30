package com.test.project.service.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.test.project.menu.MenuNameManager;
import com.test.project.service.interfaces.LoginManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

/**
 * 일반회원 로그인 관련 기능을 담당하는 클래스
 */
public class GeneralUserLogin implements LoginManager {

	/**
	 * 사용자 정보를 로드하여 ID와 비밀번호의 일치 여부를 확인
	 * @param id 사용자 ID
	 * @param password 사용자 비밀번호
	 * @return 정보 일치 시 true, 불일치 시 false
	 */
	@Override
	public boolean userInfoLoad(String id, String password) {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(com.test.project.file.FileManager.
						GENERALUSER))) {
//						GENERALUSER_TEST))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] token = line.split("■");
				String Ids = token[0];
				String pass = token[1];

				if (Ids.equals(id) && pass.equals(password)) {
					return true;

				}

			}
			reader.close();

		} catch (Exception e) {
			System.out.println("유저 정보 불러오기 실패!!");
		}

		return false;

	}
	
	/**
	 * 일반회원 로그인을 처리
	 * @return 페이지 결과
	 */
	@Override
	public PageResult login() {
		PageResult result = new PageResult();

		Scanner scan = InputUtil.SCANNER;
		UI.title("일반회원 로그인");
		System.out.println("아이디, 비밀번호를 입력하세요.\n");

		while (true) {

			System.out.print("아이디 : ");
			String id = scan.nextLine();
			System.out.print("비밀번호 : ");
			String password = scan.nextLine();

			if (userInfoLoad(id, password)) {
				System.out.println();
				System.out.println("환영합니다. " + "일반회원 "+ id + "님.");
				
				result.nextPage = MenuNameManager.일반회원메뉴;
				result.id = id;
						
				break;
			} else {
				System.out.println("잘못된 아이디 또는 비밀번호입니다.\n");
			}

		}
		
		return result;
	}
	
	/**
	 * 로그아웃을 처리
	 * @return 페이지 결과
	 */
	@Override
	public PageResult logOut() { //return 값을 String으로 바꿈
		PageResult pageResult = new PageResult();
		pageResult.nextPage = MenuNameManager.시작화면;
		
//		String nextPage = ""; //nextPage 변수 선언
		
		if (pageResult.id != null) {
			System.out.println();
			System.out.println(pageResult.id + "님이 로그아웃 되었습니다.");
			pageResult.id = null;
			
//			nextPage = MenuNameManager.시작화면; //로그아웃 하면 시작메뉴로 간다는 것을 지정
			
		} else {
			System.out.println("현재 로그인된 계정이 없습니다.");
		}
		
		return pageResult;

	}

}
