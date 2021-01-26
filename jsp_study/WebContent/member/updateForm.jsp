<%@page import="com.exam.vo.MemberVo"%>
<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션값 id 가져오기
String id = (String) session.getAttribute("id");
//세션값 id 없으면  loginForm.jsp 이동
if (id == null) {
	response.sendRedirect("loginForm.jsp");
	return;
}

//DAO 객체 준비
MemberDao memberDao = MemberDao.getInstance();
//현재 로그인된 id 기준으로 회원정보 한개 가져오기
MemberVo memberVo = memberDao.getMemberById(id);

String gender = memberVo.getGender();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 수정</h1>
	<hr>
	<form action="updatePro.jsp" method="post">
		아이디: <input type="text" name="id" value="<%=memberVo.getId() %>" readonly><br>
		패스워드: <input type="password" name="passwd" value="<%=memberVo.getPasswd() %>"><br>
		이름: <input type="text" name="name" value="<%=memberVo.getName() %>"><br>
		나이: <input type="number" name="age" min="0" max="200" value="<%=memberVo.getAge() %>"><br>
		성별: <input type="radio" name="gender" value="남" <%=(gender.equals("남")) ? "checked" : "" %>>남성
			  <input type="radio" name="gender" value="여" <%=(gender.equals("여")) ? "checked" : "" %>>여성<br>
		이메일: <input type="email" name="email" value="<%=(memberVo.getEmail() == null) ? "" : memberVo.getEmail() %>"><br>
		<input type="submit" value="회원수정">
	</form>
	
</body>
</html>




