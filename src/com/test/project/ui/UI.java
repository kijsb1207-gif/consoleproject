package com.test.project.ui;

import java.io.File;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;
import com.test.project.util.InputUtil;

public class UI {
	
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";

	
	/**
	 * jfiglet으로 제작한 hotel 스플래시 화면 출력
	 */
	public static void splashTitle() {
		try {
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			String asciiArt = FigletFont.convertOneLine(new File("./flf/Alligator2.flf"), " hotel ");
		    System.out.print(asciiArt);
		    System.out.println();
		    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━ RESERVATION SYSTEM ━━━━━━━━━━━━━━━━━━━━━━━━");
		    System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
		    System.out.println();
		} catch (Exception e) {
			System.out.println("UI.title");
			e.printStackTrace();	
		}
	}
	
	/*
	
	*/
	
	/**
	 * 단일 구분선 출력
	 * @param n : 출력할 구분선의 길이
	 */
	public static void singleLine(int n) {
		String singleLine = "";
		for (int i=0; i<n;i++) {
			singleLine += "━";
		}
		System.out.print(singleLine);
	}
	
	public static void singleLine2(int n) {
		String singleLine = "";
		for (int i=0; i<n;i++) {
			singleLine += "┈";
		}
		System.out.print(singleLine);
	}
	
	

	/**
	 * 이중 구분선 출력
	 * @param n : 출력할 구분선의 길이
	 */
	public static void doubleLine(int n) {
		String doubleLine = "";
		for (int i=0; i<n;i++) {
			doubleLine += "═";
		}
		System.out.print(doubleLine);
	}
	
	/**
	 * "계속하려면 엔터를 입력하세요." 메세지 출력 후 사용자가 엔터를 입력할 때까지 기다린다.
	 * 선생님이랑 했던 예제의 pause() 메서드와 동일 기능
	 */
	public static void pressEnterUI() {
		Scanner scan = InputUtil.SCANNER;
		System.out.println();
		System.out.println("계속하려면 엔터를 입력하세요.");
		scan.nextLine();
	}
	
	/**
	 * 입력한 내용을 구분선으로 감싼 제목 출력
	 * @param text
	 */
	public static void title(String text) {
		text = "✦ " + text + " ✦";
		System.out.println();
		System.out.print("");
		doubleLine(68);
		System.out.println();
		System.out.println(centerText(text,68));
		System.out.print("⊷");
		singleLine2(75);
		System.out.println("⊶");
		
	}
	
	public static void chartTitle(String text) {
		System.out.println();
		System.out.print("");
		singleLine2(75);
		System.out.println();
		System.out.println(text);
		singleLine2(75);
		System.out.println();
		
	}
	
	
	//menuDetail() 오버로딩(최대 5개 메뉴까지, 모두 String)
	/**
	 * 선택할 수 있는 메뉴 목록과 메뉴 선택창을 출력. 
	 * 넣을 수 있는 문자열 최대 5개까지 오버로딩되어있음
	 * @param menu1
	 */
	public static void menuDetail(String menu1) {
		System.out.println();
		System.out.println("   1. "+ menu1);
		System.out.println();
		System.out.print("⊷");
		singleLine2(75);
		System.out.print("⊶");
		
		System.out.println();
		System.out.print("메뉴 선택: ");
		
	}
	
	/**
	 * 선택할 수 있는 메뉴 목록과 메뉴 선택창을 출력. 
	 * 넣을 수 있는 문자열 최대 5개까지 오버로딩되어있음
	 * @param menu1
	 * @param menu2
	 */
	public static void menuDetail(String menu1, String menu2) {
		System.out.println();
		System.out.println("   1. "+ menu1);
		System.out.println("   2. "+ menu2);
		System.out.println();
		System.out.print("⊷");
		singleLine2(75);
		System.out.print("⊶");
		
		System.out.println();
		System.out.print("메뉴 선택: ");
		
	}
	
	/**
	 * 선택할 수 있는 메뉴 목록과 메뉴 선택창을 출력. 
	 * 넣을 수 있는 문자열 최대 5개까지 오버로딩되어있음
	 * @param menu1
	 * @param menu2
	 * @param menu3
	 */
	public static void menuDetail(String menu1, String menu2, String menu3) {
		System.out.println();
		System.out.println("   1. "+ menu1);
		System.out.println("   2. "+ menu2);
		System.out.println("   3. "+ menu3);
		System.out.println();
		System.out.print("⊷");
		singleLine2(75);
		System.out.print("⊶");
		
		System.out.println();
		System.out.print("메뉴 선택: ");

		
	}
	
	/**
	 * 선택할 수 있는 메뉴 목록과 메뉴 선택창을 출력. 
	 * 넣을 수 있는 문자열 최대 5개까지 오버로딩되어있음
	 * @param menu1
	 * @param menu2
	 * @param menu3
	 * @param menu4
	 */
	public static void menuDetail(String menu1, String menu2, String menu3, String menu4) {
		System.out.println();
		
		System.out.println("   1. "+ menu1);
		System.out.println("   2. "+ menu2);
		System.out.println("   3. "+ menu3);
		System.out.println("   4. "+ menu4);
		System.out.println();
		System.out.print("⊷");
		singleLine2(75);
		System.out.print("⊶");
		
		System.out.println();
		System.out.print("메뉴 선택: ");
		
	}
	
	/**
	 * 선택할 수 있는 메뉴 목록과 메뉴 선택창을 출력. 
	 * 넣을 수 있는 문자열 최대 5개까지 오버로딩되어있음
	 * @param menu1
	 * @param menu2
	 * @param menu3
	 * @param menu4
	 * @param menu5
	 */
	public static void menuDetail(String menu1, String menu2, String menu3, String menu4, String menu5) {
		System.out.println();
		System.out.println("   1. "+ menu1);
		System.out.println("   2. "+ menu2);
		System.out.println("   3. "+ menu3);
		System.out.println("   4. "+ menu4);
		System.out.println("   5. "+ menu5);
		System.out.println();
		System.out.print("⊷");
		singleLine2(75);
		System.out.print("⊶");
		
		System.out.println();
		System.out.print("메뉴 선택: ");
		
	}
	
	/**
	 * 제목 가운데 정렬
	 * @param text
	 * @param width
	 * @return
	 */
	private static String centerText(String text, int width) {
	    int displayWidth = getDisplayWidth(text);
	    
	    if (displayWidth >= width) {
	        return text.substring(0, Math.min(text.length(), width));
	    }
	    
	    int spaces = width - displayWidth;
	    int leftSpaces = spaces / 2;
	    int rightSpaces = spaces - leftSpaces;
	    
	    return " ".repeat(leftSpaces) + text + " ".repeat(rightSpaces);
	}
	
	private static int getDisplayWidth(String text) {
	    int width = 0;
	    for (char c : text.toCharArray()) {
	        if (c >= 0x1100 && c <= 0x11FF ||  // 한글 자모
	            c >= 0x3130 && c <= 0x318F ||  // 한글 호환 자모
	            c >= 0xAC00 && c <= 0xD7AF ||  // 한글 음절
	            c >= 0xFF01 && c <= 0xFF5E) {   // 전각 문자
	            width += 2;  // 한글/전각 문자는 2칸
	        } else {
	            width += 1;  // 영문자는 1칸
	        }
	    }
	    return width;
	}
        
        
    }
	
