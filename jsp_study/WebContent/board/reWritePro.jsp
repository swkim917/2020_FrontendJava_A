<%@page import="java.sql.Timestamp"%>
<%@page import="com.exam.dao.BoardDao"%>
<%@page import="com.exam.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// post 파라미터값 한글처리
request.setCharacterEncoding("utf-8");

// 파라미터값 pageNum 가져오기
String pageNum = request.getParameter("pageNum");

// VO 객체 준비
//BoardVo boardVo = new BoardVo();
%>
<jsp:useBean id="boardVo" class="com.exam.vo.BoardVo" />

<%
	// 파라미터값 가져와서 VO에 저장
// boardVo.setName(request.getParameter("name"));
// boardVo.setPasswd(request.getParameter("passwd"));
// boardVo.setSubject(request.getParameter("subject"));
// boardVo.setContent(request.getParameter("content"));

// 답글을 다는 대상글에 대한 정보인 re로 시작하는 3개의 파라미터값 가져와서 VO에 저장
// re로 시작하는 이 정보들은 같은 VO객체에 있지만 답글 자체의 정보가 아님에 주의!!
// boardVo.setReRef(Integer.parseInt(request.getParameter("reRef")));
// boardVo.setReLev(Integer.parseInt(request.getParameter("reLev")));
// boardVo.setReSeq(Integer.parseInt(request.getParameter("reSeq")));
%>
<jsp:setProperty property="*" name="boardVo"/>

<%
	// DAO 객체 준비
BoardDao boardDao = BoardDao.getInstance();

// 글번호 가져와서 VO에 저장
int nextNum = boardDao.getNextNum();
boardVo.setNum(nextNum);

// ip  regDate  readcount  값 저장
boardVo.setIp(request.getRemoteAddr());
boardVo.setRegDate(new Timestamp(System.currentTimeMillis()));
boardVo.setReadcount(0);  // 조회수

// 답글 등록하기
boardDao.updateAndAddReply(boardVo);

// 글내용 상세보기 화면 content.jsp로 이동
response.sendRedirect("content.jsp?num=" + boardVo.getNum() + "&pageNum=" + pageNum);
%>




