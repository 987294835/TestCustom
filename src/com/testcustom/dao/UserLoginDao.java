package com.testcustom.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testcustom.daoImpl.UserLoginDaoImpl;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.MD5;
/**
 * 用户登录注册Dao类实现
 * @author Administrator
 *
 */

@Component
public class UserLoginDao implements UserLoginDaoImpl {
	
	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 根据用户名获取用户
	 */
	public User selectUserByName(String user_name) {
		Session session = sessionFactory.openSession();
		String hql = "select u.user_id from User u where u.user_name = ?";
		Query query = session.createQuery(hql);
		query.setString(0, user_name);
		List<Object> user_id = query.list();
		Integer id = null;
		for(Object u_id : user_id){
			id = (Integer) u_id;
		}
		User user = null;
		if(!user_id.isEmpty()){
			user = (User) session.get(User.class, id);
		}
		session.close();
		return user;
	}

	/**
	 * 注册用户
	 */
	public void inputUser(User user) {
		Session session = sessionFactory.openSession();
		session.save(user);
		session.close();
	}
	
	/**
	 * 插入验证码与验证码时间
	 * @param user
	 * @param id_code
	 * @param time
	 */
	public void insert_id_code(User user,String id_code,String time){
		Session session = sessionFactory.openSession();	
		/*User user = (User) session.get(User.class, user_id);*/
		if(user != null){
			user.setId_code(id_code);
			user.setTime(time);
		}
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * 修改密码
	 */
	
	public void setNewPass(String new_pass,User user){
		Session session = sessionFactory.openSession();
		if(user!=null){
			user.setUser_password(MD5.md5(new_pass));
		}
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	
	
}
