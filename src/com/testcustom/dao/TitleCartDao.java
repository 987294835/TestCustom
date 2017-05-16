package com.testcustom.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.TitleCartDaoImpl;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;

@Component
public class TitleCartDao implements TitleCartDaoImpl {

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 根据id获取试题
	 */

	@Override
	public Title findTitleById(int title_id) {
		Session session = sessionFactory.openSession();
		Title title = (Title) session.get(Title.class, title_id);
		session.close();
		return title;
	}

	
	/**
	 * 把试题筐的试题保存到数据库中
	 */
	@Override
	public void cartInputHistory(int user_id, String exam_name,
			List<Integer> title_id) {
		Session session = sessionFactory.openSession();
		Exam exam = new Exam();
		User user = (User) session.get(User.class, user_id);

		exam.setExam_name(exam_name);
		exam.setUser(user);

		session.beginTransaction();
		for (int id : title_id) {
			Title title = (Title) session.get(Title.class, id);
			title.setTitle_num(title.getTitle_num()+1);
			exam.getExamTitles().add(title);
			title.getExams().add(exam);
			session.update(title);
		}

		
		session.save(exam);
		session.getTransaction().commit();
		session.close();

	}
	/**
	 * 获取最新添加的试卷
	 * @param user_id
	 * @return
	 */
	public Exam getNewExam(int user_id){
		Session session = sessionFactory.openSession();
		Exam new_exam = null;
		User user = (User) session.get(User.class, user_id);
		List<Exam> exams = user.getExams();
		for(Exam exam : exams){
			new_exam = exam;
		}
		session.close();
		System.out.println("exam_name===>"+new_exam.getExam_name());
		return new_exam;
	}

	/**
	 * 插入试卷总分
	 * @param score
	 * @param exam_id
	 */
	public void setExamScore(int score, int exam_id){
		Session session = sessionFactory.openSession();
		Exam exam = (Exam) session.get(Exam.class, exam_id);
		exam.setExam_score(score);
		session.beginTransaction();
		session.update(exam);
		session.getTransaction().commit();
		session.close();
	}
}
