package com.exam.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class MemberStayLoggedInFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext application = fConfig.getServletContext();
		application.setAttribute("test", "데이터");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 요청 사용자의 세션 가져오기
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		// 세션에 로그인 아이디가 없으면 쿠키에서 아이디 찾아서 세션에 저장하고 다음 필터를 호출함
		String id = (String) session.getAttribute("id");
		if (id == null) {
			// 로그인 상태유지 쿠키정보 가져오기
			Cookie[] cookies = req.getCookies();
			// 쿠키 name이 "id"인 쿠키객체 찾기
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("id")) {
						id = cookie.getValue();
						
						// 로그인 인증 처리(세션에 id값 추가)
						session.setAttribute("id", id);
					}
				}
			}
		}
		// 세션에 로그인 아이디가 이미 있으면 바로 다음 필터를 호출함
		chain.doFilter(request, response);
	} // doFilter

	public void destroy() {}
}
