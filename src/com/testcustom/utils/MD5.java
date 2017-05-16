package com.testcustom.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String md5(String pass){
		String hexStr="";
		try {
			//1��ָ����������
			MessageDigest md=MessageDigest.getInstance("MD5");
			
			//2������Ҫ������ת��Ϊbyte��������
			//��֤��������16λbyte��������
			byte[] bs=md.digest((pass+".testcustom").getBytes());
			//3��ѭ������bs
			for(byte b:bs){
				int i=b & 0xff;
				//��ʮ���Ƶ����֣�ת��Ϊ16����
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
