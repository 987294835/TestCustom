package com.testcustom.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exam_table")
public class Exam{
	@Id
	@GeneratedValue
	private int exam_id;
	private String exam_name;
	private int exam_score;
	/*
	 * 和User有多对一关系
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	/*
	 * 和Title有多对多关系
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="history_table",inverseJoinColumns=@JoinColumn(name="title_id"),joinColumns=@JoinColumn(name="exam_id"))
	private List<Title> examTitles = new ArrayList<Title>();
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	public int getExam_score() {
		return exam_score;
	}
	public void setExam_score(int exam_score) {
		this.exam_score = exam_score;
	}
	public List<Title> getExamTitles() {
		return examTitles;
	}
	public void setExamTitles(List<Title> examTitles) {
		this.examTitles = examTitles;
	}
//	@Override
//	public String toString() {
//		return "Exam [exam_id=" + exam_id + ", exam_name=" + exam_name
//				+ ", exam_score=" + exam_score + ", user=" + user
//				+ ", examTitles=" + examTitles + "]";
//	}
	
}
