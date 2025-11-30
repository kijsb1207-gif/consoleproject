package com.test.project.service.signin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 회원가입 시 입력 정보의 유효성을 검사하는 클래스
 */
public final class SignInValidator {
	/**
	 * 아이디 유효성을 검사
	 * @param id 검사할 아이디
	 * @return 유효하면 true, 그렇지 않으면 false
	 */
	public static boolean isValidId(String id) { // 아이디 유효성 검사
		if (id == null)
			return false;
		return id.matches("^[a-zA-Z0-9_]{4,16}$");
	}
	
	/**
	 * 비밀번호 유효성을 검사
	 * @param password 검사할 비밀번호
	 * @return 유효하면 true, 그렇지 않으면 false
	 */
	public static boolean isValidPassword(String password) { // 비밀번호 유효성 검사

		if (password == null)
			return false;
		return password.matches("^[a-zA-Z0-9_]{4,16}$");

	}
	
	/**
	 * 이름 유효성을 검사
	 * @param name 검사할 이름
	 * @return 유효하면 true, 그렇지 않으면 false
	 */
	public static boolean isValidName(String name) { // 이름 유효성 검사
		if (name == null)
			return false;
		return name.matches("^[가-힣]{2,5}$");

	}
	
	/**
	 * 전화번호 유효성을 검사
	 * @param phoneNumber 검사할 전화번호
	 * @return 유효하면 true, 그렇지 않으면 false
	 */
	public static boolean isValidPhoneNumber(String phoneNumber) { // 연락처 유효성 검사
		if (phoneNumber == null)
			return false;
		return phoneNumber.matches("^010\\d{8}$");
	}
	
	/**
	 * 일반 회원 아이디 중복검사
	 * @param id 확인할 아이디
	 * @return 중복이면 true, 그렇지 않으면 false
	 */
	public static boolean isGeneralDuplicateId(String id) { // 일반회원 아이디 중복검사

		try (BufferedReader reader = new BufferedReader(new FileReader(".//dat//일반회원.txt"))) {
	        String line;

	        while ((line = reader.readLine()) != null) {
	            String[] tokens = line.split("■");
	            if (tokens.length > 0 && tokens[0].equals(id)) {
	                return true; 
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("파일 읽기 오류: " + e.getMessage());
	    }

	    return false;
	
	}
	
	/**
	 * 기업회원 아이디 중복검사
	 * @param id 확인할 아이디
	 * @return 중복이면 true, 그렇지 않으면 false
	 */
	public static boolean isBusinessDuplicateId(String id) { // 기업회원 아이디 중복검사
	
		try (BufferedReader reader = new BufferedReader(new FileReader(".//dat//기업회원.txt"))) {
	        String line;

	        while ((line = reader.readLine()) != null) {
	            String[] tokens = line.split("■");
	            if (tokens.length > 0 && tokens[0].equals(id)) {
	                return true; 
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("파일 읽기 오류: " + e.getMessage());
	    }

	    return false;
	
	
	}
	
	/**
	 * 일반 회원 아이디의 유효성 및 중복을 확인
	 * @param id 확인할 아이디
	 * @return 사용 가능하면 true, 그렇지 않으면 false
	 */
	public static boolean generalCheckId(String id) { // 일반회원 전체아이디 검사 로직

		if (!isValidId(id)) {

			System.out.print("올바른 형식이 아닙니다. ");
			return false;

		}

		if (isGeneralDuplicateId(id)) {

			System.out.print("중복된 아이디입니다. ");
			return false;

		}

		System.out.println("사용 가능한 아이디입니다.");

		return true;

	}
	
	/**
	 * 기업 회원 아이디의 유효성 및 중복을 확인
	 * @param id 확인할 아이디
	 * @return 사용 가능하면 true, 그렇지 않으면 false
	 */
	public static boolean BusinessCheckId(String id) { // 기업회원 전체아이디 검사 로직

		if (!isValidId(id)) {

			System.out.print("올바른 형식이 아닙니다. ");
			return false;

		}

		if (isBusinessDuplicateId(id)) {

			System.out.print("중복된 아이디입니다. ");
			return false;

		}

		System.out.println("사용 가능한 아이디입니다.");

		return true;

	}

}
