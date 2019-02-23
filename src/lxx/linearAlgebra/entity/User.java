package lxx.linearAlgebra.entity;

public class User {

	private int uid;
	private String uname;
	private String upwd;
	private String uemail;
	private String upermissions;
	private int state;
	private String code;

	@Override
	public String toString() {
		return "User [uname=" + uname + ", upwd=" + upwd + ", uemail=" + uemail + "]";
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User() {
	}
	
	public User(String uname, String upwd) {
		this.uname = uname;
		this.upwd = upwd;
	}
	
	public User(String uname, String upwd, String uemail) {
		this.uname = uname;
		this.upwd = upwd;
		this.uemail = uemail;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUpermissions() {
		return upermissions;
	}

	public void setUpermissions(String upermissions) {
		this.upermissions = upermissions;
	}

}
