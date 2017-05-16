package com.testcustom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.dao.UserCenterDao;
import com.testcustom.domain.User;

/**
 * 用户个人中心控制器
 * @author Administrator
 *
 */

@Controller
public class UserCenterController {

	@Resource
	private UserCenterDao userCenterDao;

	public void setUserCenterDao(UserCenterDao userCenterDao) {
		this.userCenterDao = userCenterDao;
	}

	/*
	 * 更新用户信息
	 */
	@RequestMapping("update_user")
	public String updateUser(String user_name, String user_phone,
			String user_address, String user_email, HttpSession session,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		User user_login = (User) session.getAttribute("user_login");
		user_login.setUser_name(user_name);
		user_login.setUser_phone(user_phone);
		user_login.setUser_address(user_address);
		user_login.setUser_email(user_email);
		try {
			userCenterDao.updateUser(user_login);
			session.setAttribute("user_login", user_login);
			return "p_center.jsp";
		} catch (Exception e) {
//			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("<script>alert('该用户名已存在 !!!');history.back();</script>");
			return null;
		}

	}

	/*
	 * 修改用户密码
	 */
	@RequestMapping("change_password")
	public String changePassword(String user_password,
			String new_user_password, HttpSession session,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		User user_login = (User) session.getAttribute("user_login");
		if (!user_password.equals(user_login.getUser_password())) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('密码不正确 !!!');history.back();</script>");
			return null;
		}else{
			user_login.setUser_password(new_user_password);
			userCenterDao.updateUser(user_login);
			session.setAttribute("user_login", user_login);
			return "p_center.jsp";
		}
	}

}
