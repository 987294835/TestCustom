package com.testcustom.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="job_table")
public class Job {
	@Id
	@GeneratedValue
	private int job_id;
	private String job_name;
	private String job_message;
	private String job_request;
	
	/*
	 * 与user存在多对一的关系
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private int exam_id;
	private int job_state;
	private String job_time;
	private String job_start_time;
	
	public String getJob_start_time() {
		return job_start_time;
	}

	public void setJob_start_time(String job_start_time) {
		this.job_start_time = job_start_time;
	}

	/**
	 * 与person存在一对多关系
	 */
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="job")
	private List<Person> persons = new ArrayList<Person>();

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public int getJob_state() {
		return job_state;
	}

	public void setJob_state(int job_state) {
		this.job_state = job_state;
	}

	public String getJob_time() {
		return job_time;
	}

	public void setJob_time(String job_time) {
		this.job_time = job_time;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getJob_message() {
		return job_message;
	}

	public void setJob_message(String job_message) {
		this.job_message = job_message;
	}

	public String getJob_request() {
		return job_request;
	}

	public void setJob_request(String job_request) {
		this.job_request = job_request;
	}

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
	
	
}
