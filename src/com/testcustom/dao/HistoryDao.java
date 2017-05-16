package com.testcustom.dao;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.HistoryDaoImpl;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;

@Component
public class HistoryDao implements HistoryDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
		/**
		 * 分页查询试卷
		 */
		public List<Exam> testUserExams1(int id, PageController pc){
			Session session = sessionFactory.openSession();
			User user = (User) session.get(User.class, id);
			List<Exam> exams = user.getExams();
			List<Exam> new_exams = new ArrayList<Exam>();
			for(Exam exam : exams){
//				System.out.println("test:"+title.getTitle_id());
			}
			session.close();
			for(int i = pc.getPageStartRow(); i<pc.getPageStartRow()+pc.getPageSize() && i<exams.size(); i++){
				new_exams.add(exams.get(i));
			}
			return new_exams;
		}
			
		/**
		 * 查询用户所有的试卷
		 */
		public List<Exam> testUserExams(int id){
			Session session = sessionFactory.openSession();
			User user = (User) session.get(User.class, id);
			List<Exam> exams = user.getExams();
		
			for(Exam exam : exams){
//				System.out.println("test:"+title.getTitle_id());
			}
			session.close();
			return exams;
		}
		
		public List<Title> checkTitlesFromExam(int exam_id){
			Session session = sessionFactory.openSession();
			Exam exam = (Exam) session.get(Exam.class, exam_id);
			List<Title> titles = exam.getExamTitles();
			for(Title title : titles){
				
			}
			session.close();
			return titles;
		}
			
		/**
		 * 删除用户试卷
		 */
		public void DeleteExam(int exam_id){
			Session session = sessionFactory.openSession();
			Exam exam = (Exam) session.get(Exam.class, exam_id);

			exam.getExamTitles().clear();
			session.beginTransaction();
			session.delete(exam);
			session.getTransaction().commit();
			session.close();
			}
	/**
	 * 根据Id获取试卷
	 * @param exam_id
	 * @return
	 */
		public Exam getExam(int exam_id){
			Session session = sessionFactory.openSession();
			Exam exam = (Exam) session.get(Exam.class, exam_id);
			session.close();
			return exam;		
		}
}
