package net.it_tim.dude_of_dude.db_classes;

// Generated 14 квіт 2011 15:28:49 by Hibernate Tools 3.3.0.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

	private int userId;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String comment;
	private Set<Contacts> contactses = new HashSet<Contacts>(0);

	public Users() {
	}

	public Users(int userId) {
		this.userId = userId;
	}

	public Users(int userId, String name, String surname, String login,
			String password, String comment, Set<Contacts> contactses) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.comment = comment;
		this.contactses = contactses;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<Contacts> getContactses() {
		return this.contactses;
	}

	public void setContactses(Set<Contacts> contactses) {
		this.contactses = contactses;
	}

}
