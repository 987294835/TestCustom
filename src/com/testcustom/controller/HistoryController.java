package com.testcustom.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.HistoryDao;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;
/**
 * 用户管理控制器
 * @author Administrator
 *
 */

@Controller
public class HistoryController {

	@Resource
	private HistoryDao historyDao;
	public void setHistoryDao(HistoryDao historyDao) {
		this.historyDao = historyDao;
	}
	/**
	 * 
	 * 查看用户试卷
	 */
	@RequestMapping("Select_exam")
	public String SelectExam(String choice_id, HttpSession session,
			HttpServletResponse response){
		User user_login = (User) session.getAttribute("user_login");
		List<Exam> exams = historyDao.testUserExams(user_login.getUser_id());
		int totalRows = exams.size();
		PageController pc = new PageController(totalRows, 1, 6);
		if(exams.size()!=0){
		List<Exam> exams1 = historyDao.testUserExams1(user_login.getUser_id(), pc);
		session.setAttribute("exams", exams1);
		session.setAttribute("pc", pc);
		}else{
			session.setAttribute("exams", exams);
		}
		return "history.jsp";
	}
	
	@RequestMapping("exam_page_change")
	public String examPageChange(String currPage, HttpSession session){
		User user_login = (User) session.getAttribute("user_login");
		PageController pc = (PageController) session.getAttribute("pc");
		int currpage=Integer.parseInt(currPage);
		pc.setCurrentPage(currpage);
		List<Exam> exams1 = historyDao.testUserExams1(user_login.getUser_id(), pc);
		session.setAttribute("exams", exams1);
		return "history.jsp";
	}
	/**
	 * 查看试卷试题
	 */
	@RequestMapping("query_exam_titles")
	public String queryExamTitles(String exam_id, HttpSession session, String exam_name){
		List<Title> exam_titles = historyDao.checkTitlesFromExam(Integer.parseInt(exam_id));
		Exam exam = historyDao.getExam(Integer.parseInt(exam_id));
		session.setAttribute("exam", exam);
		session.setAttribute("exam_titles", exam_titles);
		for(Title title : exam_titles){
			//System.out.println("title_id==>"+title.getTitle_id());
		}
		return "paper_list.jsp";
	}
	
	/**
	 * 删除试卷
	 */
	@RequestMapping("delete_exam")
	public String deleteExam(String exam_id, HttpSession session){
		historyDao.DeleteExam(Integer.parseInt(exam_id));
		return "Select_exam.do";
	}
	
	
}