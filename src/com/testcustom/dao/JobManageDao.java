package com.testcustom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.JobManageDaoImpl;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Job;
import com.testcustom.domain.Person;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;

@Component
public class JobManageDao implements JobManageDaoImpl {

	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	
	}
	/**
	 * ��Ӹ�λ
	 */
	@Override
	public void addJob(Job job) {
		Session session = sessionFactory.openSession();
		session.save(job);
		session.close();
	}
	/**
	 * �鿴���˷����ĸ�λ
	 * @param user_id
	 * @return
	 */
	public List<Job> showJobs(int user_id, PageController pc){
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, user_id);
		List<Job> jobs = user.getJobs();
		List<Job> new_jobs = new ArrayList<Job>();
		for(Job job : jobs){
		}
		session.close();
		for(int i = pc.getPageStartRow(); i<pc.getPageStartRow()+pc.getPageSize() && i<jobs.size(); i++){
			new_jobs.add(jobs.get(i));
		}
		return new_jobs;	
	}
	
	/**
	 * ��ȡ���˷�����λ������
	 * @param user_id
	 * @return
	 */
	public List<Job> getJobsSize(int user_id){
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, user_id);
		List<Job> jobs = user.getJobs();
		for(Job job : jobs){
		}
		session.close();
		return jobs; 
	}
	
	/**
	 * �鿴������λ��Ϣ
	 * @param job_id
	 * @return
	 */
	public Job seeJob(int job_id){
		Session session = sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, job_id);
		session.close();
		return job;
	}
	/**
	 * ɾ����λ
	 * @param job_id
	 */
	public void deleteJob(int job_id){
		Session session = sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, job_id);
		session.beginTransaction();
		session.delete(job);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * �޸ĸ�λ�ķ���״̬
	 * @param job_id
	 */
	public void publishJob(int job_id, int i){
		Session session = sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, job_id);
		if(i == 0){
			job.setJob_state(1);//����
		}else if(i == 1){
			job.setJob_state(0); //ȡ������
		}
		session.beginTransaction();
		session.update(job);
		session.getTransaction().commit();
		session.close();
	}
	/**
	 * ��ȡ���Ƶ�����
	 * @return
	 */
	public List<Exam> getMyExams(int user_id){
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, user_id);
		List<Exam> exams = user.getExams();
		for(Exam exam : exams){
		}
		session.close();
		return exams;
	}
	
	public void resetJob(int job_id,String job_name,String job_message,String job_request,int exam_id,String time){
		Session session = sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, job_id);
		job.setJob_name(job_name);
		job.setJob_request(job_request);
		job.setJob_message(job_message);
		job.setExam_id(exam_id);
		job.setJob_start_time(time);
		session.beginTransaction();
		session.update(job);
		session.getTransaction().commit();
		session.close();
	}
	
	public void setJobExam(int job_id, int exam_id){
		Session session = sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, job_id);
		job.setExam_id(exam_id);
		session.beginTransaction();
		session.update(job);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * �鿴��λӦƸ��Ա���
	 * @param job_id
	 * @return
	 */
	public List<Person> see_persons(int job_id){
		Session session = sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, job_id);
		List<Person> persons = job.getPersons();
		for(Person person : persons){
			
		}
		session.close();
		if(persons.size() != 0){
			return persons;
		}else{
			return null;
		}		
	}
	
	
}
