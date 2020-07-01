package jp.co.schoo.model;

public class InsertOutputModel {

	private static final long serialVersionUID = 1L;

	private String msg;		// メッセージ
	private String id;		// ID
	private String password;// Password
	private String name;	// 名前
	private String age;		// 年齢


//	public InsertOutputModel() {
//		msg = "";
//		id  = "";
//		password = "";
//		name="";
//		age = 0;
//
//
//	}

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

}
