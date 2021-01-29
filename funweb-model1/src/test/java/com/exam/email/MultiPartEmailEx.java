package com.exam.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MultiPartEmailEx {

	public static void main(String[] args) {

		long beginTime = System.currentTimeMillis();

		// MultiPartEmail ��ü �غ�
		MultiPartEmail email = new MultiPartEmail();
		
		// SMTP ���� ���ἳ��
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthentication("zencoding", "******");
		
		// ���ȿ��� SSL, TLS ��� ����
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		
		String response = "fail";
		
		
		// ÷�������� ���� EmailAttachment ��ü�� ÷������ ������ŭ �غ�
		EmailAttachment attach = new EmailAttachment();
		attach.setPath("D:/����/images/hobbang.jpg");
		attach.setDescription("ȣ���� �̹���"); // ÷������ ����
//		attach.setName("ȣ����.jpg"); // ���ϸ��� �ٸ��̸����� �����ϱ�
		
		EmailAttachment attach2 = new EmailAttachment();
		attach2.setPath("D:/����/images/mario.jpg");
		
		try {
			// ������ ��� ���� (SMTP ���� �α��� ���� ���̵�� �����ϰ� �ؾ��Կ� ����!)
			email.setFrom("zencoding@naver.com", "������", "utf-8");
			
			// �޴� ��� ����
			email.addTo("swkim917@daum.net", "����", "utf-8");
			email.addTo("swkim917@naver.com");
			
			// �޴� ���(������) ����
			email.addCc("swkim917@gmail.com");
			
			// �޴� ���(���� ������) ����
			email.addBcc("swkim917@nate.com");
			
			// ���� ����
			email.setSubject("÷������ ���� �����Դϴ�.");
			
			// ���� ����
			email.setMsg("÷������ ���� �����Դϴ�.\n�ι�° ���Դϴ�.\n\n����° ���Դϴ�.");
			
			// ÷������ �߰��ϱ�
			email.attach(attach);
			email.attach(attach2);
			
			// ���� �����ϱ�
			response = email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		long execTime = endTime - beginTime;
		
		System.out.println("����ð�: " + execTime + "ms");
		System.out.println("����޽���: " + response);
	} // main

}
