package jp.co.schoo.model;

import java.io.Serializable;

/**
 * @author Tomoyo.H
 *
 */

/**
 * Java入門 ログイン画面Modelクラス.
 */
public class HomeInputModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;		// メッセージ
	private String id;		// ID
	private String password;// Password
	private String role;

	public HomeInputModel() {
		msg = "";
		id  = "";
		password = "";
		setRole("");
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}