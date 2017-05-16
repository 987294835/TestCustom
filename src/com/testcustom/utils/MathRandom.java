package com.testcustom.utils;

import java.util.HashSet;
import java.util.Random;

/**
 * ���ݲ�ͨ������������� �ѷ�Χ�ڵ�����ƽ����Ϊ5�Σ�ÿһ������������ĸ��ʲ�ͬ
 * @author Luke
 */

public class MathRandom {
	/**
	 * ��1�������ֳ��ֵĸ���Ϊ%35
	 */
	public static double rate0 = 0.35;
	/**
	 * ��2�������ֳ��ֵĸ���Ϊ%25
	 */
	public static double rate1 = 0.25;
	/**
	 * ��3�������ֳ��ֵĸ���Ϊ%20
	 */
	public static double rate2 = 0.20;
	/**
	 * ��4�������ֳ��ֵĸ���Ϊ%15
	 */
	public static double rate3 = 0.15;
	/**
	 * ��5�������ֳ��ֵĸ���Ϊ%10
	 */
	public static double rate4 = 0.10;

	/**
	 * Math.random()����һ��double�͵���������ж�һ�� ����rate0���ֵĸ���Ϊ%35�������0��0.35�м�ķ��ظõ�1���е����һ������
	 * n=size/5;m=size%5;sizeΪ���ϳ���
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
	 * ���ָ��������ָ����Χ�����ֵ������
	 */
	public HashSet<Integer> getRanNum(int size, int num) {
		MathRandom a = new MathRandom();
		Random r = new Random();
		HashSet<Integer> hs = new HashSet<Integer>();
		int n = size / 5;
		int m = size % 5;
		/**size - ����������������Ŀ
		 * num - �û���Ҫ������
		 * ���size<5,ֱ������ָ�������Ĳ�ͬ�����
		 * ���򣬸��ݲ�ͬ��������ָ�������Ĳ�ͬ�����
		 */
		if (n == 0) {
			while(hs.size() < num){
				hs.add(r.nextInt(size));
			}
		} else {
			// ����ָ����Ŀ�Ĳ��ظ��������
			while (hs.size() < num) {
				hs.add(a.PercentageRandom(n, m));
			}
		}
		return hs;
	}

}
