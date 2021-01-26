<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- http://localhost:80/jsp_study/jsp1/examForm1.jsp -->
	<h1>examForm1.jsp 페이지</h1>
	<hr>
	
	<form action="examProcess.jsp" method="get">
		숫자를 입력:
		<input type="number" name="num" min="0" max="100">
		<input type="submit" value="전송">
	</form>

</body>
</html>



