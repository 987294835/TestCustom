package com.testcustom.utils;

import java.util.Random;

/**
 * 随机生成6位数的验证码
 * @author Administrator
 *
 */
public class getRandom {
	public static String getSix(){
		Random rm = new Random();
		String result = rm.nextInt(1000000)+"";
		if(result.length()!=6){
			return getSix();
		}
		return result;
	}
	
	public static String getExamCode(){
		Random rm = new Random();
		String result = rm.nextInt(100000000)+"";
		if(result.length()!=8){
			return getExamCode();
		}
		return result;
	}
}
