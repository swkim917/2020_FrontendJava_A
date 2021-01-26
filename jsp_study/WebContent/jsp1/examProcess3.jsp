<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// post 요청 파라미터 한글 처리
	request.setCharacterEncoding("utf-8");

	// 파라미터 name gender job hobby 가져와서 변수에 저장
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String job = request.getParameter("job");
	
	// checkbox 파라미터 value값이 여러개 -> 배열변수로 저장
	// 체크박스를 한개도 선택하지 않으면 null을 리턴함
	String[] hobbies = request.getParameterValues("hobby");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

이름 : <%=name %><br>
성별 : <%=gender %><br>
직업 : <%=job %><br>
취미 : 
<%
	if (hobbies != null) {
		for (String hobby : hobbies) {
			//out.print(hobby + " ");
			%>
			<%=hobby %> 
			<%
		}
	}
%>


</body>
</html>




