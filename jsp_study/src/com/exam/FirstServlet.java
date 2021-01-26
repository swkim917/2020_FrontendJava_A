package com.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답문서는 html이면서 유니코드로 설정
		response.setContentType("text/html; charset=UTF-8");
		// 브라우저로 출력하는 객체 참조 받기
		PrintWriter out = response.getWriter();
		
		// 세션 가져오기. 세션은 Map 컬렉션으로 구현됨.
		// 사용자 1명당 유지하는 정보 관리.
		HttpSession session = request.getSession();
		
		// 스크립틀릿 영역 (자바 코드 영역)
		/*
		   자바 여러줄 주석
		*/
		String str = "문자열";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		String strDate = sdf.format(date);
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset=\"UTF-8\">");
		out.println("<title>Insert title here</title>");
		out.println("</head><body><h1>JSP 테스트</h1><hr>");
		
		out.println(strDate);
		
		out.println(1+2);
		
		out.println("<br></body></html>");
	}

}



