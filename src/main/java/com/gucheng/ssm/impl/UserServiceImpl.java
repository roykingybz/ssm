package com.gucheng.ssm.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gucheng.ssm.dao.UserDao;
import com.gucheng.ssm.model.User;
import com.gucheng.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public User findUser(String id, String password) {
		return userDao.findUser(id, password);
	}

	@Override
	@Transactional
	public boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public User selectUserByUserid(String userid) {
		return userDao.selectUserByUserid(userid);
	}

}
