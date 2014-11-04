package com.ik.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ik.domain.User;
import com.ik.services.IUserService;

@ManagedBean(name = "usersBean")
@ViewScoped
@Component
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name, surname, email, login, password, phonenumber;
	private User selectedUser;

	@Autowired
	@Qualifier("userServices")
	IUserService userServices;

	private List<User> users;

	@PostConstruct
	public void init() {
		setUsers(userServices.getAllUsers());
	}

	public void loginUser(ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		User user = null;
		try {
			user = userServices.login(login, password);
			if (null != user) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/SpringMVC/userList.xhtml");
				return;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		message = new FacesMessage(FacesMessage.SEVERITY_WARN,
				"Error during logging", "Wrong username and/or password");

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", null != user);
	}

	public IUserService getUserServices() {
		return userServices;
	}

	public void setUserServices(IUserService userServices) {
		this.userServices = userServices;
	}

	public UsersBean() {
	}

	public void addUser(ActionEvent event) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPhoneNumber(phonenumber);
		user.setSurname(surname);
		user.setLogin(login);
		user.setPassword(password);
		try {
			this.userServices.addUser(user);
			setUsers(userServices.getAllUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editUser(ActionEvent event){
		try {
			userServices.updateUser(selectedUser);
			setUsers(userServices.getAllUsers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(ActionEvent event) {
		try {
			this.userServices.deleteUser(selectedUser);
			setUsers(userServices.getAllUsers());
			selectedUser = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	/*
	 * 
	 * ApplicationContext ctx =
	 * FacesContextUtils.getWebApplicationContext(FacesContext
	 * .getCurrentInstance()); ApplicationContext applicationContext = new
	 * ClassPathXmlApplicationContext(
	 * "classpath:/META-INF/spring/applicationContext.xml"); userServices =
	 * (IUserService) applicationContext.getBean("userServices");
	 */

}
