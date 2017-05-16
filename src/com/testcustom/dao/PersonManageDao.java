package com.testcustom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.personManageDaoImpl;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Job;
import com.testcustom.domain.Person;
import com.testcustom.domain.Title;
import com.testcustom.utils.PageController;


@Component
public class PersonManageDao implements personManageDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * �û�ע�� - ���ݿ������������Ψһ����
	 */
	@Override
	public void add_person(Person person) {
		Session session = sessionFactory.openSession();
		session.save(person);
		session.close();
	}
	
	/**
	 * ���������ȡ�û�
	 * @param person_email
	 * @return
	 */
	public Person selectPersonByEmail(String person_email){
		Session session = sessionFactory.openSession();
		String hql = "select p.person_id from Person p where p.person_email=?";
		Query query = session.createQuery(hql);
		query.setString(0, person_email);
		List<Object> person_id = query.list();
		Integer id = null;
		for(Object p_id : person_id){
			id = (Integer) p_id;
		}
		Person person = null;
		if(!person_id.isEmpty()){
			person = (Person) session.get(Person.class, id);
		}
		session.close();
		return person;
	}
	
	/**
	 * ���Ƹ�����Ϣ
	 * @param person
	 */
	public void addPersonMessage(Person person){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(person);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * ��ȡ�����Ѿ������ĸ�λ
	 * @return
	 */
	public List<Job> GetALLPublishJob(){
		Session session = sessionFactory.openSession();
		String hql = "select j from Job j where j.job_state = 1";
		Query query = session.createQuery(hql);
		List<Job> jobs = query.list();
		session.close();
		return jobs;
	}
	
	/**
	 * �û���Ӹ�λ��Ϣ
	 * @param person
	 * @param job_id
	 */
	public void addJobToPerson(Person person, int job_id){
		Session session = sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, job_id);
		session.beginTransaction();
		person.setJob(job);
		session.update(person);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 * @param person_id
	 * @return
	 */
	public Person seePerson(int person_id){
		Session session = sessionFactory.openSession();
		Person person = (Person) session.get(Person.class, person_id);
		session.close();
		return person;
	}
	
	/**
	 * ��ҳ��ȡ�Ծ�����
	 * @param job_id
	 * @return
	 */
	public List<Title> GetJobExamTitles(Job job, PageController pc){
		Session session = sessionFactory.openSession();
		int exam_id = job.getExam_id();
		Exam exam = (Exam) session.get(Exam.class, exam_id);
		List<Title> examTitles = exam.getExamTitles();
		for(Title examTitle : examTitles){
		}
		session.close();
		List<Title> new_exam_titles = new ArrayList<Title>();
		for(int i = pc.getPageStartRow(); i<pc.getPageStartRow()+pc.getPageSize() && i<examTitles.size(); i++){
			new_exam_titles.add(examTitles.get(i));
		}
		return new_exam_titles;
	}
	
	/**
	 * ��ȡ��������
	 * @param job
	 * @return
	 */
	public List<Title> GetJobTitleSize(Job job){
		Session session = sessionFactory.openSession();
		int exam_id = job.getExam_id();
		Exam exam = (Exam) session.get(Exam.class, exam_id);
		List<Title> examTitles = exam.getExamTitles();
		for(Title examTitle : examTitles){
		}
		session.close();
		return examTitles;
	}
	
	/**
	 * ����id��ȡtitle
	 * @param title_id
	 * @return
	 */
	public Title GetTitleById(int title_id){
		Session session = sessionFactory.openSession();
		Title title = (Title) session.get(Title.class, title_id);
		session.close();
		return title;
	}
	
	
}
