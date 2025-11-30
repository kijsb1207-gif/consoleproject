package com.test.project.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.test.project.data.User;
import com.test.project.menu.MenuNameManager;
import com.test.project.service.signin.GeneralUser;
import com.test.project.util.PageResult;

/**
 * 일반 회원 데이터를 관리하는 클래스
 */
public class GeneralUserData {
	/**
	 * 일반회원.txt를 저장하기 위한 ArrayList
	 */
	public static ArrayList<User> generalUserList;
	static {
		generalUserList = new ArrayList<User>();
	}
	/**
	 * 일반회원.txt를 불러와 ArrayList에 저장
	 */
	public static void generalUserDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.GENERALUSER));
			String line = null;
			while ((line = reader.readLine())!=null) {
				//ghn58■4632pa■박유민■01096055277■general
				String[] temp = line.split("■");
				User generalUser = new GeneralUser(temp[0], temp[1], temp[2], temp[3], temp[4]);
				generalUserList.add(generalUser);
			}
			//System.out.println(generalUserList);
			reader.close();
			
		} catch (Exception e) {
			// 
			System.out.println("GeneralUserData.generalUserDataLoad");
			e.printStackTrace();
		}
	}
	
	/**
	 * 일반 회원 정보를 파일에 저장
	 * @param GeneralUserList 저장할 일반 회원 목록
	 */
	public static void saveUserdata(List<GeneralUser> GeneralUserList) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.GENERALUSER,true));
			
			for(GeneralUser list : GeneralUserList) {
				writer.newLine();
				writer.write(list.getUserId()+"■"+list.getUserPw()+"■"+list.getUserName()+"■"+list.getPhoneNumber()+"■"+list.getCategoryCode());
				writer.close();
			}
		} catch (IOException e) {
			System.out.println("저장실패");
			e.printStackTrace();
		}
		

	}
	
	/**
	 * 일반회원 회원가입시 실행할 메서드
	 * @return 이동할 페이지 지정
	 */
	public static PageResult registerAndSaveUserData() { 
		PageResult result = new PageResult();
		
		GeneralUser generalUserList = new GeneralUser("", "", "", "", "");
		generalUserList.AccountRegister();
		List<GeneralUser> tempList = new ArrayList<GeneralUser>();
		tempList.add(generalUserList);
		saveUserdata(tempList);
		
		result.nextPage = MenuNameManager.시작화면;
		return result;
	}

}
