package com.testcustom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.PersonManageDao;
import com.testcustom.domain.Job;
import com.testcustom.domain.Person;
import com.testcustom.domain.Title;
import com.testcustom.utils.MD5;
import com.testcustom.utils.PageController;
import com.testcustom.utils.TimeDifferent;
import com.testcustom.utils.getBJtime;

@Controller
public class PersonController {

	@Resource
	private PersonManageDao personManageDao;
	public void setPersonManageDao(PersonManageDao personManageDao) {
		this.personManageDao = personManageDao;
	}
	
	/**
	 * 用户注册
	 * @param person_name
	 * @param person_password
	 * @param person_phone
	 * @param person_email
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("add_person")
	public String AddPerson(String person_name, String person_password, String person_phone, String person_email,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Person person = new Person();
		person.setPerson_name(person_name);
		person.setPerson_password(MD5.md5(person_password));
		person.setPerson_email(person_email);
		person.setPerson_phone(person_phone);
		try {
			personManageDao.add_person(person);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('用户名已存在 !!!');history.go(-1);</script>");
			return null;
		}
		return "index.jsp";
	}
	/**
	 * 用户登录
	 * @param person_email
	 * @param person_password
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("person_login")
	public String PersonLogin(String person_email, String person_password, HttpSession session, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Person person = personManageDao.selectPersonByEmail(person_email);
		if(person != null){
			String person_password2 = person.getPerson_password();
			if(person_password2.equals(MD5.md5(person_password))){
				session.setAttribute("person_login", person);
				Job person_job = person.getJob();
				if(person_job == null){
					session.setAttribute("have_job", 0);
					System.out.println("****null****");
				}else{
					session.setAttribute("have_job", 1);
					System.out.println("*****not null****");
				}
				return "personInfo.jsp";
			}else{
				out.print("<script>alert('密码不正确 !!!');history.back();</script>");
			}
		}else{
			out.print("<script>alert('用户不存在 !!!');history.back();</script>");
		}
		return null;
	}
	/**
	 * 完善个人资料 /修改资料
	 * @param person_name
	 * @param person_email
	 * @param person_phone
	 * @param person_age
	 * @param person_sex
	 * @param person_education
	 * @param person_practice
	 * @param person_project
	 * @param session
	 * @return
	 */
	@RequestMapping("add_person_message")
	public String AddPersonMessage(String person_name,String person_email,String person_phone,String person_age, String person_sex, String person_education, String person_practice, String person_project,HttpSession session){
		Person person = (Person) session.getAttribute("person_login");
		person.setPerson_name(person_name);
		person.setPerson_phone(person_phone);
		person.setPerson_email(person_email);
		person.setPerson_age(Integer.parseInt(person_age));
		person.setPerson_sex(Integer.parseInt(person_sex));
		person.setPerson_education(person_education);
		person.setPerson_practice(person_practice);
		person.setPerson_project(person_project);
		personManageDao.addPersonMessage(person);
		return "get_all_jobs.do";
	}
	
	/**
	 * 获取所有已经发布的岗位
	 * @param session
	 * @return
	 */
	@RequestMapping("get_all_jobs")
	public String GetALLJobs(HttpSession session){
		List<Job> publish_jobs = personManageDao.GetALLPublishJob();
		session.setAttribute("publish_jobs", publish_jobs);
		return "jobList.jsp";
	}
	
	/**
	 * 申请岗位
	 * @param job_id
	 * @param session
	 * @return
	 */
	@RequestMapping("add_job_to_person")
	public String AddJobToPerson(String job_id, HttpSession session){
		Person person = (Person) session.getAttribute("person_login");	
		personManageDao.addJobToPerson(person, Integer.parseInt(job_id));
		return "success.jsp";
	}
	
	/**
	 * 查看用户信息
	 * @param session
	 * @param person_id
	 */
	@RequestMapping("see_person")
	public String SeePerson(HttpSession session,String person_id){
		Person person = personManageDao.seePerson(Integer.parseInt(person_id));
		session.setAttribute("person", person);
		return  "person.jsp";
	}
	
	/**
	 * 登录进入考试
	 * @param person_email
	 * @param exam_code
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("login_to_exam")
	public String LoginToExam(String person_email, String exam_code, HttpSession session, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Person person = personManageDao.selectPersonByEmail(person_email);
		if(person != null){
			String job_start_time = person.getJob().getJob_start_time();
			String get_BJtime = getBJtime.get_BJtime(3);
			long different = TimeDifferent.different(get_BJtime, job_start_time.replace("T", " "));
			System.out.println("different===>"+different);
			if(different < 5 && different>0){
				if(person.getPerson_state() == 0){
					String exam_code2 = person.getPerson_exam_code();
					if(exam_code2.equals(exam_code)){
						session.setAttribute("person_login_exam", person);
						return "get_job_exam_title.do";
					}else{
						out.print("<script>alert('验证码不正确 !!!');history.back();</script>");
					}
				}else{
					out.print("<script>alert('您已完成考试!!!');history.back();</script>");
				}
			}else{
				out.print("<script>alert('考试时间没到或已过!!!');history.back();</script>");
			}	
		}else{
			out.print("<script>alert('用户名不存在!!!');history.back();</script>");
		}
		return null;
	}
	
	/**
	 * 获取第一条试题
	 * @param session
	 * @return
	 */
	@RequestMapping("get_job_exam_title")
	public String getJobExamTitle(HttpSession session){
		Person person_login_exam = (Person) session.getAttribute("person_login_exam");
		Job job = person_login_exam.getJob();
		List<Title> titles = personManageDao.GetJobTitleSize(job);
		int totalRows = titles.size();
		System.out.println("totalRows===>"+totalRows);
		if(totalRows != 0){
			PageController pc = new PageController(totalRows, 1, 1);
			List<Title> JobExamTitle = personManageDao.GetJobExamTitles(job, pc);
			session.setAttribute("pc", pc);
			session.setAttribute("time", 3600);
			session.setAttribute("score", 0);
			session.setAttribute("JobExamTitle", JobExamTitle);	
		}else{
			session.setAttribute("JobExamTitle", titles);
		}
		return "time.jsp";
	}
	
	/**
	 * 下一题，同时判断上一题是否回答正确
	 * @param session
	 * @return
	 */
	@RequestMapping("next_job_exam_title")
	public String nextJobExamTitle(HttpSession session, String title_id, String answer, String timer
			,String[] mutil_answer,String judge_answer,String blank_answer){
		Person person_login_exam = (Person) session.getAttribute("person_login_exam");
		Job job = person_login_exam.getJob();
		Title title = personManageDao.GetTitleById(Integer.parseInt(title_id));
		int score = (Integer) session.getAttribute("score");
		int title_type = title.getTitle_type();
		if(mutil_answer != null){
			answer = "";
			for(int i = 0; i < mutil_answer.length; i++){
				answer += mutil_answer[i];
			}
		}
		if(judge_answer != null){
			answer="";
			answer = judge_answer;
		}
		if(blank_answer != null){
			answer = "";
			answer = blank_answer;
		}
		
		System.out.println("title_answer===>"+title.getTitle_answer());
		System.out.println("my_answer===>"+answer);
		if(title.getTitle_answer().equals(answer)){
			if(title_type == 0 || title_type == 2){
				score+=3;
			}else if(title_type == 1 || title_type == 3){
				score+=5;
			}
		}
		System.out.println("score===>"+score);
		session.setAttribute("score", score);
		PageController pc = (PageController) session.getAttribute("pc");
		if(pc.getCurrentPage()== pc.getTotalPages() || timer.equals("1")){
			person_login_exam.setPerson_score(score);
			person_login_exam.setPerson_state(1);
			personManageDao.addPersonMessage(person_login_exam);
			return "finish.jsp";
		}else{
			pc.setCurrentPage(pc.getCurrentPage()+1);
			List<Title> JobExamTitle = personManageDao.GetJobExamTitles(job, pc);
			session.setAttribute("JobExamTitle", JobExamTitle);
			session.setAttribute("time", timer);
			return "time.jsp";
		}
		
	}	
}
