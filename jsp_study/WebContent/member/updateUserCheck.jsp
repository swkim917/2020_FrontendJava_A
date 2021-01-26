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

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 수정 전 사용자 패스워드 확인</h1>
	<hr>
	<form action="updateUserCheckPro.jsp" method="post">
		아이디: <input type="text" value="<%=id %>" readonly><br>
		패스워드: <input type="password" name="passwd"><br>
		<input type="submit" value="패스워드 확인">
	</form>
	
</body>
</html>


