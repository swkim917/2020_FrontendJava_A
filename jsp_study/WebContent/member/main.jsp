<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 세션값 가져오기
String id = (String) session.getAttribute("id");
// 세션값 없으면 loginForm.jsp 이동
if (id == null) {
	%>
	<script>
		alert('로그인 후 접근 가능한 페이지 입니다.');
		location.href = 'loginForm.jsp';
	</script>
	<%
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메인 페이지</h1>
	<%=id %> 님 로그인 하셨습니다.<br>
	<hr>
	<input type="button" value="로그아웃" onclick="location.href='logout.jsp'"><br>
	<a href="info.jsp">회원정보 조회</a><br>
	<a href="updateUserCheck.jsp">회원정보 수정</a><br>
	<a href="deleteUserCheck.jsp">회원정보 삭제</a><br>
<%
if (id.equals("admin")) {
	%>
	<a href="list.jsp">전체 회원목록 조회</a><br>
	<%
}
%>
</body>
</html>



