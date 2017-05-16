package com.testcustom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testcustom.dao.CollectDao;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;

@Controller
public class CollectController {
	@Resource
	private CollectDao collectDao;

	public void setCollectDao(CollectDao collectDao) {
		this.collectDao = collectDao;
	}


	/*
	 * ����ղ�
	 */
	@RequestMapping("Collect")
	public void Collect(String choice_id, HttpSession session,
			HttpServletResponse response) throws IOException {
		boolean n = true;
		User user_login = (User) session.getAttribute("user_login");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int id = Integer.parseInt(choice_id);
		List<Title> titles = collectDao.testUserTitles(user_login.getUser_id());
		PrintWriter out = response.getWriter();
		for(Title title : titles){
		     if(title.getTitle_id() == id){
		    	 n = false;     /*n==false ֤�����ղع���*/
		    	 out.print("���ղ�");
		    	 return;
		    	 
		     }
		}
		/*System.out.println("title_id===>" + choice_id);*/
		if(n){
			collectDao.Testcollect(user_login.getUser_id(),id); /*�������ղ�*/
			
			out.print("success");
		}else{
			
			out.print("error");
		}
		
	}
	
	/*
	 * �鿴�ղ��б� ��ҳ��ʾ��
	 */
	@RequestMapping("Select_Collect")
	public String SelectCollect(String choice_id, HttpSession session,
			HttpServletResponse response){
		User user_login = (User) session.getAttribute("user_login");
		List<Title> titles = collectDao.testUserTitles(user_login.getUser_id());
		int totalRows = titles.size(); //��ȡ�ܵ��ղ���
		PageController pc = new PageController(totalRows, 1, 5);
		if(titles.size()!=0){
		List<Title> titles1 = collectDao.testUserTitles1(user_login.getUser_id(), pc);
		session.setAttribute("titles", titles1);
		session.setAttribute("pc", pc);
		}else{
			session.setAttribute("titles", titles);
		}
		return "collect.jsp";
	}
	

	/*
	 * ��ʾ�ղ��б�ҳ��ת����
	 */
	@RequestMapping("collect_page_change")
	public String collerctPageChange(String currPage, HttpSession session){
		User user_login = (User) session.getAttribute("user_login");
		PageController pc = (PageController) session.getAttribute("pc");
		int currpage=Integer.parseInt(currPage);
		pc.setCurrentPage(currpage);
		List<Title> titles1 = collectDao.testUserTitles1(user_login.getUser_id(), pc);
		session.setAttribute("titles", titles1);
		return "collect.jsp";
	}

	/*
	 * ɾ���ղ�
	 */
	@RequestMapping("Delect_Collect")
	public String DelectCollect(String title_id, HttpSession session){
		User user_login = (User) session.getAttribute("user_login");
		int tid = Integer.parseInt(title_id);
		collectDao.deletecollect(user_login.getUser_id(),tid);
		List<Title> titles = collectDao.testUserTitles(user_login.getUser_id());
		session.setAttribute("titles", titles);
		return "Select_Collect.do";
	}
	
}
