package com.testcustom.daoImpl;

import java.util.List;

import com.testcustom.domain.Exam;
import com.testcustom.domain.Title;
import com.testcustom.utils.PageController;

public interface HistoryDaoImpl {

	public List<Exam> testUserExams1(int id, PageController pc);
	public List<Exam> testUserExams(int id);
}
