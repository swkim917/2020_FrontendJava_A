<%@page import="com.exam.dao.MemberDao"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.exam.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- post 파라미터값 한글처리 --%>
<% request.setCharacterEncoding("utf-8"); %>

<%-- VO객체 준비 --%>
<% //MemberVo memberVo = new MemberVo(); %>
<%-- 액션태그 useBean으로 Vo 객체 준비 --%>
<jsp:useBean id="memberVo" class="com.exam.vo.MemberVo" />


<%-- 파라미터 찾기 --%>
<%
// memberVo.setId(request.getParameter("id"));
// memberVo.setPasswd(request.getParameter("passwd"));
// memberVo.setName(request.getParameter("name"));
// memberVo.setAge(Integer.parseInt(request.getParameter("age")));
// memberVo.setGender(request.getParameter("gender"));
// memberVo.setEmail(request.getParameter("email"));
%>
<jsp:setProperty property="*" name="memberVo" />


<%-- 회원가입(날짜시간) 정보 설정 --%>
<% memberVo.setRegDate(new Timestamp(System.currentTimeMillis())); %>

<%
// DAO 객체 준비
MemberDao memberDao = MemberDao.getInstance();

// 회원가입 처리 수행
memberDao.addMember(memberVo);
%>
<script>
	alert('회원가입 성공!');
	location.href = 'loginForm.jsp';
</script>




