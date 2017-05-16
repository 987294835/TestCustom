package com.testcustom.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.CollectDao;
import com.testcustom.dao.CustomDao;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.MathRandom;
import com.testcustom.utils.PageController;
import com.testcustom.utils.sortList;
/**
 * 试题定制控制器
 * @author Administrator
 *
 */

@Controller
public class CustomController {
	@Resource
	private CollectDao collectDao;

	public void setCollectDao(CollectDao collectDao) {
		this.collectDao = collectDao;
	}
	@Resource
	private CustomDao customDao;

	public void setCustomDao(CustomDao customDao) {
		this.customDao = customDao;
	}

	/*
	 * 获取岗位信息
	 */
	@RequestMapping("select_Major")
	public String selectMajor(HttpSession session) {
		List<Object> major = customDao.SelectMajorList();
		// major.add("test");
		session.setAttribute("major", major);
//		for (Object majorLists : major) {
//			System.out.println(majorLists);
//		}
		return "paper_made.jsp";
	}

	/*
	 * 根据搜素条件获得随机题目
	 */
	@RequestMapping("query_By_Condition")
	public String queryByCondition(String major, String backgroud, 
			String mutilchoice_num, String choice_num, String blank_num, String quiz_num,
			HttpSession session) {
		
		session.setAttribute("choice_num", choice_num);
		session.setAttribute("blank_num", blank_num);
       	session.setAttribute("quiz_num", quiz_num);
       	session.setAttribute("mutilchoice_num", mutilchoice_num);
		
		session.removeAttribute("customTitles_choice");
		session.removeAttribute("customTitles_blank");
		session.removeAttribute("customTitles_quiz");
		session.removeAttribute("customTitles_mutilchoice");
//		
		System.out.println("choice_num===>"+choice_num);
		System.out.println("blank_num===>"+blank_num);
//		System.out.println(jqiz_num);
		
		MathRandom a = new MathRandom();
		sortList s = new sortList();
		
		List<Integer> numOfList_choice = new ArrayList<Integer>();
		List<Integer> numOfList_blank = new ArrayList<Integer>();
		List<Integer> numOfList_quiz = new ArrayList<Integer>();
		List<Integer> numOfList_mutilchoice = new ArrayList<Integer>();
	

		Properties pro = new Properties();
		int BACKGROUD = Integer.parseInt(backgroud);
		System.out.println("major===>" + major);
		System.out.println("backgroud===>" + backgroud);
		if (major != null && !major.trim().equals("")) {
			pro.setProperty("title_major", major);
		}
		if (!backgroud.equals("-1")) {
			/*switch (BACKGROUD) {
			// 实习生，应届生，工作经验<=0
			case 0: {
				pro.setProperty("title_backgroud", "0");
				break;
			}
			// 工作经验1~2年
			case 1: {
				pro.setProperty("title_backgroud", "2");
				pro.setProperty("title_backgroud1", "1");
				break;
			}
			// 工作经验2~4年
			case 2: {
				pro.setProperty("title_backgroud", "4");
				pro.setProperty("title_backgroud1", "2");
				break;
			}
			// 工作5年以上
			case 3: {
				pro.setProperty("title_backgroud1", "5");
				break;
			}
			}*/
			pro.setProperty("title_backgroud", backgroud);
		}
		
		/*
		 * 选择题随机生成
		 */
		/**
		 * 根据用户条件来推荐最符合用户的试题
		 */
		List<Title> customTitles_choice = new ArrayList<Title>();
		//判断用户是否选择了选择题
		if(Integer.parseInt(choice_num)>0){
			List<Title> titles_choice_first = customDao.QueryTypeByCondition(pro, "0");
			List<Title> titles_Choice = new ArrayList<Title>();
			//判断是否有符合类型的题目且题目数量大于等于用户需求
			if(titles_choice_first.size()>0 && titles_choice_first.size() >= Integer.parseInt(choice_num)){
				titles_Choice = s.sort_List(titles_choice_first);
				System.out.println("****titles_Choice*******");
				for (Title title_Choice : titles_Choice) {
					System.out.println("titles_Choice==>" + title_Choice.getTitle_id()
							+ ":" + title_Choice.getTitle_time() + ":"
							+ title_Choice.getTitle_num());
				}
				HashSet<Integer> ranNum = a.getRanNum(titles_Choice.size(),Integer.parseInt(choice_num));
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_choice.add(titles_Choice.get(rannum));
					numOfList_choice.add(rannum);
				}
				System.out.println("numOfList_choice.size()===>"+numOfList_choice.size());
				for(int i = numOfList_choice.size()-1; i >= 0 ; i--){
					int j = numOfList_choice.get(i);
//					titles_Choice.remove(j);
					for(int n = titles_Choice.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_Choice.remove(n);
						}
					}
					
				}
				for (Title new_titles_choice : titles_Choice) {
					System.out.println("new_titles_choice==>" + new_titles_choice.getTitle_id()
							+ ":" + new_titles_choice.getTitle_time() + ":"
							+ new_titles_choice.getTitle_num());
				}
				session.setAttribute("customTitles_choice", customTitles_choice);
				session.setAttribute("titles_Choice", titles_Choice);
			}else{
				session.setAttribute("customTitles_choice", titles_choice_first);
				session.setAttribute("titles_Choice", titles_Choice);
			}
		}
		/*
		 * 多项选择题随机生成
		 */
		List<Title> customTitles_mutilchoice = new ArrayList<Title>();
		//判断用户是否选择了选择题
		if(Integer.parseInt(mutilchoice_num)>0){
			List<Title> titles_mutilchoice_first = customDao.QueryTypeByCondition(pro, "1");
			List<Title> titles_mutilchoice = new ArrayList<Title>();
			//判断是否有符合类型的题目且题目数量大于等于用户需求
			if(titles_mutilchoice_first.size()>0 && titles_mutilchoice_first.size() >= Integer.parseInt(mutilchoice_num)){
				titles_mutilchoice = s.sort_List(titles_mutilchoice_first);
				System.out.println("****titles_mutilchoice*******");
				for (Title title_mutilchoice : titles_mutilchoice) {
					System.out.println("titles_mutilchoice==>" + title_mutilchoice.getTitle_id()
							+ ":" + title_mutilchoice.getTitle_time() + ":"
							+ title_mutilchoice.getTitle_num());
				}
				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice.size(),Integer.parseInt(mutilchoice_num));
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_mutilchoice.add(titles_mutilchoice.get(rannum));
					numOfList_mutilchoice.add(rannum);
				}
				System.out.println("numOfList_mutilchoice.size()===>"+numOfList_mutilchoice.size());
				for(int i = numOfList_mutilchoice.size()-1; i >= 0 ; i--){
					int j = numOfList_mutilchoice.get(i);
//					titles_mutilchoice.remove(j);
					for(int n = titles_mutilchoice.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_mutilchoice.remove(n);
						}
					}
					
				}
				for (Title new_titles_mutilchoice : titles_mutilchoice) {
					System.out.println("new_titles_mutilchoice==>" + new_titles_mutilchoice.getTitle_id()
							+ ":" + new_titles_mutilchoice.getTitle_time() + ":"
							+ new_titles_mutilchoice.getTitle_num());
				}
				session.setAttribute("customTitles_mutilchoice", customTitles_mutilchoice);
				session.setAttribute("titles_mutilchoice", titles_mutilchoice);
			}else{
				session.setAttribute("customTitles_mutilchoice", titles_mutilchoice_first);
				session.setAttribute("titles_mutilchoice", titles_mutilchoice);
			}
		}
		
		/*
		 * 判断题随机生成
		 */
		List<Title> customTitles_blank = new ArrayList<Title>();
		//判断用户是否选择了填空题
		if(Integer.parseInt(blank_num)>0){
			List<Title> titles_blank_first = customDao.QueryTypeByCondition(pro, "2");
			List<Title> titles_blank = new ArrayList<Title>();
			//判断是否有符合类型的题目且题目数量大于等于用户需求
			if(titles_blank_first.size()>0 && titles_blank_first.size() >= Integer.parseInt(blank_num)){
				titles_blank = s.sort_List(titles_blank_first);
				System.out.println("****titles_blank*******");
				for (Title title_Blank : titles_blank) {
					System.out.println("titles_Blank==>" + title_Blank.getTitle_id()
							+ ":" + title_Blank.getTitle_time() + ":"
							+ title_Blank.getTitle_num());
				}
				HashSet<Integer> ranNum = a.getRanNum(titles_blank.size(),Integer.parseInt(blank_num));
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_blank.add(titles_blank.get(rannum));
					numOfList_blank.add(rannum);
				}
				System.out.println("numOfList_blank.size()===>"+numOfList_blank.size());
				for(int i = numOfList_blank.size()-1; i >= 0 ; i--){
					int j = numOfList_blank.get(i);
//					titles_blank.remove(j);
					for(int n = titles_blank.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_blank.remove(n);
						}
					}
					
				}
				for (Title new_titles_blank : titles_blank) {
					System.out.println("new_titles_blank==>" + new_titles_blank.getTitle_id()
							+ ":" + new_titles_blank.getTitle_time() + ":"
							+ new_titles_blank.getTitle_num());
				}
				session.setAttribute("customTitles_blank", customTitles_blank);
				session.setAttribute("titles_blank", titles_blank);
			}else{
				session.setAttribute("customTitles_blank", titles_blank_first);
				session.setAttribute("titles_blank", titles_blank);
			}
		}
		
		/*
		 * 填空题随机生成
		 */
		List<Title> customTitles_quiz = new ArrayList<Title>();
		//判断用户是否选择了选择题
		if(Integer.parseInt(quiz_num)>0){
			List<Title> titles_quiz_first = customDao.QueryTypeByCondition(pro, "3");
			List<Title> titles_quiz = new ArrayList<Title>();
			//判断是否有符合类型的题目且题目数量大于等于用户需求
			if(titles_quiz_first.size()>0 && titles_quiz_first.size() >= Integer.parseInt(quiz_num)){
				titles_quiz = s.sort_List(titles_quiz_first);
				System.out.println("****titles_quiz*******");
				for (Title title_quiz : titles_quiz) {
					System.out.println("titles_quiz==>" + title_quiz.getTitle_id()
							+ ":" + title_quiz.getTitle_time() + ":"
							+ title_quiz.getTitle_num());
				}
				HashSet<Integer> ranNum = a.getRanNum(titles_quiz.size(),Integer.parseInt(quiz_num));
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_quiz.add(titles_quiz.get(rannum));
					numOfList_quiz.add(rannum);
				}
				System.out.println("numOfList_quiz.size()===>"+numOfList_quiz.size());
				for(int i = numOfList_quiz.size()-1; i >= 0 ; i--){
					int j = numOfList_quiz.get(i);
//					titles_quiz.remove(j);
					for(int n = titles_quiz.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_quiz.remove(n);
						}
					}
				}
				for (Title new_titles_quiz : titles_quiz) {
					System.out.println("new_titles_quiz==>" + new_titles_quiz.getTitle_id()
							+ ":" + new_titles_quiz.getTitle_time() + ":"
							+ new_titles_quiz.getTitle_num());
				}
				session.setAttribute("customTitles_quiz", customTitles_quiz);
				session.setAttribute("titles_quiz", titles_quiz);
			}else{
				session.setAttribute("customTitles_quiz", titles_quiz_first);
				session.setAttribute("titles_quiz", titles_quiz);
			}
		}
//		/*
//		 * 选择题中等随机生成
//		 */
//		List<Title> customTitles_choice_middle = new ArrayList<Title>();
//		if(Integer.parseInt(choice_middle_num)>0){
//			List<Title> titles_middle_choice = customDao.QueryTypeByCondition(pro, "0","1");
//			List<Title> titles_Choice_middle = new ArrayList<Title>();
//			if(titles_middle_choice.size()>0 && titles_middle_choice.size() >= Integer.parseInt(choice_middle_num)){
//				titles_Choice_middle = s.sort_List(titles_middle_choice);
//				System.out.println("****titles_Choice_middle*******");
//				for (Title title_Choice_middle : titles_Choice_middle) {
//					System.out.println("title_Choice_middle==>" + title_Choice_middle.getTitle_id()
//							+ ":" + title_Choice_middle.getTitle_time() + ":"
//							+ title_Choice_middle.getTitle_num());
//				}
//				HashSet<Integer> ranNum = a.getRanNum(titles_Choice_middle.size(),Integer.parseInt(choice_middle_num));
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_choice_middle.add(titles_Choice_middle.get(rannum));
//					numOfList_choice_middle.add(rannum);
//				}
//				System.out.println("numOfList_choice_middle.size()===>"+numOfList_choice_middle.size());
//				for(int i = numOfList_choice_middle.size()-1; i >= 0 ; i--){
//					int j = numOfList_choice_middle.get(i);
//					titles_Choice_middle.remove(j);
//				}
//				session.setAttribute("customTitles_choice_middle", customTitles_choice_middle);
//				session.setAttribute("titles_Choice_middle", titles_Choice_middle);			
//			}else{
//				session.setAttribute("customTitles_choice_middle", titles_middle_choice);
//				session.setAttribute("titles_Choice_middle", titles_Choice_middle);
//			}
//			
//		}
//		
//		/*
//		 * 选择题困难随机生成
//		 */
//		List<Title> customTitles_choice_hard = new ArrayList<Title>();
//		if(Integer.parseInt(choice_hard_num)>0){
//			List<Title> titles_hard_choice = customDao.QueryTypeByCondition(pro, "0","2");
//			List<Title> titles_Choice_hard = new ArrayList<Title>();
//			if(titles_hard_choice.size()>0 && titles_hard_choice.size() >= Integer.parseInt(choice_hard_num)){
//				titles_Choice_hard = s.sort_List(titles_hard_choice);
//				System.out.println("****titles_Choice_hard*******");
//				for (Title title_Choice_hard : titles_Choice_hard) {
//					System.out.println("title_Choice_hard==>" + title_Choice_hard.getTitle_id()
//							+ ":" + title_Choice_hard.getTitle_time() + ":"
//							+ title_Choice_hard.getTitle_num());
//				}
//				HashSet<Integer> ranNum = a.getRanNum(titles_Choice_hard.size(),Integer.parseInt(choice_hard_num));
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_choice_hard.add(titles_Choice_hard.get(rannum));
//					numOfList_choice_hard.add(rannum);
//				}
//				System.out.println("numOfList_choice_hard.size()===>"+numOfList_choice_hard.size());
//				for(int i = numOfList_choice_hard.size()-1; i >= 0 ; i--){
//					int j = numOfList_choice_hard.get(i);
//					titles_Choice_hard.remove(j);
//				}
//				session.setAttribute("customTitles_choice_hard", customTitles_choice_hard);
//				session.setAttribute("titles_Choice_hard", titles_Choice_hard);			
//			}else{
//				session.setAttribute("customTitles_choice_hard", titles_hard_choice);
//				session.setAttribute("titles_Choice_hard", titles_Choice_hard);
//			}
//			
//		}
//		
////		/*
////		 * 多项选择题简单随机生成
////		 */
////		List<Title> customTitles_mutilchoice_easy = new ArrayList<Title>();
////		//判断用户是否选择了简单类型
////		if(Integer.parseInt(mutilchoice_easy_num)>0){
////			List<Title> titles_easy_mutilchoice = customDao.QueryTypeByCondition(pro, "1","0");
////			List<Title> titles_mutilchoice_easy = new ArrayList<Title>();
////			//判断是否有符合类型的题目且题目数量大于等于用户需求
////			if(titles_easy_mutilchoice.size()>0 && titles_easy_mutilchoice.size() >= Integer.parseInt(mutilchoice_easy_num)){
////				titles_mutilchoice_easy = s.sort_List(titles_easy_mutilchoice);
////				System.out.println("****titles_mutilchoice_easy*******");
////				for (Title title_mutilchoice_Easy : titles_mutilchoice_easy) {
////					System.out.println("titles_mutilchoice_easy==>" + title_mutilchoice_Easy.getTitle_id()
////							+ ":" + title_mutilchoice_Easy.getTitle_time() + ":"
////							+ title_mutilchoice_Easy.getTitle_num());
////				}
////				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice_easy.size(),Integer.parseInt(mutilchoice_easy_num));
////				Iterator<Integer> it = ranNum.iterator();
////				while (it.hasNext()) {
////					int rannum = it.next();
////					System.out.println("rannum===>" + rannum);
////					customTitles_mutilchoice_easy.add(titles_mutilchoice_easy.get(rannum));
////					numOfList_mutilchoice_easy.add(rannum);
////				}
////				System.out.println("numOfList_mutilchoice_easy.size()===>"+numOfList_mutilchoice_easy.size());
////				for(int i = numOfList_mutilchoice_easy.size()-1; i >= 0 ; i--){
////					int j = numOfList_mutilchoice_easy.get(i);
////					titles_mutilchoice_easy.remove(j);
////				}
////				session.setAttribute("customTitles_mutilchoice_easy", customTitles_mutilchoice_easy);
////				session.setAttribute("titles_mutilchoice_easy", titles_mutilchoice_easy);
////			}else{
////				session.setAttribute("customTitles_mutilchoice_easy", titles_easy_mutilchoice);
////				session.setAttribute("titles_mutilchoice_easy", titles_mutilchoice_easy);
////			}
////		}
////		
////		/*
////		 * 多项选择题中等随机生成
////		 */
////		List<Title> customTitles_mutilchoice_middle = new ArrayList<Title>();
////		if(Integer.parseInt(mutilchoice_middle_num)>0){
////			List<Title> titles_middle_mutilchoice = customDao.QueryTypeByCondition(pro, "1","1");
////			List<Title> titles_mutilchoice_middle = new ArrayList<Title>();
////			if(titles_middle_mutilchoice.size()>0 && titles_middle_mutilchoice.size() >= Integer.parseInt(mutilchoice_middle_num)){
////				titles_mutilchoice_middle = s.sort_List(titles_middle_mutilchoice);
////				System.out.println("****titles_mutilchoice_middle*******");
////				for (Title title_mutilchoice_middle : titles_mutilchoice_middle) {
////					System.out.println("title_mutilchoice_middle==>" + title_mutilchoice_middle.getTitle_id()
////							+ ":" + title_mutilchoice_middle.getTitle_time() + ":"
////							+ title_mutilchoice_middle.getTitle_num());
////				}
////				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice_middle.size(),Integer.parseInt(mutilchoice_middle_num));
////				Iterator<Integer> it = ranNum.iterator();
////				while (it.hasNext()) {
////					int rannum = it.next();
////					System.out.println("rannum===>" + rannum);
////					customTitles_mutilchoice_middle.add(titles_mutilchoice_middle.get(rannum));
////					numOfList_mutilchoice_middle.add(rannum);
////				}
////				System.out.println("numOfList_mutilchoice_middle.size()===>"+numOfList_mutilchoice_middle.size());
////				for(int i = numOfList_mutilchoice_middle.size()-1; i >= 0 ; i--){
////					int j = numOfList_mutilchoice_middle.get(i);
////					titles_mutilchoice_middle.remove(j);
////				}
////				session.setAttribute("customTitles_mutilchoice_middle", customTitles_mutilchoice_middle);
////				session.setAttribute("titles_mutilchoice_middle", titles_mutilchoice_middle);			
////			}else{
////				session.setAttribute("customTitles_mutilchoice_middle", titles_middle_mutilchoice);
////				session.setAttribute("titles_mutilchoice_middle", titles_mutilchoice_middle);
////			}
////			
////		}
////		/*
////		 * 多项选择题困难随机生成
////		 */
////		List<Title> customTitles_mutilchoice_hard = new ArrayList<Title>();
////		if(Integer.parseInt(mutilchoice_hard_num)>0){
////			List<Title> titles_hard_mutilchoice = customDao.QueryTypeByCondition(pro, "1","2");
////			List<Title> titles_mutilchoice_hard = new ArrayList<Title>();
////			if(titles_hard_mutilchoice.size()>0 && titles_hard_mutilchoice.size() >= Integer.parseInt(mutilchoice_hard_num)){
////				titles_mutilchoice_hard = s.sort_List(titles_hard_mutilchoice);
////				System.out.println("****titles_mutilchoice_hard*******");
////				for (Title title_mutilchoice_hard : titles_mutilchoice_hard) {
////					System.out.println("title_mutilchoice_hard==>" + title_mutilchoice_hard.getTitle_id()
////							+ ":" + title_mutilchoice_hard.getTitle_time() + ":"
////							+ title_mutilchoice_hard.getTitle_num());
////				}
////				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice_hard.size(),Integer.parseInt(mutilchoice_hard_num));
////				Iterator<Integer> it = ranNum.iterator();
////				while (it.hasNext()) {
////					int rannum = it.next();
////					System.out.println("rannum===>" + rannum);
////					customTitles_mutilchoice_hard.add(titles_mutilchoice_hard.get(rannum));
////					numOfList_mutilchoice_hard.add(rannum);
////				}
////				System.out.println("numOfList_mutilchoice_hard.size()===>"+numOfList_mutilchoice_hard.size());
////				for(int i = numOfList_mutilchoice_hard.size()-1; i >= 0 ; i--){
////					int j = numOfList_mutilchoice_hard.get(i);
////					titles_mutilchoice_hard.remove(j);
////				}
////				session.setAttribute("customTitles_mutilchoice_hard", customTitles_mutilchoice_hard);
////				session.setAttribute("titles_mutilchoice_hard", titles_mutilchoice_hard);			
////			}else{
////				session.setAttribute("customTitles_mutilchoice_hard", titles_hard_mutilchoice);
////				session.setAttribute("titles_mutilchoice_hard", titles_mutilchoice_hard);
////			}
////		}
//		/*
//		 * 填空题简单随机生成
//		 */
//		List<Title> customTitles_blank_easy = new ArrayList<Title>();
//		//判断用户是否选择了简单类型
//		if(Integer.parseInt(blank_easy_num)>0){
//			List<Title> titles_easy_blank = customDao.QueryTypeByCondition(pro, "2","0");
//			List<Title> titles_blank_easy = new ArrayList<Title>();
//			//判断是否有符合类型的题目且题目数量大于等于用户需求
//			if(titles_easy_blank.size()>0 && titles_easy_blank.size() >= Integer.parseInt(blank_easy_num)){
//				titles_blank_easy = s.sort_List(titles_easy_blank);
//				System.out.println("****titles_blank_easy*******");
//				for (Title title_blank_Easy : titles_blank_easy) {
//					System.out.println("titles_blank_easy==>" + title_blank_Easy.getTitle_id()
//							+ ":" + title_blank_Easy.getTitle_time() + ":"
//							+ title_blank_Easy.getTitle_num());
//				}
//				HashSet<Integer> ranNum = a.getRanNum(titles_blank_easy.size(),Integer.parseInt(blank_easy_num));
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_blank_easy.add(titles_blank_easy.get(rannum));
//					numOfList_blank_easy.add(rannum);
//				}
//				System.out.println("numOfList_blank_easy.size()===>"+numOfList_blank_easy.size());
//				for(int i = numOfList_blank_easy.size()-1; i >= 0 ; i--){
//					int j = numOfList_blank_easy.get(i);
//					titles_blank_easy.remove(j);
//				}
//				session.setAttribute("customTitles_blank_easy", customTitles_blank_easy);
//				session.setAttribute("titles_blank_easy", titles_blank_easy);
//			}else{
//				session.setAttribute("customTitles_blank_easy", titles_easy_blank);
//				session.setAttribute("titles_blank_easy", titles_blank_easy);
//			}
//		}
//		/*
//		 * 填空题中等随机生成
//		 */
//		List<Title> customTitles_blank_middle = new ArrayList<Title>();
//		if(Integer.parseInt(blank_middle_num)>0){
//			List<Title> titles_middle_blank = customDao.QueryTypeByCondition(pro, "2","1");
//			List<Title> titles_blank_middle = new ArrayList<Title>();
//			if(titles_middle_blank.size()>0 && titles_middle_blank.size() >= Integer.parseInt(blank_middle_num)){
//				titles_blank_middle = s.sort_List(titles_middle_blank);
//				System.out.println("****titles_blank_middle*******");
//				for (Title title_blank_middle : titles_blank_middle) {
//					System.out.println("title_blank_middle==>" + title_blank_middle.getTitle_id()
//							+ ":" + title_blank_middle.getTitle_time() + ":"
//							+ title_blank_middle.getTitle_num());
//				}
//				HashSet<Integer> ranNum = a.getRanNum(titles_blank_middle.size(),Integer.parseInt(blank_middle_num));
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_blank_middle.add(titles_blank_middle.get(rannum));
//					numOfList_blank_middle.add(rannum);
//				}
//				System.out.println("numOfList_blank_middle.size()===>"+numOfList_blank_middle.size());
//				for(int i = numOfList_blank_middle.size()-1; i >= 0 ; i--){
//					int j = numOfList_blank_middle.get(i);
//					titles_blank_middle.remove(j);
//				}
//				session.setAttribute("customTitles_blank_middle", customTitles_blank_middle);
//				session.setAttribute("titles_blank_middle", titles_blank_middle);			
//			}else{
//				session.setAttribute("customTitles_blank_middle", titles_middle_blank);
//				session.setAttribute("titles_blank_middle", titles_blank_middle);
//			}
//			
//		}
//		/*
//		 * 填空题困难随机生成
//		 */
//		List<Title> customTitles_blank_hard = new ArrayList<Title>();
//		if(Integer.parseInt(blank_hard_num)>0){
//			List<Title> titles_hard_blank = customDao.QueryTypeByCondition(pro, "2","2");
//			List<Title> titles_blank_hard = new ArrayList<Title>();
//			if(titles_hard_blank.size()>0 && titles_hard_blank.size() >= Integer.parseInt(blank_hard_num)){
//				titles_blank_hard = s.sort_List(titles_hard_blank);
//				System.out.println("****titles_blank_hard*******");
//				for (Title title_blank_hard : titles_blank_hard) {
//					System.out.println("title_blank_hard==>" + title_blank_hard.getTitle_id()
//							+ ":" + title_blank_hard.getTitle_time() + ":"
//							+ title_blank_hard.getTitle_num());
//				}
//				HashSet<Integer> ranNum = a.getRanNum(titles_blank_hard.size(),Integer.parseInt(blank_hard_num));
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_blank_hard.add(titles_blank_hard.get(rannum));
//					numOfList_blank_hard.add(rannum);
//				}
//				System.out.println("numOfList_blank_hard.size()===>"+numOfList_blank_hard.size());
//				for(int i = numOfList_blank_hard.size()-1; i >= 0 ; i--){
//					int j = numOfList_blank_hard.get(i);
//					titles_blank_hard.remove(j);
//				}
//				session.setAttribute("customTitles_blank_hard", customTitles_blank_hard);
//				session.setAttribute("titles_blank_hard", titles_blank_hard);			
//			}else{
//				session.setAttribute("customTitles_blank_hard", titles_hard_blank);
//				session.setAttribute("titles_blank_hard", titles_blank_hard);
//			}
//		}
		User user_login = (User) session.getAttribute("user_login");
		PageController pc = (PageController) session.getAttribute("pc");
		List<Title> titles1 = collectDao.testUserTitles(user_login.getUser_id());
		session.setAttribute("titles", titles1);
		List<Title> customTitles_choice2=(List<Title>) session.getAttribute("customTitles_choice");
		List<Integer> isColl=new ArrayList<Integer>();
		for(Title t1:customTitles_choice2){
			boolean flag=false;
		for(Title t:titles1){
			if(t1.getTitle_id()==t.getTitle_id()){
				flag=true;
			}
		}if(flag){
			isColl.add(1);
		}
		else{
			isColl.add(0);
		}
		}
		session.setAttribute("isColl", isColl);
		
		return "paper_create.jsp";
	}

	/*
	 *其他推荐方案
	 */
	@RequestMapping("other_Choice")
	public String otherChoice(HttpSession session){
		MathRandom a = new MathRandom();
		String choice_num = (String) session.getAttribute("choice_num");
		String blank_num = (String) session.getAttribute("blank_num");
		String quiz_num = (String) session.getAttribute("quiz_num");
		String mutilchoice_num = (String) session.getAttribute("mutilchoice_num");
		/*
		 *  选择题其他推荐方案 
		 */
		if(Integer.parseInt(choice_num)>0){
			List<Title> titles_Choice = (List<Title>) session.getAttribute("titles_Choice");
			if(titles_Choice.size() >= Integer.parseInt(choice_num)){
//				System.out.println("****other choice***");
//				for(Title title : titles_Choice){
//					System.out.println(title.getTitle_id());
//				}
				List<Integer> numOfList_choice = new ArrayList<Integer>();
				HashSet<Integer> ranNum = a.getRanNum(titles_Choice.size(),Integer.parseInt(choice_num));
				List<Title> customTitles_choice = new ArrayList<Title>();
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_choice.add(titles_Choice.get(rannum));
					numOfList_choice.add(rannum);
				}
				System.out.println("numOfList_choice.size()===>"+numOfList_choice.size());
				for(int i = numOfList_choice.size()-1; i >= 0 ; i--){
					int j = numOfList_choice.get(i);
//					titles_Choice.remove(j);
					for(int n = titles_Choice.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_Choice.remove(n);
						}
					}
				}
				session.setAttribute("customTitles_choice", customTitles_choice);
				session.setAttribute("titles_Choice", titles_Choice);
			}else{
				session.setAttribute("customTitles_choice", titles_Choice);
			}
		}
		/*
		 *  多项选择题其他推荐方案 
		 */
		if(Integer.parseInt(mutilchoice_num)>0){
			List<Title> titles_mutilchoice = (List<Title>) session.getAttribute("titles_mutilchoice");
			if(titles_mutilchoice.size() >= Integer.parseInt(mutilchoice_num)){
//				System.out.println("****other mutilchoice***");
//				for(Title title : titles_mutilchoice){
//					System.out.println(title.getTitle_id());
//				}
				List<Integer> numOfList_mutilchoice = new ArrayList<Integer>();
				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice.size(),Integer.parseInt(mutilchoice_num));
				List<Title> customTitles_mutilchoice = new ArrayList<Title>();
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_mutilchoice.add(titles_mutilchoice.get(rannum));
					numOfList_mutilchoice.add(rannum);
				}
				System.out.println("numOfList_mutilchoice.size()===>"+numOfList_mutilchoice.size());
				for(int i = numOfList_mutilchoice.size()-1; i >= 0 ; i--){
					int j = numOfList_mutilchoice.get(i);
//					titles_mutilchoice.remove(j);
					for(int n = titles_mutilchoice.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_mutilchoice.remove(n);
						}
					}
				}
				session.setAttribute("customTitles_mutilchoice", customTitles_mutilchoice);
				session.setAttribute("titles_mutilchoice", titles_mutilchoice);
			}else{
				session.setAttribute("customTitles_mutilchoice", titles_mutilchoice);
			}
		}
		/*
		 *  判断题其他推荐方案 
		 */
		if(Integer.parseInt(blank_num)>0){
			List<Title> titles_blank = (List<Title>) session.getAttribute("titles_blank");
			if(titles_blank.size() >= Integer.parseInt(blank_num)){
//				System.out.println("****other blank***");
//				for(Title title : titles_blank){
//					System.out.println(title.getTitle_id());
//				}
				List<Integer> numOfList_blank = new ArrayList<Integer>();
				HashSet<Integer> ranNum = a.getRanNum(titles_blank.size(),Integer.parseInt(blank_num));
				List<Title> customTitles_blank = new ArrayList<Title>();
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_blank.add(titles_blank.get(rannum));
					numOfList_blank.add(rannum);
				}
				System.out.println("numOfList_blank.size()===>"+numOfList_blank.size());
				for(int i = numOfList_blank.size()-1; i >= 0 ; i--){
					int j = numOfList_blank.get(i);
//					titles_blank.remove(j);
					for(int n = titles_blank.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_blank.remove(n);
						}
					}
				}
				session.setAttribute("customTitles_blank", customTitles_blank);
				session.setAttribute("titles_blank", titles_blank);
			}else{
				session.setAttribute("customTitles_blank", titles_blank);
			}
		}
		
		/*
		 *  填空其他推荐方案 
		 */
		if(Integer.parseInt(quiz_num)>0){
			List<Title> titles_quiz = (List<Title>) session.getAttribute("titles_quiz");
			if(titles_quiz.size() >= Integer.parseInt(quiz_num)){
//				System.out.println("****other quiz***");
//				for(Title title : titles_quiz){
//					System.out.println(title.getTitle_id());
//				}
				List<Integer> numOfList_quiz = new ArrayList<Integer>();
				HashSet<Integer> ranNum = a.getRanNum(titles_quiz.size(),Integer.parseInt(quiz_num));
				List<Title> customTitles_quiz = new ArrayList<Title>();
				Iterator<Integer> it = ranNum.iterator();
				while (it.hasNext()) {
					int rannum = it.next();
					System.out.println("rannum===>" + rannum);
					customTitles_quiz.add(titles_quiz.get(rannum));
					numOfList_quiz.add(rannum);
				}
				System.out.println("numOfList_quiz.size()===>"+numOfList_quiz.size());
				for(int i = numOfList_quiz.size()-1; i >= 0 ; i--){
					int j = numOfList_quiz.get(i);
//					titles_quiz.remove(j);
					for(int n = titles_quiz.size()-1 ; n>=0 ; n--){
						if(j==n){
							titles_quiz.remove(n);
						}
					}
				}
				session.setAttribute("customTitles_quiz", customTitles_quiz);
				session.setAttribute("titles_quiz", titles_quiz);
			}else{
				session.setAttribute("customTitles_quiz", titles_quiz);
			}
		}
		
//		/*
//		 * 选择题中等其他推荐方案
//		 */
//		if(Integer.parseInt(choice_middle_num)>0){
//			List<Title> titles_Choice_middle = (List<Title>) session.getAttribute("titles_Choice_middle");
//			if(titles_Choice_middle.size() >= Integer.parseInt(choice_middle_num)){
////				System.out.println("****other choice***");
////				for(Title title : titles_Choice_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_middle_choice = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_Choice_middle.size(),Integer.parseInt(choice_middle_num));
//				List<Title> customTitles_choice_middle = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_choice_middle.add(titles_Choice_middle.get(rannum));
//					numOfList_middle_choice.add(rannum);
//				}
//				System.out.println("numOfList_middle_choice.size()===>"+numOfList_middle_choice.size());
//				for(int i = numOfList_middle_choice.size()-1; i >= 0 ; i--){
//					int j = numOfList_middle_choice.get(i);
//					titles_Choice_middle.remove(j);
//				}
//				session.setAttribute("customTitles_choice_middle", customTitles_choice_middle);
//				session.setAttribute("titles_Choice_middle", titles_Choice_middle);
//			}else{
//				session.setAttribute("customTitles_choice_middle", titles_Choice_middle);
//			}
//		}
//		
//		/*
//		 * 选择题困难其他推荐方案
//		 */
//		if(Integer.parseInt(choice_hard_num)>0){
//			List<Title> titles_Choice_hard = (List<Title>) session.getAttribute("titles_Choice_hard");
//			if(titles_Choice_hard.size() >= Integer.parseInt(choice_hard_num)){
////				System.out.println("****other choice***");
////				for(Title title : titles_Choice_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_hard_choice = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_Choice_hard.size(),Integer.parseInt(choice_hard_num));
//				List<Title> customTitles_choice_hard = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_choice_hard.add(titles_Choice_hard.get(rannum));
//					numOfList_hard_choice.add(rannum);
//				}
//				System.out.println("numOfList_hard_choice.size()===>"+numOfList_hard_choice.size());
//				for(int i = numOfList_hard_choice.size()-1; i >= 0 ; i--){
//					int j = numOfList_hard_choice.get(i);
//					titles_Choice_hard.remove(j);
//				}
//				session.setAttribute("customTitles_choice_hard", customTitles_choice_hard);
//				session.setAttribute("titles_Choice_hard", titles_Choice_hard);
//			}else{
//				session.setAttribute("customTitles_choice_hard", titles_Choice_hard);
//			}
//		}
//		
//		/*
//		 *  多项选择题简单其他推荐方案 
//		 */
//		if(Integer.parseInt(mutilchoice_easy_num)>0){
//			List<Title> titles_mutilchoice_easy = (List<Title>) session.getAttribute("titles_mutilchoice_easy");
//			if(titles_mutilchoice_easy.size() >= Integer.parseInt(mutilchoice_easy_num)){
////				System.out.println("****other mutilchoice***");
////				for(Title title : titles_mutilchoice_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_easy_mutilchoice = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice_easy.size(),Integer.parseInt(mutilchoice_easy_num));
//				List<Title> customTitles_mutilchoice_easy = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_mutilchoice_easy.add(titles_mutilchoice_easy.get(rannum));
//					numOfList_easy_mutilchoice.add(rannum);
//				}
//				System.out.println("numOfList_easy_mutilchoice.size()===>"+numOfList_easy_mutilchoice.size());
//				for(int i = numOfList_easy_mutilchoice.size()-1; i >= 0 ; i--){
//					int j = numOfList_easy_mutilchoice.get(i);
//					titles_mutilchoice_easy.remove(j);
//				}
//				session.setAttribute("customTitles_mutilchoice_easy", customTitles_mutilchoice_easy);
//				session.setAttribute("titles_mutilchoice_easy", titles_mutilchoice_easy);
//			}else{
//				session.setAttribute("customTitles_mutilchoice_easy", titles_mutilchoice_easy);
//			}
//		}
//		/*
//		 * 多项选择题中等其他推荐方案
//		 */
//		if(Integer.parseInt(mutilchoice_middle_num)>0){
//			List<Title> titles_mutilchoice_middle = (List<Title>) session.getAttribute("titles_mutilchoice_middle");
//			if(titles_mutilchoice_middle.size() >= Integer.parseInt(mutilchoice_middle_num)){
////				System.out.println("****other mutilchoice***");
////				for(Title title : titles_mutilchoice_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_middle_mutilchoice = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice_middle.size(),Integer.parseInt(mutilchoice_middle_num));
//				List<Title> customTitles_mutilchoice_middle = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_mutilchoice_middle.add(titles_mutilchoice_middle.get(rannum));
//					numOfList_middle_mutilchoice.add(rannum);
//				}
//				System.out.println("numOfList_middle_mutilchoice.size()===>"+numOfList_middle_mutilchoice.size());
//				for(int i = numOfList_middle_mutilchoice.size()-1; i >= 0 ; i--){
//					int j = numOfList_middle_mutilchoice.get(i);
//					titles_mutilchoice_middle.remove(j);
//				}
//				session.setAttribute("customTitles_mutilchoice_middle", customTitles_mutilchoice_middle);
//				session.setAttribute("titles_mutilchoice_middle", titles_mutilchoice_middle);
//			}else{
//				session.setAttribute("customTitles_mutilchoice_middle", titles_mutilchoice_middle);
//			}
//		}
//		/*
//		 * 多项选择题困难其他推荐方案
//		 */
//		if(Integer.parseInt(mutilchoice_hard_num)>0){
//			List<Title> titles_mutilchoice_hard = (List<Title>) session.getAttribute("titles_mutilchoice_hard");
//			if(titles_mutilchoice_hard.size() >= Integer.parseInt(mutilchoice_hard_num)){
////				System.out.println("****other mutilchoice***");
////				for(Title title : titles_mutilchoice_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_hard_mutilchoice = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_mutilchoice_hard.size(),Integer.parseInt(mutilchoice_hard_num));
//				List<Title> customTitles_mutilchoice_hard = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_mutilchoice_hard.add(titles_mutilchoice_hard.get(rannum));
//					numOfList_hard_mutilchoice.add(rannum);
//				}
//				System.out.println("numOfList_hard_mutilchoice.size()===>"+numOfList_hard_mutilchoice.size());
//				for(int i = numOfList_hard_mutilchoice.size()-1; i >= 0 ; i--){
//					int j = numOfList_hard_mutilchoice.get(i);
//					titles_mutilchoice_hard.remove(j);
//				}
//				session.setAttribute("customTitles_mutilchoice_hard", customTitles_mutilchoice_hard);
//				session.setAttribute("titles_mutilchoice_hard", titles_mutilchoice_hard);
//			}else{
//				session.setAttribute("customTitles_mutilchoice_hard", titles_mutilchoice_hard);
//			}
//		}
//		/*
//		 *  填空题简单其他推荐方案 
//		 */
//		if(Integer.parseInt(blank_easy_num)>0){
//			List<Title> titles_blank_easy = (List<Title>) session.getAttribute("titles_blank_easy");
//			if(titles_blank_easy.size() >= Integer.parseInt(blank_easy_num)){
////				System.out.println("****other blank***");
////				for(Title title : titles_blank_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_easy_blank = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_blank_easy.size(),Integer.parseInt(blank_easy_num));
//				List<Title> customTitles_blank_easy = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_blank_easy.add(titles_blank_easy.get(rannum));
//					numOfList_easy_blank.add(rannum);
//				}
//				System.out.println("numOfList_easy_blank.size()===>"+numOfList_easy_blank.size());
//				for(int i = numOfList_easy_blank.size()-1; i >= 0 ; i--){
//					int j = numOfList_easy_blank.get(i);
//					titles_blank_easy.remove(j);
//				}
//				session.setAttribute("customTitles_blank_easy", customTitles_blank_easy);
//				session.setAttribute("titles_blank_easy", titles_blank_easy);
//			}else{
//				session.setAttribute("customTitles_blank_easy", titles_blank_easy);
//			}
//		}
//		/*
//		 * 填空题中等其他推荐方案
//		 */
//		if(Integer.parseInt(blank_middle_num)>0){
//			List<Title> titles_blank_middle = (List<Title>) session.getAttribute("titles_blank_middle");
//			if(titles_blank_middle.size() >= Integer.parseInt(blank_middle_num)){
////				System.out.println("****other blank***");
////				for(Title title : titles_blank_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_middle_blank = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_blank_middle.size(),Integer.parseInt(blank_middle_num));
//				List<Title> customTitles_blank_middle = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_blank_middle.add(titles_blank_middle.get(rannum));
//					numOfList_middle_blank.add(rannum);
//				}
//				System.out.println("numOfList_middle_blank.size()===>"+numOfList_middle_blank.size());
//				for(int i = numOfList_middle_blank.size()-1; i >= 0 ; i--){
//					int j = numOfList_middle_blank.get(i);
//					titles_blank_middle.remove(j);
//				}
//				session.setAttribute("customTitles_blank_middle", customTitles_blank_middle);
//				session.setAttribute("titles_blank_middle", titles_blank_middle);
//			}else{
//				session.setAttribute("customTitles_blank_middle", titles_blank_middle);
//			}
//		}
//		/*
//		 * 填空题困难其他推荐方案
//		 */
//		if(Integer.parseInt(blank_hard_num)>0){
//			List<Title> titles_blank_hard = (List<Title>) session.getAttribute("titles_blank_hard");
//			if(titles_blank_hard.size() >= Integer.parseInt(blank_hard_num)){
////				System.out.println("****other blank***");
////				for(Title title : titles_blank_easy){
////					System.out.println(title.getTitle_id());
////				}
//				List<Integer> numOfList_hard_blank = new ArrayList<Integer>();
//				HashSet<Integer> ranNum = a.getRanNum(titles_blank_hard.size(),Integer.parseInt(blank_hard_num));
//				List<Title> customTitles_blank_hard = new ArrayList<Title>();
//				Iterator<Integer> it = ranNum.iterator();
//				while (it.hasNext()) {
//					int rannum = it.next();
//					System.out.println("rannum===>" + rannum);
//					customTitles_blank_hard.add(titles_blank_hard.get(rannum));
//					numOfList_hard_blank.add(rannum);
//				}
//				System.out.println("numOfList_hard_blank.size()===>"+numOfList_hard_blank.size());
//				for(int i = numOfList_hard_blank.size()-1; i >= 0 ; i--){
//					int j = numOfList_hard_blank.get(i);
//					titles_blank_hard.remove(j);
//				}
//				session.setAttribute("customTitles_blank_hard", customTitles_blank_hard);
//				session.setAttribute("titles_blank_hard", titles_blank_hard);
//			}else{
//				session.setAttribute("customTitles_blank_hard", titles_blank_hard);
//			}
//		}
		
		return "paper_create.jsp";
	}
	
	/*
	 * 查看题目相关信息
	 */
	@RequestMapping("see_information")
	public String seeImformation(String title_id, HttpSession session){
		Title title = customDao.queryTitleById(Integer.parseInt(title_id));
		session.setAttribute("title_infomation", title);
		return "title_information.jsp";
	}
}
