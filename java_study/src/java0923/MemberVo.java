package java0923;

import java.sql.Timestamp;

public class MemberVo {
	
	private String id;
	private String passwd;
	private String name;
	private Timestamp regDate;
	
	public MemberVo() {
		super();
	}
	
	public MemberVo(String id, String passwd, String name) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
	}
	
	// 추가된 필드의 getter, setter 메소드 추가하기

	public String getId() {
		return id;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getName() {
		return name;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", passwd=" + passwd + ", name=" + name + ", regDate=" + regDate + "]";
	}
	
}




