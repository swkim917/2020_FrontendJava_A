package java0922;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// select, insert, update, delete sql문 수행 기능
// DAO(Data Access Object) : 데이터를 조회,추가,수정,삭제 가능한 객체
public class ActorDao {
	
	public Connection getConnection() throws Exception {
		// DB접속정보
		String dbUrl = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
		String dbId = "myid";
		String dbPwd = "mypwd";
		
		Connection con = null;
		
		// 1단계. DB드라이버 클래스 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2단계. DB에 연결 시도. 연결후 Connection객체를 리턴함.
		con = DriverManager.getConnection(dbUrl, dbId, dbPwd);
		
		return con;
	} // getConnection()
	
	
	public List<ActorVo> getActors() {
		List<ActorVo> list = new ArrayList<>();

		// SELECT문 실행에 필요한 JDBC 객체 3가지
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";

		try {
			con = getConnection();
			
			// 3단계. sql문장객체 준비
			sql = "SELECT * FROM actor ORDER BY actor_id DESC";
			pstmt = con.prepareStatement(sql);
			// 실행 후 select결과를 ResultSet으로 받음
			rs = pstmt.executeQuery(); // SELECT문 수행시 호출함.
			
			// 4단계. ResultSet 데이터 꺼내서 사용
			while (rs.next()) {
				ActorVo actorVo = new ActorVo();
				actorVo.setActorId(rs.getInt("actor_id"));
				actorVo.setFirstName(rs.getString("first_name"));
				actorVo.setLastName(rs.getString("last_name"));
				actorVo.setLastUpdate(rs.getTimestamp("last_update"));
				
				list.add(actorVo);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // getActors()
	
	
	public ActorVo getActorById(int actorId) {
		ActorVo actorVo = null;

		// SELECT문 실행에 필요한 JDBC 객체 3가지
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";

		try {
			con = getConnection();
			
			// 3단계. sql문장객체 준비
			sql = "SELECT * FROM actor WHERE actor_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, actorId);
			
			// 실행 후 select결과를 ResultSet으로 받음
			rs = pstmt.executeQuery(); // SELECT문 수행시 호출함.
			
			// 4단계. ResultSet 데이터 꺼내서 사용
			if (rs.next()) {
				actorVo = new ActorVo();
				actorVo.setActorId(rs.getInt("actor_id"));
				actorVo.setFirstName(rs.getString("first_name"));
				actorVo.setLastName(rs.getString("last_name"));
				actorVo.setLastUpdate(rs.getTimestamp("last_update"));
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actorVo;
	}
	
	
	public ActorVo getActorByFirstName(String firstName) {
		ActorVo actorVo = null;

		// SELECT문 실행에 필요한 JDBC 객체 3가지
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";

		try {
			con = getConnection();
			
			// 3단계. sql문장객체 준비
			sql = "SELECT * FROM actor WHERE first_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, firstName);
			
			// 실행 후 select결과를 ResultSet으로 받음
			rs = pstmt.executeQuery(); // SELECT문 수행시 호출함.
			
			// 4단계. ResultSet 데이터 꺼내서 사용
			if (rs.next()) {
				actorVo = new ActorVo();
				actorVo.setActorId(rs.getInt("actor_id"));
				actorVo.setFirstName(rs.getString("first_name"));
				actorVo.setLastName(rs.getString("last_name"));
				actorVo.setLastUpdate(rs.getTimestamp("last_update"));
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actorVo;
	}
	
	
	public int addActor(ActorVo actorVo) {
		int count = 0;
		
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			// sql문 준비
			sql  = "INSERT INTO actor (first_name, last_name, last_update) ";
			sql += "VALUES (?, ?, now())";
			
			// 3단계. sql문장객체 준비
			pstmt = con.prepareStatement(sql);
			// 값 설정
			pstmt.setString(1, actorVo.getFirstName());
			pstmt.setString(2, actorVo.getLastName());
			
			// sql문장 실행
			count = pstmt.executeUpdate(); // INSERT,UPDATE,DELETE문장 실행시 호출
			
			System.out.println(count + "개 행이 추가됨.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	} // addActor()
	
	
	
	public int deleteActorByFirstName(String firstName) {
		int count = 0;
		// DELETE문 수행하기
		
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			// sql문 준비
			sql  = "DELETE FROM actor ";
			sql += "WHERE first_name = ? ";
			
			// 3단계. sql문장객체 준비
			pstmt = con.prepareStatement(sql);
			// 값 설정
			pstmt.setString(1, firstName);
			
			// sql문장 실행
			count = pstmt.executeUpdate(); // INSERT,UPDATE,DELETE문장 실행시 호출
			
			System.out.println(count + "개 행이 삭제됨.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	} // deleteActorByFirstName()
	
	
	
	public void updateActorByFirstName(ActorVo actorVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = getConnection();
			
			// sql문 준비
			sql  = "UPDATE actor ";
			sql += "SET last_name = ? ";
			sql += "WHERE first_name = ? ";
			
			// 3단계. sql문장객체 준비
			pstmt = con.prepareStatement(sql);
			// 값 설정
			pstmt.setString(1, actorVo.getLastName());
			pstmt.setString(2, actorVo.getFirstName());
			
			// sql문장 실행
			int count = pstmt.executeUpdate(); // INSERT,UPDATE,DELETE문장 실행시 호출
			
			System.out.println(count + "개 행이 수정됨.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // updateActorByFirstName()
	
	
	
	public static void main(String[] args) {
	
		ActorDao actorDao = new ActorDao();
		/*
		List<ActorVo> list = actorDao.getActors();
		
		for (ActorVo actorVo : list) {
			System.out.println(actorVo.toString());
		}
		
		System.out.println("요소의 갯수: " + list.size());
		
		System.out.println("========================");
		
		ActorVo actorVo1 = actorDao.getActorById(203);
		System.out.println("actorVo1 : " + actorVo1);
		
		ActorVo actorVo2 = actorDao.getActorById(204);
		System.out.println("actorVo2 : " + actorVo2);
		
		if (actorVo2 == null) {
			
		} else { // actorVo2 != null
			
		}
		*/
		
		System.out.println("========= insert 테스트 ===============");
		
		// 샘플데이터 2개 준비
		ActorVo actorVo3 = new ActorVo();
		actorVo3.setFirstName("길동");
		actorVo3.setLastName("홍");
		
		ActorVo actorVo4 = new ActorVo("사임당", "신");
		
		// insert 테스트
		actorDao.addActor(actorVo3);
		actorDao.addActor(actorVo4);
		
		System.out.println("========= insert 결과 확인 ===============");
		
		ActorVo actorVoDb1 = actorDao.getActorByFirstName(actorVo3.getFirstName());
		ActorVo actorVoDb2 = actorDao.getActorByFirstName(actorVo4.getFirstName());
		
		System.out.println(actorVoDb1.toString());
		System.out.println(actorVoDb2.toString());
		
		System.out.println("========= update 테스트 ===============");
		
		actorVo3.setLastName("고"); // 성씨를 수정될 값으로 설정
		actorVo4.setLastName("서");
		
		actorDao.updateActorByFirstName(actorVo3);
		actorDao.updateActorByFirstName(actorVo4);
		
		System.out.println("========= update 결과 확인 ===============");
		
		ActorVo actorVoDb3 = actorDao.getActorByFirstName(actorVo3.getFirstName());
		ActorVo actorVoDb4 = actorDao.getActorByFirstName(actorVo4.getFirstName());
		
		System.out.println(actorVoDb3.toString());
		System.out.println(actorVoDb4.toString());
		
		System.out.println("========= delete 테스트 ===============");
		
		actorDao.deleteActorByFirstName(actorVo3.getFirstName());
		actorDao.deleteActorByFirstName(actorVo4.getFirstName());
		
	} // main
	
	
} // class ActorDao



