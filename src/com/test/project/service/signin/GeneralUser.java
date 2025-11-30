package com.test.project.service.signin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.project.StartApplication;
import com.test.project.data.User;
import com.test.project.file.FileManager;
import com.test.project.menu.MenuNameManager;
import com.test.project.ui.UI;
import com.test.project.util.InputUtil;
import com.test.project.util.PageResult;

/**
 * 일반 회원 정보를 나타내는 클래스
 */
public class GeneralUser extends User {
	/**
	 * GeneralUser 클래스의 생성자
	 * @param userId 아이디
	 * @param userPw 패스워드
	 * @param userName 이름
	 * @param phoneNumber 연락처
	 * @param categoryCode 카테고리코드
	 */
	public GeneralUser(String userId, String userPw, String userName, String phoneNumber, String categoryCode) {
		super(userId, userPw, userName, phoneNumber, categoryCode);

	}
	
	/**
	 * GeneralUser 클래스의 기본 생성자
	 */
	public GeneralUser() {
		super();
	}
	
	/**
	 * 일반 회원 정보를 담을 ArrayList
	 */
	public static List<GeneralUser> GeneralUserList = new ArrayList<>();

	/**
	 * 일반회원 회원가입
	 */
	@Override
	public void AccountRegister() { // 계정 정보 저장
		setCategoryCode(UserCategory.GENERAL_CODE); // 코드정보 먼저 채우기

			Scanner scan = InputUtil.SCANNER;
			UI.title("일반회원으로 가입");

			while (true) {
				System.out.print("아이디 입력 (4~16자, 영문/숫자/언더바만 입력): ");
				String input = scan.nextLine();

				if (SignInValidator.generalCheckId(input)) {
					setUserId(input);
					break;

				} else {
					System.out.println("다시 입력해주세요.");
				}

			}

			while (true) {
				System.out.print("비밀번호 입력 (4~16자, 영문/숫자/언더바만 입력): ");
				String input = scan.nextLine();
				if (SignInValidator.isValidPassword(input)) {
					setUserPw(input);
					break;
				} else {
					System.out.println("올바른 형식이 아닙니다. 다시 입력해주세요.");
				}
			}

			while (true) {
				System.out.print("이름 입력: (2~5자, 한글만 입력): ");
				String input = scan.nextLine();
				if (SignInValidator.isValidName(input)) {
					setUserName(input);
					break;
				} else {
					System.out.println("올바른 형식이 아닙니다. 다시 입력해주세요.");
				}
			}
			while (true) {
				System.out.print("전화번호 입력: (010으로 시작, 숫자만 입력): ");
				String input = scan.nextLine();
				if (SignInValidator.isValidPhoneNumber(input)) {
					setPhoneNumber(input);
					break;

				} else {
					System.out.println("올바른 형식이 아닙니다. 다시 입력해주세요.");
				}
			}
		
		String phoneNum = getPhoneNumber().substring(0, 3) + "-" + getPhoneNumber().substring(3, 7) + "-" + getPhoneNumber().substring(7);
		System.out.println("\n✅ 회원가입 완료!\n");
		System.out.printf("아이디: %s, 이름: %s, 전화번호: %s\n", getUserId(), getUserName(), phoneNum);
	}
	
	/**
	 * 일반회원 계정 정보 수정
	 * @return 페이지 결과
	 */
	@Override
	public PageResult accountEdit() { // 일반회원 계정 정보 수정
		PageResult pageResult = new PageResult();
		
		pageResult.id = StartApplication.nowLoginID;
		
		Scanner scan = InputUtil.SCANNER;
		
		UI.title("일반회원 정보 수정");
		System.out.println();
		System.out.print("수정할 이름 : ");
		String newName = scan.nextLine();
		System.out.print("수정할 비밀번호 : ");
		String newPassword = scan.nextLine();
		System.out.print("수정할 연락처 : ");
		String newPhoneNumber = scan.nextLine();

		List<String> editLine = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(
				new FileReader(com.test.project.file.FileManager.GENERALUSER))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] token = line.split("■");
				String oldId = token[0];
//				String oldPassword = token[1];
//				String oldName = token[2];
//				String oldPhoneNumber = token[3];
				if (oldId.equals(pageResult.id)) {
					line = pageResult.id + "■" + newPassword + "■" + newName + "■" + newPhoneNumber + "■general";
					System.out.println("정보가 수정되었습니다.");
					pageResult.nextPage = MenuNameManager.시작화면;
				}
				editLine.add(line);
			}
		} catch (IOException e) {
			System.out.println("회원정보 수정 실패");
			e.printStackTrace();
			pageResult.nextPage = MenuNameManager.일반회원_정보수정;
			return pageResult;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileManager.GENERALUSER))) {
			for (String line : editLine) {
				writer.write(line);
				writer.newLine();
			}

		} catch (IOException e) {
			System.out.println("변경된 정보 파일 쓰기 오류");
			e.printStackTrace();
		}
		return pageResult;

	}// 메서드

	
}// 클래스
