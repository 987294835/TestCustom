package com.testcustom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.CollectDaoImpl;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;

@Component
public class CollectDao implements CollectDaoImpl{
	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	/**
	 * 把试题id加入用户的收藏
	 */
	public void Testcollect(int user_id, int title_id){
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, user_id);//通过use_id
		Title title = (Title) session.get(Title.class, title_id);
		user.getUserTitles().add(title);
		title.getUsers().add(user);
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 
	 * 获取用户已收藏的title对象并分页
	 */
	public List<Title> testUserTitles1(int id, PageController pc){
	Session session = sessionFactory.openSession();
	User user = (User) session.get(User.class, id);
	List<Title> titles = user.getUserTitles();
	List<Title> new_titles = new ArrayList<Title>();
	for(Title title : titles){
//		System.out.println("test:"+title.getTitle_id());
	}
	session.close();
	
	for(int i = pc.getPageStartRow(); i<pc.getPageStartRow()+pc.getPageSize() && i<titles.size(); i++){
		new_titles.add(titles.get(i));
	}
	
	return new_titles;
}
	/**
	 * 
	 * 获取用户已收藏的title对象
	 */
	public List<Title> testUserTitles(int id){
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, id);
		List<Title> titles = user.getUserTitles();
		
		for(Title title : titles){
//			System.out.println("test:"+title.getTitle_id());
		}
		session.close();
		
		return titles;
	}
	
	public void deletecollect(int user_id, int title_id){
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, user_id);
		Title title = (Title) session.get(Title.class, title_id);
		user.getUserTitles().remove(title);
		title.getUsers().remove(user);
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}
	
}

