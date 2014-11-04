package com.ik.services;

import java.util.List;

import com.ik.dao.IUserDAO;
import com.ik.domain.User;

public interface IUserService {
	public User login(String username, String password);

	public void addUser(User user);
	
	public void updateUser(User user);
	
	
	public void deleteUser(User user);

	public List<User> getAllUsers();

	public IUserDAO getUserDao();

	public void setUserDao(IUserDAO userDao);
}
