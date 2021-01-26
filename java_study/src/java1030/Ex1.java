package java1030;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ex1 {

	public static void main(String[] args) {
		// 자바 IO(입출력) 데이터 종류
		
		// byte 단위 처리 입출력 : 이미지파일 등의 바이너리 위주의 데이터 입출력
		//    InputStream 인터페이스 구현한 클래스
		//      - FileInputStream, BufferedInputStream
		//    OutputStream 인터페이스 구현한 클래스
		//      - FileOutputStream, BufferedOutputStream
		
		// char 단위 처리 입출력 : 입출력을 char처리, 텍스트 위중의 데이터 입출력
		//    Reader 인터페이스 구현한 클래스
		//      - FileReader, InputStreamReader, BufferedReader
		//    Writer 인터페이스 구현한 클래스
		//      - FileWriter, OutputStreamWriter, BufferedWriter

		
		// FileReader로 텍스트 파일 읽어서 콘솔화면에 출력하기
		
		// 입력스트림 준비
		FileReader reader = null;
		
		try {
			reader = new FileReader("C:/Windows/system.ini");
			
			int c;
			while ((c = reader.read()) != -1) { // 파일의 끝(-1)이 아닐때까지 문자 한개씩 읽음
				System.out.print((char) c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	} // main

}
