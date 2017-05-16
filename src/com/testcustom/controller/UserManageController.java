package com.testcustom.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.HistoryDao;
import com.testcustom.dao.JobManageDao;
import com.testcustom.dao.UserManageDao;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Job;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;
/**
 * 用户管理控制器
 * @author Administrator
 *
 */

@Controller
public class UserManageController {

	@Resource
	private UserManageDao userManageDao;
	public void setUserManageDao(UserManageDao userManageDao) {
		this.userManageDao = userManageDao;
	}
	@Resource
	private HistoryDao historyDao;
	public void setHistoryDao(HistoryDao historyDao) {
		this.historyDao = historyDao;
	}
	@Resource
	private JobManageDao jobManageDao;
	public void setJobManageDao(JobManageDao jobManageDao) {
		this.jobManageDao = jobManageDao;
	}

	/*
	 * 分页显示用户列表
	 */
	@RequestMapping("select_all_user")
	public String selectAllUser(HttpSession session){
//		List<User> user = (List<User>) session.getAttribute("user_login");
//		System.out.println(user);
		int totalRows = userManageDao.getTotalrows();
		PageController pc = new PageController(totalRows, 1, 6);
		List<User> users = userManageDao.queryAllUser(pc);
		session.setAttribute("pc", pc);
//		for(User user : users){
//			System.out.println("user_id===>"+user.getUser_id());
//		}
		session.setAttribute("users", users);
		return "super_manager.jsp";
	}
	
	/*
	 * 页数跳转
	 */
	@RequestMapping("page_change")
	public String pageChange(String currPage, HttpSession session){
		PageController pc = (PageController) session.getAttribute("pc");
		pc.setCurrentPage(Integer.parseInt(currPage));
		List<User> users = userManageDao.queryAllUser(pc);
		session.setAttribute("users", users);
		session.setAttribute("pc", pc);
		return "super_manager.jsp";
	}
	
	@RequestMapping("return_user")
	public String return_user(HttpSession session){
		PageController pc = (PageController) session.getAttribute("pc");
		List<User> users = userManageDao.queryAllUser(pc);
		if(users.size() == 0){
			pc.setCurrentPage(pc.getCurrentPage()-1);
			users = userManageDao.queryAllUser(pc);
		}
		session.setAttribute("users", pc);
		return "super_manager.jsp";
	}
	
	/*
	 * 删除用户
	 */
	@RequestMapping("delete_by_userid")
	public String deleteByUserId(String[] u_id){
		if(u_id != null){
			for(String id : u_id){
				userManageDao.deleteUser(Integer.parseInt(id));
			}
		}
		return "select_all_user.do";
	}
	/*
	 * 修改用户类型
	 */
	@RequestMapping("change_user_type")
	public String changeUserType(String[] u_id, HttpServletRequest request){
		if(u_id != null ){
			for(String id : u_id){
				String u_type = request.getParameter(id+"_u_type");
				userManageDao.changeType(Integer.parseInt(id), Integer.parseInt(u_type));
			}
		}
		return "select_all_user.do";
	}
	
	/**
	 * 分页查询所有试卷
	 * @param session
	 * @return
	 */
	@RequestMapping("show_all_user_exams")
	public String showALLUserJobs(HttpSession session){
		List<Exam> allExams = userManageDao.ALLExams();
		int totalRows = allExams.size();
		PageController pc = new PageController(totalRows, 1, 8);
		List<Exam> allExamsByPage = userManageDao.ALLExamsByPage(pc);
		session.setAttribute("pc", pc);
		session.setAttribute("allExamsByPage", allExamsByPage);
		return "super_testmanage.jsp";
	}
	
	@RequestMapping("all_user_exams_change")
	public String all_user_jobs_change(HttpSession session,String currPage){
		PageController pc = (PageController) session.getAttribute("pc");
		pc.setCurrentPage(Integer.parseInt(currPage));
		List<Exam> allExamsByPage = userManageDao.ALLExamsByPage(pc);
		session.setAttribute("allExamsByPage", allExamsByPage);
		session.setAttribute("pc", pc);
		return "super_testmanage.jsp";
	}
	
	@RequestMapping("returnExam")
	public String return_exam(HttpSession session){
		PageController pc = (PageController) session.getAttribute("pc");
		List<Exam> allExamsByPage = userManageDao.ALLExamsByPage(pc);
		if(allExamsByPage.size() == 0){
			pc.setCurrentPage(pc.getCurrentPage()-1);
			pc.setTotalPages(pc.getTotalPages()-1);
			allExamsByPage = userManageDao.ALLExamsByPage(pc);
		}
		session.setAttribute("allExamsByPage", allExamsByPage);
		return "super_testmanage.jsp";
	}
	
	@RequestMapping("delete_user_exam")
	public String deleteUserExam(String exam_id, HttpSession session){
		historyDao.DeleteExam(Integer.parseInt(exam_id));
		return "returnExam.do";
	}
	
	@RequestMapping("show_user_jobs")
	public String showUserJobs(HttpSession session){
		List<Job> allJobs = userManageDao.ALLJobs();
		int totalRows = allJobs.size();
		PageController pc = new PageController(totalRows, 1, 8);
		List<Job> allJobsByPage = userManageDao.ALLJobsByPage(pc);
		session.setAttribute("allJobsByPage", allJobsByPage);
		session.setAttribute("pc", pc);
		return "super_jobmanage.jsp";
	}
	
	@RequestMapping("all_user_job_change")
	public String all_user_job_change(HttpSession session, String currPage){
		PageController pc = (PageController) session.getAttribute("pc");
		pc.setCurrentPage(Integer.parseInt(currPage));
		List<Job> allJobsByPage = userManageDao.ALLJobsByPage(pc);
		session.setAttribute("allJobsByPage", allJobsByPage);
		session.setAttribute("pc", pc);
		return "super_jobmanage.jsp";
	}
	
	@RequestMapping("return_user_job")
	public String return_user_job(HttpSession session){
		PageController pc = (PageController) session.getAttribute("pc");
		List<Job> allJobsByPage = userManageDao.ALLJobsByPage(pc);
		if(allJobsByPage.size() == 0){
			pc.setCurrentPage(pc.getCurrentPage()-1);
			pc.setTotalPages(pc.getTotalPages()-1);
			allJobsByPage = userManageDao.ALLJobsByPage(pc);
		}
		session.setAttribute("allJobsByPage", allJobsByPage);
		return "super_jobmanage.jsp";
	}
	
	@RequestMapping("delete_user_job")
	public String DeleteJob(String job_id){
		jobManageDao.deleteJob(Integer.parseInt(job_id ));
		return "return_user_job.do";
	}
}
