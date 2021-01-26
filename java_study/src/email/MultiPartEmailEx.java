package email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MultiPartEmailEx {

	public static void main(String[] args) {

		long beginTime = System.currentTimeMillis();

		// MultiPartEmail 객체 준비
		MultiPartEmail email = new MultiPartEmail();
		
		// SMTP 서버 연결설정
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthentication("zencoding", "******");
		
		// 보안연결 SSL, TLS 사용 설정
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		
		String response = "fail";
		
		
		// 첨부파일을 위한 EmailAttachment 객체를 첨부파일 갯수만큼 준비
		EmailAttachment attach = new EmailAttachment();
		attach.setPath("D:/공유/images/hobbang.jpg");
		attach.setDescription("호빵맨 이미지"); // 첨부파일 설명
//		attach.setName("호빵맨.jpg"); // 파일명을 다른이름으로 변경하기
		
		EmailAttachment attach2 = new EmailAttachment();
		attach2.setPath("D:/공유/images/mario.jpg");
		
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
			email.setSubject("첨부파일 메일 제목입니다.");
			
			// 본문 설정
			email.setMsg("첨부파일 메일 본문입니다.\n두번째 줄입니다.\n\n세번째 줄입니다.");
			
			// 첨부파일 추가하기
			email.attach(attach);
			email.attach(attach2);
			
			// 메일 전송하기
			response = email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		long execTime = endTime - beginTime;
		
		System.out.println("실행시간: " + execTime + "ms");
		System.out.println("응답메시지: " + response);
	} // main

}
