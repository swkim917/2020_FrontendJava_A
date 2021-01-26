package email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SimpleEmailEx {

	public static void main(String[] args) {
		
		// CommonsEmail 라이브러리를 사용해서 이메일 전송기능 구현
		//  -> JavaMail 라이브러리에 의존
		//  -> JAF(Java Activation Framework) 라이브러리에 의존
		
		// SimpleEmail 클래스 : 텍스트 메시지만 전송하는 용도
		// MultiPartEmail 클래스 : 텍스트 메시지와 파일을 함께 전송
		// HtmlEmail 클래스 : 이메일을 HTML 형식으로 전송하는 용도
		
		// 이메일 서비스는 메일 전송용 프로토콜인 SMTP를 사용함.
		// SMTP 서버 호스트명(IP주소)
		// SMTP 서버 포트: 기본포트 465(SSL) 또는 587(TLS)
		
		
		long beginTime = System.currentTimeMillis();
		
		// SimpleEmail 객체 준비
		SimpleEmail email = new SimpleEmail();
		
		// SMTP 서버 연결설정
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthentication("zencoding", "******");
		
		// 보안연결 SSL, TLS 사용 설정
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		
		String response = "fail";
		
		try {
			// 보내는 사람 설정 (SMTP 서비스 로그인 계정 아이디와 동일하게 해야함에 주의!)
			email.setFrom("zencoding@naver.com", "관리자", "utf-8");
			
			// 받는 사람 설정
			email.addTo("swkim917@daum.net", "김상우", "utf-8");
			email.addTo("swkim917@naver.com");
			
			// 받는 사람(참조인) 설정
			email.addCc("swkim917@gmail.com");
			
			// 받는 사람(숨은 참조인) 설정
			email.addBcc("swkim917@nate.com");
			
			// 제목 설정
			email.setSubject("메일 제목입니다.");
			
			// 본문 설정
			email.setMsg("메일 본문입니다.\n두번째 줄입니다.\n\n세번째 줄입니다.");
			
			// 메일 전송하기
			response = email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		long execTime = endTime - beginTime;
		
		System.out.println("실행시간: " + execTime + "ms");
		System.out.println("응답메시지: " + response);
	}// main

}
