package com.exam.vo;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
//@Data
public class MemberVo {
	
	private String id;
	private String passwd;
	private String name;
	private Integer age;    // 나이
	private String gender;  // 성별
	private String email;   // 이메일 주소
	private Timestamp regDate;
	private String address;
	private String tel;
}




