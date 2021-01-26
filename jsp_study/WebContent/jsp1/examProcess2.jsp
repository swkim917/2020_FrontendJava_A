<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// post 요청 파라미터 한글 처리
	request.setCharacterEncoding("utf-8");

	// 파라미터 name age 가져와서 변수에 저장
	String name = request.getParameter("name");
	String strAge = request.getParameter("age");
	
	// 문자열 -> 정수형
	//int age = Integer.parseInt(strAge);
	int age = Integer.parseInt(request.getParameter("age"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

이름 : <%=name %><br>
나이 : <%=age %><br>

<%
	// 나이가 20살 이상이면 "..님의 나이는 20살 이상입니다."
	//             미만이면 "..님은 미성년자 입니다."
	if (age >= 20) {
		out.println(name + "님의 나이는 20살 이상입니다.<br>");
	} else { // age < 20
		out.println(name + "님은 미성년자 입니다.<br>");
	}
%>
<hr>
<%
	// 나이가 20살 이상이면 "..님의 나이는 20살 이상입니다."
	//             미만이면 "..님은 미성년자 입니다."
	if (age >= 20) {
		%>
		<%=name %>님의 나이는 20살 이상입니다.<br>
		<%
	} else { // age < 20
		%>
		<%=name %>님은 미성년자 입니다.<br>
		<%
	}
%>
</body>
</html>




