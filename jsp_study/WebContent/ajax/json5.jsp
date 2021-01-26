<%@page import="com.google.gson.Gson"%>
<%@page import="com.exam.vo.MemberVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.MemberDao"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// POST 전송 파라미터값 한글처리
request.setCharacterEncoding("utf-8");

// 파라미터값  id 가져오기
String id = request.getParameter("id");

//DAO 객체 준비
MemberDao memberDao = MemberDao.getInstance();

//전체 회원정보 가져오기
MemberVo memberVo = memberDao.getMemberById(id);

// Gson 객체 준비
Gson gson = new Gson();

// memberList 객체를 JSON 문자열로 변환하기
String strJson = gson.toJson(memberVo); // JSONObject 형식 문자열로 변환됨

// 웹서버 콘솔에 출력
System.out.println(strJson);

// 웹브라우저로 출력
//out.println(strJson);
%>
<%-- 웹브라우저로 출력 --%>
<%=strJson %>



