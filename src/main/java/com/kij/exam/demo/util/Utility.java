package com.kij.exam.demo.util;

public class Utility {

	public static boolean empty(Object obj) {
		// 값이 비어있다면
		if(obj == null) {
			return true;
		}
		
//		// String 이 아니라면
//		if(obj instanceof String == false) {
//			return true;
//		}
		
		// 형 변환
		String str = (String) obj;
		
		return str.trim().length() == 0;
	}
	
	// 텍스트 포맷용
	public static String f(String format, Object ...args) {
		return String.format(format, args);
	}

}
