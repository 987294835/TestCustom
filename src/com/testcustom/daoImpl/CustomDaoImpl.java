package com.testcustom.daoImpl;
/**
 *  ‘Ã‚∂®÷∆
 */
import java.util.List;
import java.util.Properties;

import com.testcustom.domain.Title;

public interface CustomDaoImpl {

	public List<Object> SelectMajorList();
	public List<Title> QueryByCondition(Properties pro);
	public List<Title> QueryTypeByCondition(Properties pro,String type);
	public Title queryTitleById(int title_id);
}
