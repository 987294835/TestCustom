package com.testcustom.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String md5(String pass){
		String hexStr="";
		try {
			//1、指定加密类型
			MessageDigest md=MessageDigest.getInstance("MD5");
			
			//2、把需要的密码转换为byte类型数组
			//保证生产的是16位byte类型数组
			byte[] bs=md.digest((pass+".testcustom").getBytes());
			//3、循环遍历bs
			for(byte b:bs){
				int i=b & 0xff;
				//把十进制的数字，转换为16进制
				String hex=Integer.toHexString(i);
				
				if(hex.length()<2){
					hex="0"+hex;//01
				}
				hexStr+=hex;
			
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hexStr;
	}
	
}
