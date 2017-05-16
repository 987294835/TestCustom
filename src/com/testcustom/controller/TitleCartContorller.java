package com.testcustom.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.CustomDao;
import com.testcustom.dao.JobManageDao;
import com.testcustom.dao.TitleCartDao;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;

@Controller
public class TitleCartContorller {
	
	@Resource
	private TitleCartDao titleCartDao;
	public void setTitleCartDao(TitleCartDao titleCartDao) {
		this.titleCartDao = titleCartDao;
	}
	@Resource
	private JobManageDao jobManageDao;
	public void setJobManageDao(JobManageDao jobManageDao) {
		this.jobManageDao = jobManageDao;
	}


	/**
	 * 添加试题到时试题筐
	 */
	@RequestMapping("add_title_to_cart")
	public String addTitleToCart(String[] choice_ids,String[] mutilchoice_ids, 
			                     String[] blank_ids, String[] quiz_ids, HttpSession session){
		/**
		 * 判断用户是否有购物车，没有给用户一个购物车
		 */
		System.out.println("*****添加试题到购物车*****");
		List<Title> cart_choice_titles = (List<Title>) session.getAttribute("cart_choice_titles");
		if(cart_choice_titles == null){
			cart_choice_titles = new ArrayList<Title>();
		}
		
		/**
		 * 添加题到试题筐
		 */
		if(choice_ids != null)
		{
			boolean check_choice;
			for(String choice_title_id : choice_ids){
				check_choice = true;
				for(int i = 0; i<cart_choice_titles.size(); i++){
					if(Integer.parseInt(choice_title_id) == cart_choice_titles.get(i).getTitle_id()){
						check_choice = false;
						break;
					}
				}
				if(check_choice){
					Title choice_title = titleCartDao.findTitleById(Integer.parseInt(choice_title_id));
					cart_choice_titles.add(choice_title);
				}			
			}
			session.setAttribute("cart_choice_titles", cart_choice_titles);
			List<Title> c = (List<Title>) session.getAttribute("cart_choice_titles");
			for(Title cc : c){
				System.out.println(cc.getTitle_type());
			}
			
		}
	
		
		return "paper_create.jsp";
	}

	
	/**
	 * 删除试题筐的试题
	 */
	@RequestMapping("cart_remove_tilte")
	public String cartRemoveTitle(String choice_id, String mutilchoice_id, 
			String blank_id, String quiz_id, HttpSession session){
		
		if(choice_id != null){
			List<Title> cart_choice_titles = (List<Title>) session.getAttribute("cart_choice_titles");
			for(int i = 0; i<cart_choice_titles.size(); i++){
				if(Integer.parseInt(choice_id) == cart_choice_titles.get(i).getTitle_id()){
					cart_choice_titles.remove(i);
					break;
				}
			}
			session.setAttribute("cart_choice_titles", cart_choice_titles);
		}
		
		
		
		
		return "shopCart.jsp";
	}
	
	/**
	 *  把试题筐中的试题添加到数据库
	 */
	@RequestMapping("cart_input_history")
	public String cartInputHistory(HttpSession session, String exam_name){
		User user_login = (User) session.getAttribute("user_login");
		List<Integer> title_id = new ArrayList<Integer>();
		List<Title> get_cart_choice_titles = (List<Title>) session.getAttribute("cart_choice_titles");
		/**
		 * 把试题筐中的题目id添加到title_id集合中
		 */
		int score = 0;
		if(get_cart_choice_titles != null){
			for(Title cart_choice_title : get_cart_choice_titles){
				title_id.add(cart_choice_title.getTitle_id());
				if(cart_choice_title.getTitle_type()==0 || cart_choice_title.getTitle_type()==2){
					score += 3; //默认单选题、判断题3分/道
				}else if(cart_choice_title.getTitle_type()==1 || cart_choice_title.getTitle_type()==3){
					score += 5; //默认单选题、判断题5分/道
				}
			}
		}
	
		/**
		 * 将试题存放到数据库中
		 */
		if(title_id != null){
			titleCartDao.cartInputHistory(user_login.getUser_id(), exam_name, title_id);
			if(get_cart_choice_titles!=null){
				get_cart_choice_titles.clear();
			}
			
		}
		Exam newExam = titleCartDao.getNewExam(user_login.getUser_id());
		titleCartDao.setExamScore(score, newExam.getExam_id());
		/**
		 * 直接绑定岗位
		 */
		String job_id = (String) session.getAttribute("job_id");
		if(job_id != null){
			System.out.println("job_id===>"+job_id);
			
			jobManageDao.setJobExam(Integer.parseInt(job_id),newExam.getExam_id());
			job_id = null;
			session.setAttribute("job_id", job_id);
			
		}
		return "home.jsp";
	}
	

}
