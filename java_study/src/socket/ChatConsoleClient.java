package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatConsoleClient {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		Scanner scanner = new Scanner(System.in); // 키보드에서 읽을 scanner 객체 생성
		
		try {
			socket = new Socket("localhost", 9999); // 클라이언트 소켓 생성. 서버에 연결
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			while (true) {
				System.out.print("보내기>> ");  // 프롬프트
				String outputMessage = scanner.nextLine(); // 키보드에서 한 행 읽기
				if (outputMessage.equalsIgnoreCase("bye")) {
					writer.write(outputMessage + "\n"); // "bye" 문자열 전송
					writer.flush();
					break;  // 사용자가 "bye"를 입력한 경우 서버로 전송 후 실행 종료
				}
				writer.write(outputMessage + "\n"); // 키보드에서 읽은 문자열 전송
				writer.flush();  // writer의 스트림 버퍼에 있는 모든 문자열 전송
				
				String inputMessage = reader.readLine(); // 서버로부터 한 행 수신
				System.out.println("서버: " + inputMessage);
			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();  // scanner 닫기
				socket.close();   // 클라이언트 소켓 닫기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	} // main

}
