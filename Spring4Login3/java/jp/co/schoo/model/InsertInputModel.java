package jp.co.schoo.model;


import java.io.Serializable;

/**
 * @author Tomoyo.H
 *
 */

/**
 * Java入門 ユーザ新規登録画面Modelクラス.
 */
public class InsertInputModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;		// メッセージ
	private String id;		// ID
	private String password;// Password
	private String name;	// 名前
	private String age;

//	public InsertInputModel() {
//		msg = "こんにちは";
//		id  = "";
//		password = "";
//		age=0;
//		name="";
//
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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


}