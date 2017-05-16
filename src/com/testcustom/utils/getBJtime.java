package com.testcustom.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class getBJtime {
	public static String get_BJtime(int i) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date date = calendar.getTime();
		SimpleDateFormat dateFormat = null;
		if(i == 1){
			 dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
		}else if(i == 2){
			dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
		}else if(i == 3){
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
		
		String dateString = dateFormat.format(date);
		return dateString;
	}
}
