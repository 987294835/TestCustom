package com.testcustom.daoImpl;
/**
 * 用户管理
 */
import java.util.List;

import com.testcustom.domain.User;
import com.testcustom.utils.PageController;

public interface UserManageDaoImpl {
	
	public List<User> queryAllUser(PageController pc);
	public int getTotalrows();
	public void deleteUser(int user_id);
	public void changeType(int user_id, int user_type);

}
