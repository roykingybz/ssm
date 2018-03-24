package com.gucheng.ssm.service;

import com.gucheng.ssm.model.User;

public interface UserService {
	public User findUser(String id,String password);

	public boolean update(User user);

	public User selectUserByUserid(String userid);
}
