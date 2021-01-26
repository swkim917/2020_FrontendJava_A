<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 파라미터값  reRef  reLev  reSeq   pageNum   가져오기
String reRef = request.getParameter("reRef"); // 답글을 쓰는 대상글에 대한 정보
String reLev = request.getParameter("reLev");
String reSeq = request.getParameter("reSeq");
String pageNum = request.getParameter("pageNum");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 답글쓰기</h1>
	<hr>
	<form action="reWritePro.jsp" method="post">
		<input type="hidden" name="pageNum" value="<%=pageNum %>">
		<input type="hidden" name="reRef" value="<%=reRef %>">
		<input type="hidden" name="reLev" value="<%=reLev %>">
		<input type="hidden" name="reSeq" value="<%=reSeq %>">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="passwd" required></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="[답글] " required></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="13" cols="40" name="content" required></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답글쓰기">
					<input type="reset" value="다시쓰기">
					<input type="button" value="글목록" onclick="location.href='list.jsp?pageNum=<%=pageNum %>'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>



