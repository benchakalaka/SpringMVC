package com.ik.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ik.dao.IUserDAO;
import com.ik.domain.User;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDao;

	@Override
	public IUserDAO getUserDao() {
		return userDao;
	}

	@Override
	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public UserService() {
	}

	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	@Override
	public User login(String login, String password) {
		return this.userDao.login(login, password);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userDao.listAll();
	}

	@Override
	public void deleteUser(User user) {
		this.userDao.deleteUser(user);
	}

	@Override
	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}
}