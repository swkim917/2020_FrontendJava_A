package com.exam.vo;

import java.sql.Timestamp;

public class BoardVo {
	private int num;
	private String name;
	private String passwd;
	private String subject;
	private String content;
	private int readcount;
	private Timestamp regDate;
	private String ip;
	private String file;
	private int reRef;  // 그룹번호. 주글번호는 그룹번호가 됨.
	private int reLev;  // 들여쓰기 레벨
	private int reSeq;  // 그룹 내에서의 순번
	
	public int getNum() {
		return num;
	}
	public String getName() {
		return name;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getSubject() {
		return subject;
	}
	public String getContent() {
		return content;
	}
	public int getReadcount() {
		return readcount;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public String getIp() {
		return ip;
	}
	public String getFile() {
		return file;
	}
	public int getReRef() {
		return reRef;
	}
	public int getReLev() {
		return reLev;
	}
	public int getReSeq() {
		return reSeq;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public void setReRef(int reRef) {
		this.reRef = reRef;
	}
	public void setReLev(int reLev) {
		this.reLev = reLev;
	}
	public void setReSeq(int reSeq) {
		this.reSeq = reSeq;
	}
	
	@Override
	public String toString() {
		return "BoardVo [num=" + num + ", name=" + name + ", passwd=" + passwd + ", subject=" + subject + ", content="
				+ content + ", readcount=" + readcount + ", regDate=" + regDate + ", ip=" + ip + ", file=" + file
				+ ", reRef=" + reRef + ", reLev=" + reLev + ", reSeq=" + reSeq + "]";
	}
}
