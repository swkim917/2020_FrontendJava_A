<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.exam.vo.MemberVo"%>
<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 세션값 id 가져오기
String id = (String) session.getAttribute("id");
// 세션값 id 없으면  loginForm.jsp 이동
if (id == null) {
	response.sendRedirect("loginForm.jsp");
	return;
}

// DAO 객체 준비
MemberDao memberDao = MemberDao.getInstance();
// 현재 로그인된 id 기준으로 회원정보 한개 가져오기
MemberVo memberVo = memberDao.getMemberById(id);

SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 조회</h1>
	<hr>
	아이디: <%=memberVo.getId() %><br>
	패스워드: <%=memberVo.getPasswd() %><br>
	이름: <%=memberVo.getName() %><br>
	나이: <%=memberVo.getAge() %><br>
	성별: <%=memberVo.getGender() %><br>
	이메일: <%=(memberVo.getEmail() == null) ? "" : memberVo.getEmail() %><br>
	가입날짜: <%=sdf.format(memberVo.getRegDate()) %><br>

	<h3><a href="main.jsp">메인화면</a></h3>
	
</body>
</html>


