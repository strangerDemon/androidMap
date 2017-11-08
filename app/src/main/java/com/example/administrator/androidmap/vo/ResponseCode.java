package com.example.administrator.androidmap.vo;

import com.example.administrator.androidmap.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sloanwu
 * 2011-7-7 下午04:32:49
 *
 */
public class ResponseCode {

	public static final List<String> CODE_SUCCESS = new ArrayList<String>();
	public static final List<String> CODE_ERROR = new ArrayList<String>();
	static{
		CODE_SUCCESS.add("01");
		CODE_SUCCESS.add("2101");
		CODE_SUCCESS.add("2201");
		CODE_SUCCESS.add("2301");
		CODE_SUCCESS.add("2401");
		CODE_SUCCESS.add("2501");
		CODE_SUCCESS.add("2601");
		CODE_SUCCESS.add("2701");
		CODE_SUCCESS.add("2801");
		CODE_SUCCESS.add("2901");
		CODE_SUCCESS.add("3101");
		CODE_SUCCESS.add("3201");
		CODE_SUCCESS.add("3301");
		CODE_SUCCESS.add("3401");
		CODE_SUCCESS.add("3501");
		CODE_SUCCESS.add("3601");
		CODE_SUCCESS.add("3701");
		CODE_SUCCESS.add("3801");
		CODE_SUCCESS.add("3901");
		CODE_SUCCESS.add("4001");
		CODE_SUCCESS.add("4101");
		CODE_SUCCESS.add("4201");
		CODE_SUCCESS.add("4301");
		CODE_SUCCESS.add("4401");
		CODE_SUCCESS.add("4501");
		CODE_SUCCESS.add("4601");
		CODE_SUCCESS.add("5101");
		CODE_SUCCESS.add("5201");
		CODE_SUCCESS.add("5301");
		CODE_SUCCESS.add("5401");
		
		CODE_ERROR.add("02");
		CODE_ERROR.add("2102");
		CODE_ERROR.add("2202");
		CODE_ERROR.add("2302");
		CODE_ERROR.add("2402");
		CODE_ERROR.add("2502");
		CODE_ERROR.add("2602");
		CODE_ERROR.add("2702");
		CODE_ERROR.add("2802");
		CODE_ERROR.add("2902");
		CODE_ERROR.add("3102");
		CODE_ERROR.add("3202");
		CODE_ERROR.add("3302");
		CODE_ERROR.add("3402");
		CODE_ERROR.add("3502");
		CODE_ERROR.add("3602");
		CODE_ERROR.add("3702");
		CODE_ERROR.add("3802");
		CODE_ERROR.add("3902");
		CODE_ERROR.add("4002");
		CODE_ERROR.add("4102");
		CODE_ERROR.add("4202");
		CODE_ERROR.add("4302");
		CODE_ERROR.add("4402");
		CODE_ERROR.add("4502");
		CODE_ERROR.add("4602");
		CODE_ERROR.add("5102");
		CODE_ERROR.add("5202");
		CODE_ERROR.add("5302");
		CODE_ERROR.add("5402");
		
		
	}
	
	public static boolean isInSuccess(String code){
		return CODE_SUCCESS.contains(code);
	}
	
	public static boolean isInError(String code){
		return CODE_ERROR.contains(code);
	}
	
	public static boolean isSuccess(String code){
		if(StringUtils.isEmpty(code))
			return false;
		
		boolean result = false;
		char lastChar  = code.charAt(code.length() - 1);
		result = lastChar == '1';
//		switch(lastChar){
//		case '1' :
//			result = true;
//			break;
//		case '2' :
//			result = false;
//			break;
//		}
		return result;
	}
	public static boolean isLoginOtherPhone(String code){
		if(StringUtils.isEmpty(code))
			return false;
		boolean result = false;
		char lastChar  = code.charAt(code.length() - 1);
		result = lastChar == '2';
		return result;
	}
}
