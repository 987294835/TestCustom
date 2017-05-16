package com.testcustom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.UserLoginDao;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.MD5;
import com.testcustom.utils.getRandom;

/**
 * 用户登录注册控制器
 * 
 * @author Administrator
 * 
 */

@Controller
public class UserLoginController {

	@Resource
	private UserLoginDao userLoginDao;

	public void setUserLoginDao(UserLoginDao userLoginDao) {
		this.userLoginDao = userLoginDao;
	}

	/*
	 * 登录
	 */
	@RequestMapping("user_login")
	public String UserLogin(String user_name, String user_password,
			HttpSession session, HttpServletResponse response) {
		User user_login = userLoginDao.selectUserByName(user_name);
		session.setAttribute("user_login", user_login);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if (user_login != null) {
			String password = user_login.getUser_password();
			int type = user_login.getUser_type();

			if (password.equals(MD5.md5(user_password))) {
				if (type == 1) {// 用户管理员身份
					return "select_all_user.do";
				} else if (type == 2) {// 试题管理员身份
					return "home.jsp";
				} else {// 普通用户身份
					return "home.jsp";
				}
			} else {
				try {
					PrintWriter out = response.getWriter();
					out.print("<script>alert('密码不正确 !!!');history.back();</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		} else {
			try {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('用户不存在 !!!');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/*
	 * 用户注册
	 */
	@RequestMapping("save_user")
	public String saveUser(String user_name, String user_password,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if (user_name == null || user_name.trim().equals("")
				|| user_password == null || user_password.trim().equals("")) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('用户名和密码不能为空 !!!');history.back();</script>");
			return null;
		} else {
			User user = new User();
			user.setUser_name(user_name);
			user.setUser_password(MD5.md5(user_password));
			user.setUser_type(3);
			try {
				userLoginDao.inputUser(user);
				return "index.jsp";
			} catch (Exception e) {
				// e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.print("<script>alert('用户名已存在 !!!');history.back();</script>");
				return null;
			}
		}
	}

	/*
	 * 发送邮件
	 */
	@RequestMapping("send_Email")
	public String sendEmail(String e_address, String user_name,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id_code = getRandom.getSix();
		
		User user = userLoginDao.selectUserByName(user_name);
		if(user!=null){
			String user_email = user.getUser_email();
			if(user_email!=null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				String time = df.format(new Date());
				System.out.println("time===>"+time);
				try {
					PrintWriter out = response.getWriter();
					if (user_email.equals(e_address)) {
						boolean b = com.testcustom.utils.sendEmail.sendMassage(e_address,
								id_code);
						if (b) {
							System.out.println("发送成功");
							userLoginDao.insert_id_code(user, id_code, time);
							return "index.jsp";
							
						} else {
							System.out.println("发送失败");
							out.print("<script>alert('验证码发送失败,请重新操作!!!');history.back();</script>");
						}
					} else {
						out.print("<script>alert('邮箱错误!!!');history.back();</script>");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					PrintWriter out = response.getWriter();
					out.print("<script>alert('还没有绑定的邮箱！！！');history.back();</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			PrintWriter out1;
			try {
				out1 = response.getWriter();
				out1.print("<script>alert('用户不存在!!!');history.back();</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return null;
	}
	@RequestMapping("set_new_pass")
	public String SetNewPass(String user_name,String id_code,String new_pass,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		User user = userLoginDao.selectUserByName(user_name);
		if(user!=null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sys_time = df.format(new Date());
			try {
				PrintWriter out = response.getWriter();
				Date d1 = df.parse(sys_time);
				Date d2 = df.parse(user.getTime());
				long diff = d1.getTime() - d2.getTime();   
			    long minutes = diff / (1000 * 60 );
			    if(minutes<=10){//验证码10分钟内有效
			    	if(user.getId_code().equals(id_code)){//匹配验证码
			    		userLoginDao.setNewPass(new_pass, user);
			    		return "index.jsp";
			    	}else{
			    		out.print("<script>alert('验证码不正确!!!');history.back();</script>");
			    	}
			    }else{
			    	out.print("<script>alert('验证码已过期!!!');history.back();</script>");
			    }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

}