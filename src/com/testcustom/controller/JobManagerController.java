package com.testcustom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.JobManageDao;
import com.testcustom.dao.PersonManageDao;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Job;
import com.testcustom.domain.Person;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;
import com.testcustom.utils.getBJtime;
import com.testcustom.utils.getRandom;
import com.testcustom.utils.sendEmail;

@Controller
public class JobManagerController {
	
	@Resource
	private JobManageDao jobManageDao;
	public void setJobManageDao(JobManageDao jobManageDao) {
		this.jobManageDao = jobManageDao;
	}
	
	@Resource
	private PersonManageDao personManageDao;
	public void setPersonManageDao(PersonManageDao personManageDao) {
		this.personManageDao = personManageDao;
	}

	/**
	 * 添加岗位
	 * @param job_name
	 * @param job_message
	 * @param job_request
	 * @param session
	 * @return
	 */
	@RequestMapping("add_job")
	public String AddJob(String job_name,String job_message,String job_request,HttpSession session){
		User user = (User) session.getAttribute("user_login");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String job_time = getBJtime.get_BJtime(2);
		Job job = new Job();
		job.setJob_name(job_name);
		job.setJob_message(job_message);
		job.setJob_request(job_request);
		job.setJob_time(job_time);
		
		job.setUser(user);
		jobManageDao.addJob(job);
		
		return "home.jsp";
	}
	
	@RequestMapping("go_to_add_job")
	public String goToAddJob(HttpSession session){
		User user_login = (User) session.getAttribute("user_login");
		List<Exam> exams = jobManageDao.getMyExams(user_login.getUser_id());
		for(Exam exam : exams){
			System.out.println("exam_name===>"+exam.getExam_name());
		}
		session.setAttribute("exams", exams);
		return "addPosition.jsp";
	}
	
	/**
	 * 分页查询获得第一页岗位
	 * @param session
	 * @return
	 */
	@RequestMapping("show_jobs")
	public String ShowJobs(HttpSession session){
		User user = (User) session.getAttribute("user_login");
		List<Job> jobs = jobManageDao.getJobsSize(user.getUser_id());
		int totalRow = jobs.size();
		PageController pc = new PageController(totalRow, 1, 5);
		if(totalRow != 0){
			List<Job> jobs_1 = jobManageDao.showJobs(user.getUser_id(),pc);
			session.setAttribute("jobs", jobs_1);
			session.setAttribute("pc", pc);
		}else{
			session.setAttribute("jobs", jobs);
		}
		return "show_jobs.jsp";
	}
	/**
	 * 翻页
	 * @param currPage
	 * @param session
	 * @return
	 */
	@RequestMapping("job_page_change")
	public String jobPageChange(String currPage, HttpSession session){
		User user_login = (User) session.getAttribute("user_login");
		PageController pc = (PageController) session.getAttribute("pc");
		int currpage=Integer.parseInt(currPage);
		pc.setCurrentPage(currpage);
		List<Job> jobs_1 = jobManageDao.showJobs(user_login.getUser_id(), pc);
		session.setAttribute("jobs",jobs_1);
		return "show_jobs.jsp";
	}
	
	/**
	 * 操作返回页
	 * @param session
	 * @return
	 */
	@RequestMapping("return_job")
	public String returnJob(HttpSession session){
		User user_login = (User) session.getAttribute("user_login");
		PageController pc = (PageController) session.getAttribute("pc");
		List<Job> jobs_1 = jobManageDao.showJobs(user_login.getUser_id(), pc);
		
		if(jobs_1.size() == 0){
			System.out.println("jobs_1==null");
			pc.setCurrentPage(pc.getCurrentPage()-1);
			jobs_1 = jobManageDao.showJobs(user_login.getUser_id(), pc);
		}
		session.setAttribute("jobs",jobs_1);
		return "show_jobs.jsp";
	}
	
	/**
	 * 查看岗位信息
	 * @param job_id
	 * @param session
	 * @return
	 */
	@RequestMapping("see_job")
	public String SeeJob(String job_id, HttpSession session){
		Job job = jobManageDao.seeJob(Integer.parseInt(job_id));
		session.setAttribute("job_id", job_id);
		session.setAttribute("jobinfo", job);
		return "get_my_job.do";
	}
	/**
	 * 删除岗位
	 * @param job_id
	 * @return
	 */
	@RequestMapping("delete_job")
	public String DeleteJob(String job_id){
		jobManageDao.deleteJob(Integer.parseInt(job_id ));
		return "return_job.do";
	}
	/**
	 * 发布岗位
	 * @param job_id
	 * @param i
	 * @return
	 */
	@RequestMapping("publish_job")
	public String PublishJob(String job_id, String i){
		jobManageDao.publishJob(Integer.parseInt(job_id),Integer.parseInt(i));
		return "return_job.do";
	}
	/**
	 * 获取岗位信息
	 * @param session
	 * @return
	 */
	@RequestMapping("get_my_job")
	public String GetMyExams(HttpSession session){
		System.out.println("******GetMyExams****");
		User user_login = (User) session.getAttribute("user_login");
		List<Exam> exams = jobManageDao.getMyExams(user_login.getUser_id());
		for(Exam exam : exams){
			System.out.println("exam_name===>"+exam.getExam_name());
		}
		session.setAttribute("exmas", exams);
		
		return "jobInfo.jsp";
	}
	/**
	 * 编辑岗位信息
	 * @param job_name
	 * @param job_message
	 * @param job_request
	 * @param exam_id
	 * @param session
	 * @return
	 */
	@RequestMapping("reset_job")
	public String ResetJob(String job_name,String job_message,String job_request,String exam_id,HttpSession session,String dateTime){
		String job_id = (String) session.getAttribute("job_id");
		System.out.println("dateTime===>"+dateTime);
		jobManageDao.resetJob(Integer.parseInt(job_id), job_name, job_message, job_request, Integer.parseInt(exam_id),dateTime);
		return "return_job.do";
	}
	
	/**
	 * 获取申请该岗位的人
	 * @param job_id
	 * @param session
	 */
	@RequestMapping("see_persons")
	public String SeePersons(String job_id, HttpSession session){
		List<Person> job_persons = jobManageDao.see_persons(Integer.parseInt(job_id));
		session.setAttribute("job_persons", job_persons);
		return "job_person.jsp";
	}
	/**
	 * 发送考试验证码并插入数据库
	 * @param job_id
	 * @return
	 */
	@RequestMapping("send_exam_code")
	public String SendExamCode(String job_id){
		List<Person> persons = jobManageDao.see_persons(Integer.parseInt(job_id));
		
		if(persons!=null){
			for(Person person : persons){
				String person_email = person.getPerson_email();
				String examCode = getRandom.getExamCode();
				person.setPerson_exam_code(examCode);
				personManageDao.addPersonMessage(person);
				sendEmail.sendExamCode(person_email, examCode);
			}
		}
		return "return_job.do";
	}
	
	
}
