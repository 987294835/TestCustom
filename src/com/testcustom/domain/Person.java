package com.testcustom.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="person_table")
public class Person {
	@Id
	@GeneratedValue
	private int person_id;
	private String person_name;
	private String person_password;
	private String person_phone;
	private String person_email;
	private int person_age;
	private int person_sex;
	private String person_school;
	private String person_education;
	private String person_practice;
	private String person_project;
	private String  person_exam_code;
	private int person_score;
	private String person_code;
	private String person_time;
	private int person_state;
	public int getPerson_state() {
		return person_state;
	}
	public void setPerson_state(int person_state) {
		this.person_state = person_state;
	}
	/**
	 * 与job存在多对一的关系
	 */
	@ManyToOne
	@JoinColumn(name="job_id")
	private Job job;
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getPerson_password() {
		return person_password;
	}
	public void setPerson_password(String person_password) {
		this.person_password = person_password;
	}
	public String getPerson_phone() {
		return person_phone;
	}
	public void setPerson_phone(String person_phone) {
		this.person_phone = person_phone;
	}
	public String getPerson_email() {
		return person_email;
	}
	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}
	public int getPerson_age() {
		return person_age;
	}
	public void setPerson_age(int person_age) {
		this.person_age = person_age;
	}
	public int getPerson_sex() {
		return person_sex;
	}
	public void setPerson_sex(int person_sex) {
		this.person_sex = person_sex;
	}
	public String getPerson_school() {
		return person_school;
	}
	public void setPerson_school(String person_school) {
		this.person_school = person_school;
	}
	public String getPerson_education() {
		return person_education;
	}
	public void setPerson_education(String person_education) {
		this.person_education = person_education;
	}
	public String getPerson_practice() {
		return person_practice;
	}
	public void setPerson_practice(String person_practice) {
		this.person_practice = person_practice;
	}
	public String getPerson_project() {
		return person_project;
	}
	public void setPerson_project(String person_project) {
		this.person_project = person_project;
	}
	
	public String getPerson_exam_code() {
		return person_exam_code;
	}
	public void setPerson_exam_code(String person_exam_code) {
		this.person_exam_code = person_exam_code;
	}
	
	public int getPerson_score() {
		return person_score;
	}
	public void setPerson_score(int person_score) {
		this.person_score = person_score;
	}
	public String getPerson_code() {
		return person_code;
	}
	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}
	public String getPerson_time() {
		return person_time;
	}
	public void setPerson_time(String person_time) {
		this.person_time = person_time;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
}
