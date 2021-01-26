package email;

import java.io.File;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class HtmlEmailEx {

	public static void main(String[] args) {
		
		long beginTime = System.currentTimeMillis();
		
		// HtmlEmail 객체 준비
		HtmlEmail email = new HtmlEmail();
		
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
			
			// 내용에 표시할 이미지 파일을 File 객체로 준비
			File imgFile = new File("D:/공유/images/hobbang.jpg");
			String strEmbed = email.embed(imgFile);
			System.out.println("strEmbed : " + strEmbed);
			
			// 본문 설정
//			email.setMsg("메일 본문입니다.\n두번째 줄입니다.\n\n세번째 줄입니다.");
			String str = "";
			str += "<html>";
			str += "<head>";
			str += "<meta charset='UTF-8'>";
			str += "<style>";
			str += "body { color: purple; }";
			str += "</style>";
			str += "</head>";
			str += "<body>";
			str += "메일 본문입니다.<br>";
			str += "<img src=cid:" + strEmbed + "><br>";
			str += "두번째 줄입니다.<br><br>";
			str += "세번째 줄입니다.";
			str += "</body>";
			str += "</html>";
			
			email.setHtmlMsg(str);
			email.setCharset("utf-8");
			
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
