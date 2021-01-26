<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// post 파라미터값 한글처리
request.setCharacterEncoding("utf-8");

// 폼 파라미터값  id  passwd  가져오기
String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

// DAO 객체 준비
MemberDao memberDao = MemberDao.getInstance();

// 로그인 확인 메소드 호출   userCheck(id, passwd)
int check = memberDao.userCheck(id, passwd);
// check == 1   로그인 인증     main.jsp 이동
// check == 0   '패스워드 틀림'  뒤로이동
// check == -1  '아이디 없음'    뒤로이동
if (check == 1) {
	session.setAttribute("id", id);
	%>
	<script>
		alert('로그인 성공!');
		location.href = 'main.jsp';
	</script>
	<%
} else if (check == 0) {
	%>
	<script>
		alert('패스워드 틀림');
		history.back();
	</script>
	<%
} else { // check == -1
	%>
	<script>
		alert('아이디 없음');
		history.back();
	</script>
	<%
}
%>




