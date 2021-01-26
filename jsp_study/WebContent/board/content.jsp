<%@page import="com.exam.vo.BoardVo"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파라미터값 int num, String pageNum 가져오기
	int num = Integer.parseInt(request.getParameter("num")); // 글번호
	String pageNum = request.getParameter("pageNum"); // 페이지번호

	// DAO 객체 준비
	BoardDao boardDao = BoardDao.getInstance();
	
	// 조회수 1 증가하기
	boardDao.updateReadcount(num);
	
	// 글번호에 해당하는 글 한개 가져오기
	BoardVo boardVo = boardDao.getBoardByNum(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 600px;
}
</style>
</head>
<body>
	<h1>글내용 보기</h1>
	<hr>
	<table border="1">
		<tr>
			<th>글번호</th><td><%=boardVo.getNum() %></td>
			<th>조회수</th><td><%=boardVo.getReadcount() %></td>
		</tr>
		<tr>
			<th>작성자</th><td><%=boardVo.getName() %></td>
			<th>작성일</th><td><%=boardVo.getRegDate() %></td>
		</tr>
		<tr>
			<th>글제목</th><td colspan="3"><%=boardVo.getSubject() %></td>
		</tr>
		<tr>
			<th>파일</th>
			<td colspan="3">
				<%
				if (boardVo.getFile() != null) {
					String filename = boardVo.getFile();
					%>
					<a href="../upload/<%=filename %>"><%=filename %></a>
					<%
					if (filename.endsWith("jpg") || filename.endsWith("jpeg") 
							|| filename.endsWith("gif") || filename.endsWith("png")) {
						%>
						<br>
						<img src="../upload/<%=filename %>" width="100" height="100">
						<%
					}
				}
				%>
			</td>
		</tr>
		<tr>
			<th>글내용</th><td colspan="3"><pre><%=boardVo.getContent() %></pre></td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" value="파일글수정" onclick="location.href='fileUpdateForm.jsp?num=<%=num %>&pageNum=<%=pageNum %>'">
				<input type="button" value="글수정" onclick="location.href='updateForm.jsp?num=<%=num %>&pageNum=<%=pageNum %>'">
				<input type="button" value="글삭제" onclick="location.href='deleteForm.jsp?num=<%=num %>&pageNum=<%=pageNum %>'">
				<input type="button" value="답글쓰기" onclick="location.href='reWriteForm.jsp?reRef=<%=boardVo.getReRef() %>&reLev=<%=boardVo.getReLev() %>&reSeq=<%=boardVo.getReSeq() %>&pageNum=<%=pageNum %>'">
				<input type="button" value="글목록" onclick="location.href='list.jsp?pageNum=<%=pageNum %>'">
			</td>
		</tr>
	</table>

</body>
</html>



