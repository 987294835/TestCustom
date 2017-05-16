package com.testcustom.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class User {
	@Id
	@GeneratedValue
	private int user_id;
	private String user_name;
	private String user_password;
	private int user_type;
	private String user_address;
	private String user_email;
	private String user_phone;
	private String id_code;
	private String time;
	/*
	 * 和Title有多对多关系
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="collect_table",inverseJoinColumns=@JoinColumn(name="title_id"),joinColumns=@JoinColumn(name="user_id"))
	private List<Title> userTitles = new ArrayList<Title>();
	/*
	 * 和exam有一对多关系
	 */
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	private List<Exam> exams = new ArrayList<Exam>();
	
	/*
	 * 和job有一对多关系
	 */
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	private List<Job> jobs = new ArrayList<Job>();
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
	public List<Title> getUserTitles() {
		return userTitles;
	}
	public void setUserTitles(List<Title> userTitles) {
		this.userTitles = userTitles;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	
	
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public String getId_code() {
		return id_code;
	}
	public void setId_code(String id_code) {
		this.id_code = id_code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
//	@Override
//	public String toString() {
//		return "User [user_id=" + user_id + ", user_password=" + user_password
//				+ ", user_type=" + user_type + ", user_address=" + user_address
//				+ ", user_email=" + user_email + ", user_phone=" + user_phone
//				+ ", userTitles=" + userTitles + ", exams=" + exams + "]";
//	}
}
