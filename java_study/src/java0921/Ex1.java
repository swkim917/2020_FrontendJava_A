package java0921;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex1 {

	public static void main(String[] args) {
		System.out.println("=== 프로그램 시작 ===");
		// DB연결 테스트
		
		// DB접속정보
		String dbUrl = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
		String dbId = "myid";
		String dbPwd = "mypwd";
		
		Connection con = null;
		try {
			// MySQL용 DB드라이버 클래스를 강제 로딩
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			// 메모리에 로딩된 DB드라이버 클래스를 이용해서 DB에 연결 시도.
			// 연결이 성공하면 Connection 연결 객체를 리턴해줌.
			con = DriverManager.getConnection(dbUrl, dbId, dbPwd);
			System.out.println("DB연결 성공!");
		} catch (Exception e) {
			System.out.println("DB연결 실패...");
			e.printStackTrace();
		}
		System.out.println("=== 프로그램 종료 ===");
	} // main

}
