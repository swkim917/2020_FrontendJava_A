<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// Ajax 요청으로 온 파라미터값 가져오기
String name = request.getParameter("name");
String age = request.getParameter("age");
%>
<%=name %> : <%=age %>