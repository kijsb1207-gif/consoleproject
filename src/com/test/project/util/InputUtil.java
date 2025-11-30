package com.test.project.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 사용자로부터 입력받는 기능을 제공하는 클래스
 */
public class InputUtil {
	/**
	 * Scanner 사용시 내부 혼선을 방지하기 위해 final 상수로 scanner 객체를 생성
	 */
    public static final Scanner SCANNER = new Scanner(System.in);
    
    /**
     * 
     * @param prompt 
     * @return 
     */
    public static int getValidatedInteger(String prompt) {
        int input = 0;
        boolean isValid = false;
        while (!isValid) { // 유효한 입력이 들어올 때까지 반복
            System.out.print(prompt); 
            try {
                input = SCANNER.nextInt(); // 정수 입력 시도
                isValid = true; // 성공하면 유효 플래그를 true로 설정
            } catch (InputMismatchException e) { // 정수가 아닌 다른 타입 입력 시 예외 처리
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요."); // UI 클래스 사용
            } finally {
                SCANNER.nextLine(); // 버퍼에 남아있는 개행 문자(enter)를 비웁니다.
                                    // nextInt() 다음 nextLine()이 올 때 문제가 되는 것을 방지
            }
        }
        return input;
    }
    
    /**
     * 
     * @return 
     */
    public static int getIntData() {
        int input = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                input = SCANNER.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            } finally {
                SCANNER.nextLine();
            }
        }
        return input;
    }
    

    /**
     * 사용자로부터 문자열을 입력받는다.
     * @return String SCANNER.nextLine()
     */
    public static String getStringData() {
        return SCANNER.nextLine();
    }
    
    
   
}
