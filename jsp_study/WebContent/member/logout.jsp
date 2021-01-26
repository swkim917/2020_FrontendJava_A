<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//session.removeAttribute("id");  // 세션의 특정 데이터 삭제
session.invalidate(); // 세션의 모든 데이터 삭제
%>
<script>
	alert('로그아웃 되었습니다.');
	location.href = 'loginForm.jsp';
</script>
