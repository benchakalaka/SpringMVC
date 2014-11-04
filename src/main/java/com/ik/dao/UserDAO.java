/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ik.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ik.domain.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public User findById(Long id) {
		List<User> users = (List<User>) sessionFactory.getCurrentSession()
				.createQuery("from User where id = :id").setParameter("id", id)
				.list();
		return users.isEmpty() ? null : users.get(0);
	}

	@Transactional
	public List<User> listAll() {
		return sessionFactory.getCurrentSession().createQuery("from User")
				.list();
	}

	@Transactional
	public User login(String login, String password) {
		List<User> users = (List<User>) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User where login = :login and password = :password")
				.setParameter("login", login)
				.setParameter("password", password).list();
		return users.isEmpty() ? null : users.get(0);
	}

	@Transactional
	public void addUser(User user) {
		sessionFactory.getCurrentSession().persist(user);
	}

	@Transactional
	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

}
