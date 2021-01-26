<%@page import="com.exam.vo.BoardVo"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// int num, String pageNum  파라미터값 가져오기
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

// DAO 객체준비
BoardDao boardDao = BoardDao.getInstance();

// 글번호에 해당하는 글한개 가져오기
BoardVo boardVo = boardDao.getBoardByNum(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
span#delFile {
	color: red;
	background-color: yellow;
	font-weight: bold;
	border: 1px solid black;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</head>
<body>
	<h1>게시판 글수정</h1>
	<hr>
	<form action="fileUpdatePro.jsp" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="<%=pageNum %>">
		<input type="hidden" name="num" value="<%=num %>">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="name" value="<%=boardVo.getName() %>" required></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="passwd" required></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="<%=boardVo.getSubject() %>" required></td>
			</tr>
			<tr>
				<th>파일</th>
				<td>
					<%
					if (boardVo.getFile() == null) {
						%>
						<input type="file" name="filename">
						<%
					} else {
						%>
						<div id="oldFileArea">
							<%=boardVo.getFile() %>
							<span id="delFile">X</span>
						</div>
						<input type="hidden" name="oldFilename" value="<%=boardVo.getFile() %>">
						<%
					}
					%>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="13" cols="40" name="content" required><%=boardVo.getContent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="파일글수정">
					<input type="reset" value="다시쓰기">
					<input type="button" value="글목록" onclick="location.href='list.jsp?pageNum=<%=pageNum %>'">
				</td>
			</tr>
		</table>
	</form>


<script src="../js/jquery-3.5.1.js"></script>
<script>
	// 기존 첨부파일 삭제 [X]를 클릭했을때
	$('span#delFile').click(function () {
		// hidden 태그의 name 속성값을 변경하기
		$(this).closest('div').next().prop('name', 'delFilename');

		$(this).closest('td').prepend('<input type="file" name="filename">');
		
		$(this).closest('div').remove();
	});
</script>
</body>
</html>



