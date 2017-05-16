package com.testcustom.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.TitleDaoImpl;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;

@Component
public class TitleDao implements TitleDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	@Resource
	public PageDao pageDao;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// DAO select major ;
	public List<Object> SelectMajorList() {
		Session session = sessionFactory.openSession();
		String hql = "select distinct t.title_major from Title t";
		Query query = session.createQuery(hql);
		List<Object> titles = query.list();
		session.close();
		return titles;
	}

	// DAO select all date;
	public List<Object> SelectTitleAll() {
		int totalrow = pageDao.getTotalRows();

		PageController pc = new PageController(totalrow, 1,6);
		List<Object> titleAll = pageDao.query(pc);

		return titleAll;
	}

	// Dao层条件查询方法
	public List<Object> query(Properties pro, PageController pc) {
		Session session = sessionFactory.openSession();
		StringBuilder sb = new StringBuilder("select t from Title t");
		String hql = pageDao.getHQL(sb, pro);
		List<Object> title2 = new ArrayList<Object>();

		Query query = session.createQuery(hql);
		query.setFirstResult(pc.getPageStartRow());
		query.setMaxResults(pc.getPageSize());
		title2 = query.list();

		session.close();
		return title2;
	}

	// Dao层删除方法
	public void deleteTitles(String id) {
		/*
		 * Session session = sessionFactory.openSession(); int title_id =
		 * Integer.parseInt(id);
		 * 
		 * Title title = (Title) session.get(Title.class, title_id);
		 * session.beginTransaction(); if(null!=title){ for(Exam
		 * exam:title.getExams()) { exam.getExamTitles().clear(); }
		 * title.getUsers().clear(); session.delete(title); }
		 * session.getTransaction().commit(); session.close();
		 */
		int title_id = Integer.parseInt(id);
		Session session = sessionFactory.openSession();
		Title title = (Title) session.get(Title.class, title_id);
		List<Exam> exams = title.getExams();
		List<User> users = title.getUsers();
		session.beginTransaction();
		for (int i = 0; i < exams.size(); i++) {
			System.out.println(exams.get(i).getExam_id());
			exams.get(i).getExamTitles().remove(title);
		}
		title.getExams().remove(exams);
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUser_id());
			users.get(i).getUserTitles().remove(title);
		}
		title.getUsers().remove(users);

		title.getExams().clear();
		title.getUsers().clear();

		session.delete(title);
		session.getTransaction().commit();
		session.close();
	}

	// 跳转修改页面
	public List<Object> toModify(String id) {
		Session session = sessionFactory.openSession();
		String hql = "select t from Title t where t.title_id=" + id + "";
		List<Object> singletitle = new ArrayList<Object>();

		Query query = session.createQuery(hql);
		singletitle = query.list();

		session.close();
		return singletitle;
	}

	// 修改方法
	public void modify(String id, int type, String major, String text,
			String answer, String explain, String A, String B, String C,
			String D) {
		Session session = sessionFactory.openSession();

		String hql = "update Title t set t.title_type=?, t.title_major=?,"
				+ " t.title_text=?, t.title_answer=?, t.title_explain=?, t.title_option_a=?, "
				+ "t.title_option_b=?, t.title_option_c=?, t.title_option_d=?"
				+ " where t.title_id=" + id + "";
		Query query = session.createQuery(hql);
		query.setInteger(0, type);
		query.setString(1, major);
		query.setString(2, text);
		query.setString(3, answer);
		query.setString(4, explain);
		query.setString(5, A);
		query.setString(6, B);
		query.setString(7, C);
		query.setString(8, D);

		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();

		session.close();
	}

	// 修改方法
	public void add(int type, String major, String text, String answer,
			String explain, String A, String B, String C, String D,
			int title_backgroud) {
		Session session = sessionFactory.openSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Title title = new Title();
		title.setTitle_type(type);
		title.setTitle_major(major);
		title.setTitle_backgroud(title_backgroud);
		title.setTitle_text(text);
		title.setTitle_answer(answer);
		title.setTitle_explain(explain);
		title.setTitle_option_a(A);
		title.setTitle_option_b(B);
		title.setTitle_option_c(C);
		title.setTitle_option_d(D);
		title.setTitle_time(df.format(new Date()));

		session.save(title);
		session.close();
	}

	/*// 清除方法
	public void XXX() {
		Session session = sessionFactory.openSession();
		String hql = "select t from Title t";
		Query query = session.createQuery(hql);
		List<Title> titles = query.list();

		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String new_Time = sdf.format(Now);

		for (int i = 0; i < titles.size(); i++) {
			String str = titles.get(i).getTitle_time();
			int num = titles.get(i).getTitle_num();
			int id = titles.get(i).getTitle_id();
			Date date = null;
			try {
				date = sdf.parse(str);
				long day = (Now.getTime() - date.getTime())
						/ (24 * 60 * 60 * 1000);
				if (day >= 50 && num < 5) {
					deleteTitles(String.valueOf(id));
				}
			} catch (ParseException e) {
				System.out.println("HIHI");
				e.printStackTrace();
			}
		}
		// String sql = "update Title t set t.title_num=?,t.title_time=?";
		// Query query2 = session.createQuery(sql);
		// query2.setInteger(0, 0);
		// query2.setString(1, new_Time);
		// query2.executeUpdate();
		session.close();

	}*/

	/**
	 * 根据题号找题
	 */
	public Title findDeleteTitleById(int title_id) {
		Session session = sessionFactory.openSession();
		Title title = (Title) session.get(Title.class, title_id);
		session.close();
		return title;
	}

	/**
	 * 删除题目日志
	 * @return
	 */
	public List<Title> deleteTitleLog() {
		Session session = sessionFactory.openSession();
		String hql = "select t from Title t";
		Query query = session.createQuery(hql);
		List<Title> titles = query.list();
		List<Title> deleteTitles = new ArrayList<Title>();

		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String new_Time = sdf.format(Now);

		for (int i = 0; i < titles.size(); i++) {
			String str = titles.get(i).getTitle_time();
			int num = titles.get(i).getTitle_num();
			int id = titles.get(i).getTitle_id();
			Date date = null;
			try {
				date = sdf.parse(str);
				long day = (Now.getTime() - date.getTime())
						/ (24 * 60 * 60 * 1000);
				if (day >= 50 && num < 5) {
					Title deleteTitle = findDeleteTitleById(id);
					deleteTitles.add(deleteTitle);
				}
			} catch (ParseException e) {
				System.out.println("HIHI");
				e.printStackTrace();
			}
		}
		session.close();
		return deleteTitles;
	}
}
