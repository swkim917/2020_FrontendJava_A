<%@page import="com.exam.dao.BoardDao"%>
<%@page import="com.exam.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// post 파라미터값 한글처리
request.setCharacterEncoding("utf-8");

//파라미터값 pageNum 가져오기
String pageNum = request.getParameter("pageNum");

// VO 객체 준비
//BoardVo boardVo = new BoardVo();
%>
<jsp:useBean id="boardVo" class="com.exam.vo.BoardVo" />

<%
	// 파라미터값 찾아서 VO에 저장
// boardVo.setNum(Integer.parseInt(request.getParameter("num")));
// boardVo.setName(request.getParameter("name"));
// boardVo.setPasswd(request.getParameter("passwd")); // 글쓴이 본인확인용.
// boardVo.setSubject(request.getParameter("subject"));
// boardVo.setContent(request.getParameter("content"));
%>
<jsp:setProperty property="*" name="boardVo" />

<%
	// DAO 객체 준비
BoardDao boardDao = BoardDao.getInstance();

// 본인이 글작성자인지 글패스워드로 검증하기
boolean isPasswdOk = boardDao.isPasswdOk(boardVo.getNum(), boardVo.getPasswd());

if (isPasswdOk) {
	// 글정보 수정하기
	boardDao.updateBoard(boardVo);
	
	// 수정한 글내용 확인하기 위해 상세보기 페이지인 content.jsp로 이동
	response.sendRedirect("content.jsp?num=" + boardVo.getNum() + "&pageNum=" + pageNum);
} else {
%>
	<script>
		alert('글 비밀번호 틀림');
		history.back();
	</script>
	<%
}
%>





