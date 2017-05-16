package com.testcustom.utils;

import java.util.HashSet;
import java.util.Random;

/**
 * 根据不通概率生成随机数 把范围内的数字平均分为5段，每一段生成随机数的概率不同
 * @author Luke
 */

public class MathRandom {
	/**
	 * 第1段中数字出现的概率为%35
	 */
	public static double rate0 = 0.35;
	/**
	 * 第2段中数字出现的概率为%25
	 */
	public static double rate1 = 0.25;
	/**
	 * 第3段中数字出现的概率为%20
	 */
	public static double rate2 = 0.20;
	/**
	 * 第4段中数字出现的概率为%15
	 */
	public static double rate3 = 0.15;
	/**
	 * 第5段中数字出现的概率为%10
	 */
	public static double rate4 = 0.10;

	/**
	 * Math.random()产生一个double型的随机数，判断一下 例如rate0出现的概率为%35，则介于0到0.35中间的返回该第1段中的随机一个数字
	 * n=size/5;m=size%5;size为集合长度
	 * 
	 * @return int
	 * 
	 */
	private int PercentageRandom(int n, int m) {
		Random rm = new Random();
		double randomNumber;
		int i = 0;
		randomNumber = Math.random();
		// System.out.println("randomNumber" + randomNumber);
		if (randomNumber >= 0 && randomNumber <= rate0) {
			i = rm.nextInt(n);
			return i;
		} else if (randomNumber >= rate0 && randomNumber <= rate0 + rate1) {
			i = rm.nextInt(n) + n;
			return i;
		} else if (randomNumber >= rate0 + rate1
				&& randomNumber <= rate0 + rate1 + rate2) {
			i = rm.nextInt(n) + 2 * n;
			return i;
		} else if (randomNumber >= rate0 + rate1 + rate2
				&& randomNumber <= rate0 + rate1 + rate2 + rate3) {
			i = rm.nextInt(n) + 3 * n;
			return i;
		} else if (randomNumber >= rate0 + rate1 + rate2 + rate3
				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
			i = rm.nextInt(n + m) + 4 * n;
			return i;
		}
		return -1;
	}

	/**
	 * 获得指定个数的指定范围内数字的随机数
	 */
	public HashSet<Integer> getRanNum(int size, int num) {
		MathRandom a = new MathRandom();
		Random r = new Random();
		HashSet<Integer> hs = new HashSet<Integer>();
		int n = size / 5;
		int m = size % 5;
		/**size - 符合条件的所有题目
		 * num - 用户需要的数量
		 * 如果size<5,直接生成指定数量的不同随机数
		 * 否则，根据不同概率生成指定数量的不同随机数
		 */
		if (n == 0) {
			while(hs.size() < num){
				hs.add(r.nextInt(size));
			}
		} else {
			// 生成指定数目的不重复的随机数
			while (hs.size() < num) {
				hs.add(a.PercentageRandom(n, m));
			}
		}
		return hs;
	}

}
