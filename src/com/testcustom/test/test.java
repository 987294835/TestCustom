package com.testcustom.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.junit.Test;

import com.testcustom.dao.CustomDao;
import com.testcustom.domain.Title;
import com.testcustom.utils.MathRandom;

public class test {

	@Test
	public void test() {
		/*
		 * MathRandom a = new MathRandom(); List<Integer> list1 = new
		 * ArrayList<Integer>(); for(int i =0 ;i< 29 ; i++){ list1.add(i); }
		 * List<Integer> list2 = new ArrayList<Integer>(); List<Integer> list3 =
		 * new ArrayList<Integer>(); System.out.println(list1); HashSet<Integer>
		 * hashSet = a.getRanNum(29, 15); System.out.println(hashSet);
		 * Iterator<Integer> iterator = hashSet.iterator();
		 * while(iterator.hasNext()){ int it = iterator.next();
		 * list2.add(list1.get(it)); list3.add(it); } for(int i = 0; i <=
		 * list3.size()-1; i++){ list1.remove(list3.get(i)); }
		 * System.out.println("list1==>"+list1);
		 * System.out.println("list2==>"+list2);
		 */

		// String result = getSix();
		//
		// System.out.println("result====>"+result);
		/*
		 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); try {
		 * Date d1 = df.parse("2016-9-21 13:31:40"); Date d2 =
		 * df.parse("2016-9-21 13:22:24"); long diff = d1.getTime() -
		 * d2.getTime(); long minutes = diff / (1000 * 60 );
		 * System.out.println(minutes); } catch (Exception e) { }
		 */
		 /*Calendar calendar = Calendar.getInstance(Locale.CHINA);
	        Date date = calendar.getTime();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String dateString = dateFormat.format(date);
	        System.out.println(dateString);*/
		String time="2016:1:1T10:55";
		System.out.println(time.replace("T", " "));
		

		

	}

	public String getSix() {
		Random rm = new Random();
		String result = rm.nextInt(100) + "";
		/*
		 * if(result.length()!=6){ return getSix(); }
		 */
		return result;
	}
}

// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
// String time = df.format(new Date());
// System.out.println(time);// new Date()为获取当前系统时间
// Date date = new Date();
// System.out.println(date);
// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
// try
// {
// String time = df.format(new Date());
// Date d1 = df.parse(time);
// Date d2 = df.parse("2016-6-16");
// long diff = d1.getTime() - d2.getTime();
// long days = diff / (1000 * 60 * 60 * 24);
// System.out.println(days);
// }
// catch (ParseException e)
// {
// e.printStackTrace();
// }
// Random rm = new Random();
// for(int n =0;n<30; n++){
// System.out.print(rm.nextInt(3)+"  ");
// }
// HashSet<Integer> hs = new HashSet<Integer>();
// Random r = new Random();
// while(hs.size()<5)
// {
// hs.add(r.nextInt(100));
// }
// System.out.println(hs);
// int i = 0;
// MathRandom a = new MathRandom();
// HashSet<Integer> hs = new HashSet<Integer>();
// while(hs.size()<10)//打印100个测试概率的准确性
// {
//
// hs.add(a.PercentageRandom());
// }
// System.out.println(hs);
//
// }
//
// /**
// * JAVA 返回随机数，并根据概率、比率
// *
// *
// */
// public class MathRandom {
// /**
// * 第1段中数字出现的概率为%35
// */
// public double rate0 = 0.35;
// /**
// * 第2段中数字出现的概率为%25
// */
// public double rate1 = 0.25;
// /**
// * 第3段中数字出现的概率为%20
// */
// public double rate2 = 0.20;
// /**
// * 第4段中数字出现的概率为%15
// */
// public double rate3 = 0.15;
// /**
// * 第5段中数字出现的概率为%10
// */
// public double rate4 = 0.10;
//
// /**
// * Math.random()产生一个double型的随机数，判断一下 例如0出现的概率为%50，则介于0到0.50中间的返回0
// *
// * @return int
// *
// */
// private int PercentageRandom() {
// Random rm = new Random();
// double randomNumber;
// int i = 0;
// int n = 46/5;
// int m = 46%5;
// System.out.println("n===>"+n+" "+"m===>"+m);
// randomNumber = Math.random();
// // System.out.println("randomNumber" + randomNumber);
// if (randomNumber >= 0 && randomNumber <= rate0) {
// i=rm.nextInt(10);
// return i;
// } else if (randomNumber >= rate0 && randomNumber <= rate0 + rate1) {
// i=rm.nextInt(10)+10;
// return i;
// } else if (randomNumber >= rate0 + rate1
// && randomNumber <= rate0 + rate1 + rate2) {
// i=rm.nextInt(10)+20;
// return i;
// } else if (randomNumber >= rate0 + rate1 + rate2
// && randomNumber <= rate0 + rate1 + rate2 + rate3) {
// i=rm.nextInt(10)+30;
// return i;
// } else if (randomNumber >= rate0 + rate1 + rate2 + rate3
// && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
// i=rm.nextInt(10)+40;
// return i;
// }
// return -1;
// }
// }
// }
