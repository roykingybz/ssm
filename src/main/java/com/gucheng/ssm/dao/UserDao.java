package com.gucheng.ssm.dao;

import com.gucheng.ssm.model.User;

public interface UserDao {
	
	public User findUser(String id,String password);

	public boolean update(User user);

	public User selectUserByUserid(String userid);
}
