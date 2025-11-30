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
 *  기업회원 로그인 관련 클래스
 */
public class BusinessUserLogin implements LoginManager{

	
	/**
	 * 고객정보 로드해오는 메서드
	 */
	@Override
	public boolean userInfoLoad(String id, String password) {
		try (BufferedReader reader = new BufferedReader(new FileReader(com.test.project.file.FileManager.BUSINESSUSER))) { 

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
			System.out.println("유저 정보 불러오기 실패");
		}
		

		return false;

	}
	
	/**
	 * 고객에게 아이디 비밀번호를 입력받아 로그인하는 메서드
	 */
	@Override
	public PageResult login() {
		PageResult result = new PageResult();
		
		Scanner scan = InputUtil.SCANNER;
		UI.title("기업회원 로그인");
		System.out.println("아이디, 비밀번호를 입력하세요.\n");

		while (true) {

			System.out.print("아이디 : ");
			String id = scan.nextLine();
			System.out.print("비밀번호 : ");
			String password = scan.nextLine();

			if (userInfoLoad(id, password)) {
				System.out.println();
				System.out.println("환영합니다. " +"기업회원 " + id + "님.");
				
				result.nextPage = MenuNameManager.기업회원메뉴;
				result.id = id;
				
				break;
			} else {
				System.out.println("잘못된 아이디 또는 비밀번호입니다.\n");
			}

		}
		return result;
	}

	
	/**
	 * 로그아웃 하는 메서드
	 */
	@Override
	public PageResult logOut() {
//	public logOut() {
		PageResult pageResult = new PageResult();
		
//		String nextPage = "";
		
		
		if(pageResult.id != null) {
			System.out.println();
			System.out.println(pageResult.id+"님이 로그아웃 되었습니다.");
			pageResult.id = null;
			
			pageResult.nextPage = MenuNameManager.시작화면;
			
			
		} else {
			System.out.println("현재 로그인된 계정이 없습니다.");
			
		}
		return pageResult;
		
		

	}
	

}
