package com.testcustom.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="title_table")
public class Title {
	@Id
	@GeneratedValue
	private int title_id;
	private String title_text;
	private int title_level;
	private String title_major;
	private int title_backgroud;
	private String title_option_a;
	private String title_option_b;
	private String title_option_c;
	private String title_option_d;
	private String title_explain;
	private int title_type;
	private int title_status;
	private String title_answer;
	private String title_time;
	private int title_num;
	/*
	 * 和User有多对多关系
	 */
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="userTitles")
	private List<User> users = new ArrayList<User>();
	/*
	 * 和Exam有多对多关系
	 */
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="examTitles")
	private List<Exam> exams = new ArrayList<Exam>();
	
	
	public int getTitle_id() {
		return title_id;
	}
	public void setTitle_id(int title_id) {
		this.title_id = title_id;
	}
	public String getTitle_text() {
		return title_text;
	}
	public void setTitle_text(String title_text) {
		this.title_text = title_text;
	}
	public int getTitle_level() {
		return title_level;
	}
	public void setTitle_level(int title_level) {
		this.title_level = title_level;
	}
	public String getTitle_major() {
		return title_major;
	}
	public void setTitle_major(String title_major) {
		this.title_major = title_major;
	}
	
	public int getTitle_backgroud() {
		return title_backgroud;
	}
	public void setTitle_backgroud(int title_backgroud) {
		this.title_backgroud = title_backgroud;
	}
	public String getTitle_option_a() {
		return title_option_a;
	}
	public void setTitle_option_a(String title_option_a) {
		this.title_option_a = title_option_a;
	}
	public String getTitle_option_b() {
		return title_option_b;
	}
	public void setTitle_option_b(String title_option_b) {
		this.title_option_b = title_option_b;
	}
	public String getTitle_option_c() {
		return title_option_c;
	}
	public void setTitle_option_c(String title_option_c) {
		this.title_option_c = title_option_c;
	}
	public String getTitle_option_d() {
		return title_option_d;
	}
	public void setTitle_option_d(String title_option_d) {
		this.title_option_d = title_option_d;
	}
	public String getTitle_explain() {
		return title_explain;
	}
	public void setTitle_explain(String title_explain) {
		this.title_explain = title_explain;
	}
	public int getTitle_type() {
		return title_type;
	}
	public void setTitle_type(int title_type) {
		this.title_type = title_type;
	}
	public int getTitle_status() {
		return title_status;
	}
	public void setTitle_status(int title_status) {
		this.title_status = title_status;
	}
	public String getTitle_answer() {
		return title_answer;
	}
	public void setTitle_answer(String title_answer) {
		this.title_answer = title_answer;
	}
	
	public String getTitle_time() {
		return title_time;
	}
	public void setTitle_time(String title_time) {
		this.title_time = title_time;
	}
	public int getTitle_num() {
		return title_num;
	}
	public void setTitle_num(int title_num) {
		this.title_num = title_num;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
//	@Override
//	public String toString() {
//		return "Title [title_id=" + title_id + ", title_text=" + title_text
//				+ ", title_level=" + title_level + ", title_major="
//				+ title_major + ", title_backgroud=" + title_backgroud
//				+ ", title_option_a=" + title_option_a + ", title_option_b="
//				+ title_option_b + ", title_option_c=" + title_option_c
//				+ ", title_option_d=" + title_option_d + ", title_explain="
//				+ title_explain + ", title_type=" + title_type
//				+ ", title_status=" + title_status + ", title_answer="
//				+ title_answer + ", title_time=" + title_time + ", title_num="
//				+ title_num + ", users=" + users + ", exams=" + exams + "]";
//	}
	
	
	
}
