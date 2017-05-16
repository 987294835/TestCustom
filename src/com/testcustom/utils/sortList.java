package com.testcustom.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.testcustom.domain.Title;

/**
 * 将符合条件的试题进行排序
 * 新试题排在最前，其他试题根据选中热度排序
 * @author Luke
 *
 */
 
public class sortList {
	
	public List<Title> sort_List(List<Title> titles){
		System.out.println("****进入sort_List()*****");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(new Date());
		List<Title> sortTitle = new ArrayList<Title>();
		for (int i = titles.size() - 1; i >= 0; i--) {
			String Title_time = titles.get(i).getTitle_time();
			try {
				/**
				 * 判断题目添加时间和系统时间的差
				 */
				Date system_time = df.parse(time);
				Date title_time = df.parse(Title_time);
				long diff = system_time.getTime() - title_time.getTime();
				long days = diff / (1000 * 60 * 60 * 24);
				if (days <= 3) {
					sortTitle.add(titles.get(i));
					titles.remove(i);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		for (int i = titles.size() - 1; i >= 0; i--) {
			sortTitle.add(titles.get(i));
			titles.remove(i);
		}
		return sortTitle;
	}
}
