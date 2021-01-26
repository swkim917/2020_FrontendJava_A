package java0923;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	
	private Connection getConnection() throws Exception {
		// 헤로쿠 MySQL DB
		// mysql://bec477009e8b36:112f7808@us-cdbr-east-02.cleardb.com/heroku_2a9d67c8b09e7af?reconnect=true&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul
		
		// 헤로쿠DB id : bec477009e8b36
		// 헤로쿠DB pw : 112f7808
		// 헤로쿠DB hostname : us-cdbr-east-02.cleardb.com
		// 헤로쿠DB 스키마이름 : heroku_2a9d67c8b09e7af
		
		
		
		// DB접속정보
		String dbUrl = "jdbc:mysql://localhost:3306/jspdb?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
		String dbId = "myid";
		String dbPwd = "mypwd";
		
		Connection con = null;
		
		// 1단계. DB드라이버 클래스 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2단계. DB에 연결 시도. 연결후 Connection객체를 리턴함.
		con = DriverManager.getConnection(dbUrl, dbId, dbPwd);
		return con;
	} // getConnection()
	
	private void close(Connection con, PreparedStatement pstmt) {
		close(con, pstmt, null);
	}
	
	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close()
	

	// 회원정보 1명 insert하기
	public void addMember(MemberVo memberVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "";
			sql += "INSERT INTO member (id, passwd, name, reg_date) ";
			sql += "VALUES (?, ?, ?, now()) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPasswd());
			pstmt.setString(3, memberVo.getName());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생여부에 관계없이 무조건 정리작업 수행함.
			// try블록에서 만든 객체를 정리하는 작업을 주로 함
			close(con, pstmt);
		}
	} // addMember()
	
	
	// 전체 회원목록 가져오기
	public List<MemberVo> getAllMembers() {
		List<MemberVo> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			con = getConnection();
			
			sql = "SELECT * FROM member ORDER BY id";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVo memberVo = new MemberVo();
				memberVo.setId(rs.getString("id"));
				memberVo.setPasswd(rs.getString("passwd"));
				memberVo.setName(rs.getString("name"));
				memberVo.setRegDate(rs.getTimestamp("reg_date"));
				
				list.add(memberVo);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	} // getAllMembers()
	
	
	// 특정id에 해당하는 회원 1명 가져오기
	public MemberVo getMemberById(String id) {
		MemberVo memberVo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			con = getConnection();
			
			sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberVo = new MemberVo();
				memberVo.setId(rs.getString("id"));
				memberVo.setPasswd(rs.getString("passwd"));
				memberVo.setName(rs.getString("name"));
				memberVo.setRegDate(rs.getTimestamp("reg_date"));
			} // if
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return memberVo;
	} // getMemberById()
	
	// 특정id에 해당하는 회원의 이름 수정하기
	public void update(MemberVo memberVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "";
			sql += "UPDATE member ";
			sql += "SET name = ? ";
			sql += "WHERE id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVo.getName());
			pstmt.setString(2, memberVo.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생여부에 관계없이 무조건 정리작업 수행함.
			// try블록에서 만든 객체를 정리하는 작업을 주로 함
			close(con, pstmt);
		}
	} // addMember()
	
	// 특정id에 해당하는 회원 1명 삭제하기
	public void deleteById(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "";
			sql += "DELETE FROM member WHERE id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
	} // deleteById()
	
	
	// 모든 회원 삭제하기
	public void deleteAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "";
			sql += "DELETE FROM member ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
	} // deleteAll()
	
	
	public static void main(String[] args) {
		
		// MemberDao 객체 준비
		MemberDao memberDao = new MemberDao();
		
		memberDao.deleteAll();
		
		System.out.println("======== insert 테스트 =========");
		
		for (int i=0; i<5; i++) {
			MemberVo memberVo = new MemberVo("aaa"+i, "1234", "홍길동"+i);
			memberDao.addMember(memberVo);
		}
		
		List<MemberVo> list = memberDao.getAllMembers();
		for (MemberVo memberVo : list) {
			System.out.println(memberVo);
		}
		
		System.out.println("======== getMemberById 테스트 =========");
		
		MemberVo memberVo = memberDao.getMemberById("aaa0");
		System.out.println(memberVo);
		
		System.out.println("======== update 테스트 =========");
		
		memberVo.setName("이순신"); // 수정될 이름값 설정
		memberDao.update(memberVo);
		
		MemberVo getMemberVo = memberDao.getMemberById("aaa0");
		System.out.println(getMemberVo);
		
		System.out.println("======== deleteById 테스트 =========");
		
		memberDao.deleteById("aaa0");
		
		MemberVo getMemberVo2 = memberDao.getMemberById("aaa0");
		System.out.println(getMemberVo2);
		
	} // main
	
}






