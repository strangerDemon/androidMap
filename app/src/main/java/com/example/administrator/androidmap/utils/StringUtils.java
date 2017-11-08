package com.example.administrator.androidmap.utils;

import android.text.Editable;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

/**
 * @author sloanwu 2011-5-1 下午07:15:38
 *
 */
public class StringUtils {
	public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
	// "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$"
			"^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
	public static final Pattern MOBILE_PHONE_PATTERN = Pattern
			.compile("(^(\\+\\d{2,3})?(\\d{3,4}-)?\\d{7,30})$");

	public static final String EMPTY_SYSM = "";
	public static final int SHORT_TITLE_LENGTH = 80;

	public static boolean isEmpty(String val) {
		return val == null || EMPTY_SYSM.equals(val.trim());
	}
	public static boolean isEmpty(Editable val) {
		return val == null || EMPTY_SYSM.equals(val.toString().trim());
	}
	public static boolean isNull(String code) {
		return null == code || EMPTY_SYSM.equals(code);
	}

	public static boolean isEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		if(phoneNumber.length() != 10){
			return false;
		}
		return MOBILE_PHONE_PATTERN.matcher(phoneNumber).matches();
	}

	public static boolean isContain(String body, String keyword) {
		if (isEmpty(body)) {
			return false;
		}
		if (-1 != body.toUpperCase().indexOf(keyword.toUpperCase())) {
			return true;
		}
		return false;
	}

	public static String toShortTitle(String title, int minLength) {
		if (isEmpty(title))
			return EMPTY_SYSM;

		if (title.length() >= minLength) {
			return title.substring(0, minLength) + "...";
		} else {
			return title;
		}
	}

	public static String toShortTitle(String title) {
		return toShortTitle(title, SHORT_TITLE_LENGTH);
	}

	public static String encode(String keyword) {
		String result = EMPTY_SYSM;
		try {
			result = URLEncoder.encode(keyword, "UTF-8");
		} catch (Exception e) {
		}
		return result;
	}

	public static String decode(String keyword) {
		return URLDecoder.decode(keyword);
	}

	public static boolean invalid(String strTemp) {
		String strField = "&*#!\'\"";// 非法字符集
		for (int i = 0; i < strTemp.length(); i++) {
			char c = strTemp.charAt(i);
			if (strField.indexOf(c) != -1) {
				return true;
			}
		}
		return false;
	}

	private static final int NUM = 1;
	private static final int SMALL_LETTER = 2;
	private static final int CAPITAL_LETTER = 3;
	private static final int OTHER_CHAR = 4;

	private static int checkCharacterType(char c) {
		if (c >= 48 && c <= 57) {
			return NUM;
		}
		if (c >= 65 && c <= 90) {
			return CAPITAL_LETTER;
		}
		if (c >= 97 && c <= 122) {
			return SMALL_LETTER;
		}
		return OTHER_CHAR;
	}

	private static int countLetter(String passwd, int type) {
		int count = 0;
		if (null != passwd && passwd.length() > 0) {
			for (char c : passwd.toCharArray()) {
				if (checkCharacterType(c) == type) {
					count++;
					return count;
				}
			}
		}
		return count;
	}

	public static boolean isPassWord(String pwd){
		if (!isEmpty(pwd)){
			int level = 0;

			// increase points
			if (countLetter(pwd, NUM) > 0) {
				level++;
			}
			if (countLetter(pwd, CAPITAL_LETTER) > 0) {
				level++;
			}
			if (countLetter(pwd, OTHER_CHAR) > 0) {
				level++;
			}
            if (level==3){
                return true;
            }
		}
		return false;
	}

	public static boolean isSpecialCharacters(String input){
		if (!isEmpty(input)&&countLetter(input,OTHER_CHAR)==0){
			return false;
		}
		return true;
	}

	public static String formatSecondDecimalPoint(double v){
		return String.format("%.2f", v);
	}
	public static String formatTelephone(String phone) {
		if(isEmpty(phone)){
			return "";
		}
		try {
			String temp = phone;
			if(phone.contains("-")){
				temp = phone.replace("-", "");
			}
			if(phone.contains("(")){
				temp = temp.replace("(", "");
			}
			if(phone.contains(")")){
				temp = temp.replace(")", "");
			}
			String reuslt = temp;
			if(temp.length() > 6){
				reuslt = temp.substring(0, 3)+"-"+temp.substring(3, 6)+"-"+temp.substring(6, temp.length());
			}
			return reuslt;
		} catch (Exception e) {
			return "";
		}
	}
}
