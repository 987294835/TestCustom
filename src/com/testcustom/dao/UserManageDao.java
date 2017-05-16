package com.testcustom.dao;

import java.util.List;

import javax.annotation.Resource;

import oracle.net.aso.p;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.UserManageDaoImpl;
import com.testcustom.domain.Exam;
import com.testcustom.domain.Job;
import com.testcustom.domain.User;
import com.testcustom.utils.PageController;
/**
 * 用户管理Dao类实现
 * @author Administrator
 *
 */
@Component
public class UserManageDao implements UserManageDaoImpl{

	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 分页查询用户列表
	 */
	public List<User> queryAllUser(PageController pc) {
		Session session = sessionFactory.openSession();
		String hql = "select u from User u";
		Query query = session.createQuery(hql);
		query.setFirstResult(pc.getPageStartRow());
		query.setMaxResults(pc.getPageSize());
		List<User> users = query.list();
		session.close();
		return users;
	}

	/**
	 * 查询条数
	 */
	public int getTotalrows() {
		Session session = sessionFactory.openSession();
		String hql = "select u from User u";
		Query query = session.createQuery(hql);
		int totalRows = query.list().size();
		session.close();
		return totalRows;
	}
	
	/**
	 * 删除用户
	 */
	public void deleteUser(int user_id) {
		Session session = sessionFactory.openSession();
//		String hql = "delete from User u where u.user_id =?";
//		Query query = session.createQuery(hql);
//		query.setInteger(0, user_id);
//		query.executeUpdate();
		User user = (User) session.get(User.class, user_id);
		System.out.println("User===>"+user.getUser_id());
		session.beginTransaction();
		if(null != user){
			for(Exam exam : user.getExams()){
				exam.getExamTitles().clear();  //解除exam和title的关联
			}
			user.getUserTitles().clear();  //解除user和title的关联
			session.delete(user);
		}
		session.getTransaction().commit();
		session.close();
	}
	/**
	 * 修改角色
	 */
	public void changeType(int user_id, int user_type){
		Session session = sessionFactory.openSession();
		String hql = "update User u set u.user_type = ? where u.user_id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, user_type);
		query.setInteger(1, user_id);
		query.executeUpdate();
		session.close();
	}
	
	/**
	 * 获取所有试卷
	 * @return
	 */
	public List<Exam> ALLExams(){
		Session session = sessionFactory.openSession();
		String hql = "select e from Exam e";
		Query query = session.createQuery(hql);
		List<Exam> exams = query.list();
		session.close();
		return exams;
	}
	/**
	 * 分页获取试卷
	 * @param pc
	 * @return
	 */
	public List<Exam> ALLExamsByPage(PageController pc){
		Session session = sessionFactory.openSession();
		String hql = "select e from Exam e";
		Query query = session.createQuery(hql);
		query.setFirstResult(pc.getPageStartRow());
		query.setMaxResults(pc.getPageSize());
		List<Exam> exams  = query.list();
		session.close();
		return exams;
	}
	
	/**
	 * 获取所有岗位
	 * @return
	 */
	public List<Job> ALLJobs(){
		Session session = sessionFactory.openSession();
		String hql = "select j from Job j";
		Query query = session.createQuery(hql);
		List<Job> jobs = query.list();
		session.close();
		return jobs;
	}

	/**
	 * 分页获取岗位
	 * @param pc
	 * @return
	 */
	public List<Job> ALLJobsByPage(PageController pc){
		Session session = sessionFactory.openSession();
		String hql = "select j from Job j";
		Query query = session.createQuery(hql);
		query.setFirstResult(pc.getPageStartRow());
		query.setMaxResults(pc.getPageSize());
		List<Job> jobs = query.list();
		session.close();
		return jobs;
	}
}
