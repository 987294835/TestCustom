package com.testcustom.daoImpl;

import java.util.List;

import com.testcustom.domain.User;

public interface UserLoginDaoImpl {
	public User selectUserByName(String user_name );
	public void inputUser(User user);

}
