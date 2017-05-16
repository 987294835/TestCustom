package com.testcustom.dao;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import oracle.net.aso.q;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.CustomDaoImpl;
import com.testcustom.domain.Title;
import com.testcustom.domain.User;
/**
 * 试题定制Dao类实现
 * @author Administrator
 *
 */
@Component
public class CustomDao implements CustomDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 获取岗位
	 */
	public List<Object> SelectMajorList() {
//		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory(); 
		Session session = sessionFactory.openSession();
		String hql = "select distinct t.title_major from Title t";
		Query query = session.createQuery(hql);
		List<Object> titles = query.list();
		session.close();
		return titles;
	}
	
	/**
	 * 组合查询
	 */
	private String getHQL(StringBuilder sb, Properties pro) {
		StringBuilder hql = new StringBuilder("select t from Title t ");
		if(pro!=null && pro.size()>0){
			hql.append("where ");
			//遍历pro，将每一条记录追加到hql中
			Set<Object> keys = pro.keySet();
			for(Object key:keys){
				String value = pro.getProperty((String)key);
				System.out.println("key===>"+key);
				if(key.equals("title_major")){
					hql.append("t.title_major like '%"+value+"%' and ");
				}
				if(key.equals("title_backgroud")){
					hql.append("t.title_backgroud = "+value+" and ");
				}
//				if(key.equals("title_backgroud1")){
//					hql.append("t.title_backgroud>= "+value+" and ");
//				}
//				if(key.equals("title_major")){
//					hql.append("t."+key+" like  '%"+ value+"%' and ");
//				}else{
//					hql.append("t."+key+" =  '"+ value+"' and ");
//				}
//				System.out.println("value===>"+value);
			}
			hql.delete(hql.lastIndexOf("and"), hql.length());
			
		}
		System.out.println("hql===>"+hql);
		return hql.toString();
	}

	/**
	 * 根据条件查询
	 */
	public List<Title> QueryByCondition(Properties pro) {
		StringBuilder sb = new StringBuilder("select t from Title t");
		String hql = getHQL(sb, pro);
		System.out.println("QueryByCondition.hql===>"+hql);
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Title> titles = query.list();
		session.close();
		return titles;
	}
	
	/**
	 * 根据条件查询相对应类型和难度的试题
	 */
	public List<Title> QueryTypeByCondition(Properties pro,String type) {
		StringBuilder sb = new StringBuilder("select t from Title t");
		String hql = getHQL(sb, pro);
		if(pro.isEmpty()){
			hql = hql + "where t.title_type="+type+" order by t.title_num ";
		}else{
			hql = hql + "and t.title_type="+type+" order by t.title_num ";
		}
		System.out.println("QueryTypeByCondition.hql===>"+hql);
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Title> titles = query.list();
		session.close();
		return titles;
	}

	/**
	 * 根据id查询题目
	 */
	public Title queryTitleById(int title_id) {
		Session session = sessionFactory.openSession();
		Title title = (Title) session.get(Title.class, title_id);
		session.close();
		return title;
	}

	
	


}
