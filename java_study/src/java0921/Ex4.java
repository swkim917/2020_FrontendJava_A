package java0921;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ex4 {

	public static void main(String[] args) {
		// UPDATE문 수행하기

		// DB접속정보
		String dbUrl = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
		String dbId = "myid";
		String dbPwd = "mypwd";
		
		// update 내용
		int actorId = 202; // 변경 기준 (where조건)
		String lastName = "고"; // 변경할 값
		
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1단계. DB드라이버 클래스 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2단계. DB에 연결 시도. 연결후 Connection객체를 리턴함.
			con = DriverManager.getConnection(dbUrl, dbId, dbPwd);
			
			// sql문 준비
			sql  = "UPDATE actor ";
			sql += "SET last_name = ? ";
			sql += "WHERE actor_id = ? ";
			
			// 3단계. sql문장객체 준비
			pstmt = con.prepareStatement(sql);
			// 값 설정
			pstmt.setString(1, lastName);
			pstmt.setInt(2, actorId);
			
			// sql문장 실행
			int count = pstmt.executeUpdate(); // INSERT,UPDATE,DELETE문장 실행시 호출
			
			System.out.println(count + "개 행이 수정됨.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // main

}
