package com.testcustom.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testcustom.daoImpl.PageDaoImpl;
import com.testcustom.utils.PageController;


@Component
public class PageDao implements PageDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	public TitleDao titleDao;
	
	//初始取最大行数方法
	@Override
	public  int getTotalRows() {
		  Session session = sessionFactory.openSession();
//		 Long TotalRows = (long) 0;
		 
		 String hql = "select t from Title t";
		 Query query = session.createQuery(hql);
		 int TotalRows = query.list().size();
		 System.out.println("TotalRows===>"+TotalRows);
		 session.close();
		 
		return TotalRows;
	}
	
	//条件取最大行数方法
	public int getTotalRows(Properties pro) {
		StringBuilder sb = new StringBuilder("select t from Title t");
		String hql = getHQL(sb,pro);
//		Long TotalRows = (long) 0;
		 		 
	    Session session = sessionFactory.openSession();			
		Query query = session.createQuery(hql);
		int TotalRows =  query.list().size();
		System.out.println("TotalRows===>"+TotalRows);
		session.close();	
		 
		return  TotalRows;
	}
	
public String getHQL(StringBuilder sql,Properties pro) {
		
		if(pro!=null && pro.size()>0){
			sql.append(" where ");
		
			Set<Object> keys = pro.keySet();
			for(Object key:keys){
				String value = pro.getProperty((String)key);
				if(key.equals("title_major")){
					sql.append("t."+key+" like  '%"+value+"%' and ");
				}else{
					sql.append("t."+key+"="+value+" and ");
				}
			}
			sql.delete(sql.lastIndexOf("and"), sql.length());
		}
		System.out.println("sql==>"+sql);
		return sql.toString();
	}
	
	public List<Object> query(PageController pc) {
		Session session = sessionFactory.openSession();
	    List<Object> titles = new ArrayList<Object>();
			
			session = sessionFactory.openSession();
		    String hql = "select t from Title t";
			Query query = session.createQuery(hql);
			query.setFirstResult(pc.getPageStartRow());
			query.setMaxResults(pc.getPageSize());		
			titles = query.list();
		
		session.close();
		
		return titles;
	}


	public List<Object> mutilQueryForPage(String currPage, Properties pro,
			PageController pc) {
		
		pc.setCurrentPage(Integer.parseInt(currPage));
		
		List<Object> emps = null;
		
		emps = titleDao.query(pro, pc);
		
		
		return emps;
	}
	
	

}
