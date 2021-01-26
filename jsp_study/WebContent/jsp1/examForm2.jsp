<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>examForm2.jsp 페이지</h1>
	<hr>
	
	<form action="examProcess2.jsp" method="post">
		이름: <input type="text" name="name"><br>
		나이: <input type="number" name="age" min="0" max="150"><br>
		<input type="submit" value="전송">
	</form>

</body>
</html>



