package com.testcustom.daoImpl;

import com.testcustom.domain.User;

public interface UserCenterDaoImpl {
	
	public User selectUserById(int user_id);
	public void updateUser(User user);

}
