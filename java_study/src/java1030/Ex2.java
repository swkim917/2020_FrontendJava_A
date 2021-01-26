package java1030;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex2 {

	public static void main(String[] args) {
		// InputStreamReader로 한글 텍스트 파일 읽기
		
		InputStreamReader reader = null;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("E:/공유/수업내용/웹프로그래밍_학습.txt");
			reader = new InputStreamReader(fis, "utf-8");
			
			int c;
			
			while ((c = reader.read()) != -1) {
				System.out.print((char) c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close(); // 최종적으로 사용한 입출력 객체만 닫아주면
				                // 그 안에 잡혀있는 입출력 객체도 자동 닫힘
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	} // main

}
