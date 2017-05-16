package com.testcustom.daoImpl;

import java.util.List;

import com.testcustom.domain.Title;

public interface TitleCartDaoImpl {
	public Title findTitleById(int title_id);
	public void cartInputHistory(int user_id, String exam_name, List<Integer> title_id);
}
