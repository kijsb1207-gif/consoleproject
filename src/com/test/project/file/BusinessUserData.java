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
import com.test.project.service.signin.BusinessUser;
import com.test.project.util.PageResult;
/**
 * 기업 회원 데이터를 관리하는 클래스
 */
public class BusinessUserData {
	/**
	 * 기업회원.txt를 불러와 저장할 Arraylist
	 */
	public static ArrayList<User> businessUserList;
	static {
		businessUserList = new ArrayList<User>();
	}
	/**
	 * 기업회원.txt를 불러와 ArrayList에 저장
	 */
	public static void businessUserDataLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FileManager.BUSINESSUSER));
			String line = null;
			while ((line = reader.readLine())!=null) {
				//kae48■5474iw■오우아■01050093338■business
				String[] temp = line.split("■");
				User businessUser = new BusinessUser(temp[0], temp[1], temp[2], temp[3], temp[4]);
				businessUserList.add(businessUser);
			}
			//System.out.println(businessUserList);
			reader.close();
			
		} catch (Exception e) {
			System.out.println("BusinessUserData.businessUserDataLoad");
			e.printStackTrace();
		}
	}
	/**
	 * 기업 회원 정보를 파일에 저장
	 * @param BusinessUserList 저장할 기업 회원 목록
	 */
	public static void saveUserData(List<BusinessUser> BusinessUserList) {

	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.BUSINESSUSER,true));
		
		for(BusinessUser list : BusinessUserList) {
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
	 * 기업 회원 가입 및 정보 저장
	 * @return 이동할 페이지
	 */
	public static PageResult registerAndSaveUserData() {
		PageResult result = new PageResult();
		
		BusinessUser BusinessUserList = new BusinessUser("", "", "", "", "");
		BusinessUserList.AccountRegister();
		List<BusinessUser> tempList = new ArrayList<BusinessUser>();
		tempList.add(BusinessUserList);
		saveUserData(tempList);
		
		result.nextPage = MenuNameManager.시작화면;
		return result;
	}
}



