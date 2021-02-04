package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatConsoleServer {
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		Scanner scanner = new Scanner(System.in); // 키보드에서 읽을 scanner 객체 생성
		
		try {
			serverSocket = new ServerSocket(9999); // 서버 소켓 생성
			System.out.println("연결을 기다리고 있습니다.....");
			
			socket = serverSocket.accept(); // 클라이언트로부터 연결 요청 대기
			System.out.println("연결되었습니다.");
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			
			while (true) {
				String inputMessage = reader.readLine(); // 클라이언트로부터 한 행 읽기
				System.out.println("클라이언트: " + inputMessage);
				if (inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트에서 bye로 연결을 종료하였음");
					break;
				}
				System.out.print("보내기>> "); // 프롬프트
				String outputMessage = scanner.nextLine(); // 키보드에서 한 행 읽기
				writer.write(outputMessage + "\n"); // 키보드에서 읽은 문자열 전송
				writer.flush();  // writer의 스트림 버퍼에 있는 모든 문자열 전송
			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();  // scanner 닫기
				socket.close();   // 통신용 소켓 닫기
				serverSocket.close();   // 서버 소켓 닫기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // main
}





