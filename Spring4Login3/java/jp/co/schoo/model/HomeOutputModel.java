package jp.co.schoo.model;

import java.io.Serializable;

/**
 * @author Tomoyo.H
 *
 */

/**
 * Java入門 ログイン画面Modelクラス.
 */
public class HomeOutputModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;		// ID
	private String name;	// 名前
	private int age;		// 年齢
	private String password;
	private String role;

	public HomeOutputModel() {
		id   = "";
		name = "";
		age  = 0;
		role ="";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}