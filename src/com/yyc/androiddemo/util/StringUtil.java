package com.yyc.androiddemo.util;

public class StringUtil {
	public static String formatUnderTen(int value){//格式化小于10的数字
		if (value < 10) {
			return "0"+value;
		} else {
			return ""+value;
		}
	}
}
