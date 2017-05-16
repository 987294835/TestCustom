package com.testcustom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.CustomDao;
import com.testcustom.dao.PageDao;
import com.testcustom.dao.TitleDao;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;



@Controller
public class TitlesController {
 
	@Resource
	private CustomDao customDao;
	@Resource
	private TitleDao titleDao;
	@Resource
	private PageDao pageDao;
	 
	public void setCustomDao(CustomDao customDao) {
		this.titleDao = titleDao;
	}	
	
	//全数据查询
	@RequestMapping("Show_All")
	public String selectTitles(HttpServletRequest request,HttpSession session){	
		/*String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String type = request.getParameter("type");
		*/
        int totalrow = pageDao.getTotalRows();
		PageController pc = new PageController(totalrow, 1, 6);
		List<Object> major = titleDao.SelectMajorList();		
		List<Object> titles = titleDao.SelectTitleAll();
		
		session.setAttribute("major", major);
		session.setAttribute("titles", titles);	
		session.setAttribute("pc", pc);	
		
		User user_login = (User) session.getAttribute("user_login");
		if(user_login.getUser_type() == 1){
			return "super_papermanage.jsp";	
		}else{
			return "paper_manage.jsp";
		}
	}	
	
	//全数据翻页
	@RequestMapping("mqforpage")
	public String mqforpage(HttpServletRequest request,HttpSession session){
		String currPage = request.getParameter("currPage");
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
				
	    List<Object> titles = pageDao.mutilQueryForPage(currPage, pro, pc);
				
		session.setAttribute("pc", pc);
		session.setAttribute("titles", titles);
		User user_login = (User) session.getAttribute("user_login");
		if(user_login.getUser_type() == 1){
			return "super_papermanage.jsp";	
		}else{
			return "paper_manage.jsp";
		}
			
	}
	
	
	//条件查询
		@RequestMapping("major_condition")
		public String MajorCondition(HttpServletRequest request,HttpSession session){
			String major1 = request.getParameter("major");
			String backgroud = request.getParameter("backgroud");
			String type = request.getParameter("type");
			PageController pc =  (PageController) session.getAttribute("pc");
			Properties pro = new Properties();
	        
			if(!type.equals("题型")&&type != null&& !type.trim().equals("")){
				pro.setProperty("title_type", type);
			}
			if (!major1.equals("职位")&&major1 != null&& !major1.trim().equals("")){
				pro.setProperty("title_major", major1);
			}
			if (!backgroud.equals("背景")&&backgroud != null&& !backgroud.trim().equals("")){
				pro.setProperty("title_backgroud", backgroud);
			}
			
			pc.setPageController(pageDao.getTotalRows(pro), 1);
/*			List<Object> s_condition = customDao.S_Condition(pro,pc);	
*/			List<Object> titles = pageDao.mutilQueryForPage(String.valueOf(pc.getCurrentPage()), pro, pc);
			List<Object> major = titleDao.SelectMajorList();
					
			session.setAttribute("major", major);	
			session.setAttribute("titles", titles);
			session.setAttribute("pc", pc);
			session.setAttribute("pro", pro);
			
			User user_login = (User) session.getAttribute("user_login");
			if(user_login.getUser_type() == 1){
				return "super_papermanage.jsp";	
			}else{
				return "paper_manage.jsp";
			}		
		}
		
	//删除
	@RequestMapping("delete")
	public String deleteTitle(HttpServletRequest request,HttpSession session){
		
		String id = request.getParameter("id");
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
		String currPage = String.valueOf(pc.getCurrentPage());
		List<User> user = (List<User>) session.getAttribute("user");
							
		titleDao.deleteTitles(id);
		
		List<Object> titles = pageDao.mutilQueryForPage(currPage, pro, pc);
		
		session.setAttribute("pro", pro);
		session.setAttribute("pc", pc);
		session.setAttribute("titles", titles);
				
		User user_login = (User) session.getAttribute("user_login");
		if(user_login.getUser_type() == 1){
			return "super_papermanage.jsp";	
		}else{
			return "paper_manage.jsp";
		}
	}
	
	//跳转到修改页面
	@RequestMapping("toModify")
	public String toModifyTitle(HttpServletRequest request,HttpSession session){
		String id = request.getParameter("id");
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
		
		List<Object> singleTitle = titleDao.toModify(id);
		List<Object> major = titleDao.SelectMajorList();
		
		session.setAttribute("pro", pro);
		session.setAttribute("pc", pc);
		session.setAttribute("singleTitle", singleTitle);
		session.setAttribute("major", major);
		return "title_modify.jsp";	
	}
	
	
	@RequestMapping("return")
	public String Return_Title(HttpServletRequest request,HttpSession session){
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
		String currPage = String.valueOf(pc.getCurrentPage());
		
		List<Object> titles = pageDao.mutilQueryForPage(currPage, pro, pc);
		
		session.setAttribute("pro", pro);
		session.setAttribute("pc", pc);
		session.setAttribute("titles", titles);
	    return "super_papermanage.jsp";	
		
	}
	
	
	
	//成功修改
	@RequestMapping("modify")
	public String modifyTitle(HttpServletRequest request,HttpSession session){
		List<Title> singleTitle =  (List<Title>) session.getAttribute("singleTitle");
		String id =null;
		for(Title title: singleTitle)
		{
		    id = String.valueOf(title.getTitle_id());
		}
		System.out.println("title_id===>"+id);
		int type = Integer.parseInt(request.getParameter("type"));
		
		String major = request.getParameter("major");
		String text = request.getParameter("text");
		String answer = request.getParameter("answer");
		String explain = request.getParameter("explain");
		String A = request.getParameter("A");
		String B = request.getParameter("B");
		String C = request.getParameter("C");
		String D = request.getParameter("D");
		
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
		String currPage = String.valueOf(pc.getCurrentPage());
		
		titleDao.modify(id, type, major,text, answer, explain, A, B, C, D);
		List<Object> titles = pageDao.mutilQueryForPage(currPage, pro, pc);
			
		session.setAttribute("pro", pro);
		session.setAttribute("pc", pc);
		session.setAttribute("titles", titles);

		return "super_papermanage.jsp";	
		
	}
	
	//跳转到添加题目页面
	@RequestMapping("toAdd")
	public String toAdd(HttpServletRequest request,HttpSession session){
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
				
		session.setAttribute("pro", pro);
		session.setAttribute("pc", pc);
		
		return "add_select.jsp";		
	}
	
	//添加题目
	@RequestMapping("Add")
	public String Add(HttpServletRequest request,HttpSession session){
		int type = Integer.parseInt(request.getParameter("type"));
		String major = request.getParameter("major");
		String text = request.getParameter("text");
		String answer = request.getParameter("answer");
		int backgroud = Integer.parseInt(request.getParameter("backgroud"));
		String explain = request.getParameter("explain");
		String A = request.getParameter("A");
		String B = request.getParameter("B");
		String C = request.getParameter("C");
		String D = request.getParameter("D");
		
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
		String currPage = String.valueOf(pc.getCurrentPage());
		titleDao.add(type, major, text, answer, explain, A, B, C, D, backgroud);		
		List<Object> titles = pageDao.mutilQueryForPage(currPage, pro, pc);
		
		session.setAttribute("pro", pro);
		session.setAttribute("pc", pc);
		session.setAttribute("titles", titles);
		
		User user_login = (User) session.getAttribute("user_login");
		if(user_login.getUser_type() == 1){
			return "super_papermanage.jsp";	
		}else{
			return "paper_manage.jsp";
		}
	}
	
/*	//清除过时试题
	@RequestMapping("XXX")
	public String XXXX(HttpSession session , HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PageController pc =  (PageController) session.getAttribute("pc");
		Properties pro = (Properties) session.getAttribute("pro");
		List<User> user = (List<User>) session.getAttribute("user");
		String currPage = String.valueOf(pc.getCurrentPage());
		
				
	    titleDao.XXX();
		
	
		List<Object> titles = pageDao.mutilQueryForPage(currPage, pro, pc);
			
		session.setAttribute("pro", pro);
		session.setAttribute("pc", pc);
		session.setAttribute("titles", titles);
		
		return "super_papermanage.jsp";
	}
	*/
	/**
	 * 显示过时试题日志
	 * @throws IOException 
	 */
	@RequestMapping("delete_title_log")
	public String deleteTitleLog(HttpSession session, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Title> deleteTitles = titleDao.deleteTitleLog();
	/*	for(Title deleteTitle : deleteTitles){
			System.out.println("titile_id===>"+deleteTitle.getTitle_id());
		}*/
		if(!deleteTitles.isEmpty()){
			for(Title deleteTitle : deleteTitles){
				System.out.println("deleteTitle_id===>"+deleteTitle.getTitle_id());
			}
			session.setAttribute("deleteTitles", deleteTitles);
			return "deleteTitleLog.jsp";
		}else{
			PrintWriter out = response.getWriter();
			out.print("<script>alert('不存在存在50天且选中次数小于5的题');history.back();</script>");
			return null;
		}
		
	}
	
	/**
	 * 删除过时试题
	 */
	@RequestMapping("delete_old_title")
	public String DeleteOldTitle(String[] delete_ids){
		if(delete_ids!=null){
			for(String id : delete_ids){
				titleDao.deleteTitles(id);
			}
		}
		return "super_papermanage.jsp";
	}

}
