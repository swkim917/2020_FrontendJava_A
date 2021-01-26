<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 스크립틀릿 영역 (자바 코드 영역)
	/*
	   자바 여러줄 주석
	*/
	String str = "문자열";

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
	String strDate = sdf.format(date);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 테스트</h1>
	<hr>
	
	<!-- html 주석 -->
	<%-- jsp 주석. 보안상 우수함. --%>
	
	
	<%-- 표현문 --%>
	<%=str %><br>
	<%=strDate %><br>
	<%=1+2 %><br>
</body>
</html>




