package com.testcustom.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.UserCenterDaoImpl;
import com.testcustom.domain.User;
/**
 * 用户个人中心Dao类实现
 * @author Administrator
 *
 */
@Component
public class UserCenterDao implements UserCenterDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 根据id获取用户
	 */
	public User selectUserById(int user_id) {
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, user_id);
		session.close();
		return user;
	}

	/**
	 * 更新用户信息
	 */
	public void updateUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

}
