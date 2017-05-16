package com.testcustom.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDifferent {
	
	public static long different(String time1, String time2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long minutes = 0;
		try {
			Date d1 = df.parse(time1);
			Date d2 = df.parse(time2);
			long diff = d1.getTime() - d2.getTime(); 
			minutes = diff / (1000 * 60 );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return minutes;
	}
}
