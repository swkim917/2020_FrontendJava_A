<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.exam.vo.MemberVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 세션값 가져오기
// 1) 로그인 안한경우 null 리턴
// 2) 로그인 했는데 관리자"admin" 아닌경우
//     -> main.jsp 로 이동
String id = (String) session.getAttribute("id");

//  !(id == null || !id.equals("admin"))
//  id != null && id.equals("admin")

if (id == null || !id.equals("admin")) {
	response.sendRedirect("main.jsp");
	return;
}

// DAO 객체 준비
MemberDao memberDao = MemberDao.getInstance();
// 전체회원정보 List로 가져오기
List<MemberVo> memberList = memberDao.getAllMembers();

SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체 회원목록 조회</h1>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>패스워드</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>이메일</th>
				<th>가입일자</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (MemberVo memberVo : memberList) {
				//Timestamp timestamp = memberVo.getRegDate();
				//Date date = new Date(timestamp.getTime());
				%>
				<tr>
					<td><%=memberVo.getId() %></td>
					<td><%=memberVo.getPasswd() %></td>
					<td><%=memberVo.getName() %></td>
					<td><%=memberVo.getGender() %></td>
					<td><%=memberVo.getAge() %></td>
					<td><%=(memberVo.getEmail() == null) ? "" : memberVo.getEmail() %></td>
					<td><%=sdf.format(memberVo.getRegDate()) %></td>
				</tr>
				<%
			}
			%>
		</tbody>
	</table>
	
	<h3><a href="main.jsp">메인화면</a></h3>
	
</body>
</html>




