package java1030;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ex5 {

	public static void main(String[] args) {
		// 텍스트 파일 복사하기
		File src = new File("E:/공유/수업내용", "웹프로그래밍_학습.txt");
		File dest = new File("E:/공유/수업내용", "웹프로그래밍_학습 - 복사본.txt");
		
		if (!src.exists()) {
			System.out.println("복사할 파일 " + src.getName() + " 이 존재하지 않습니다.");
			return;
		}
		
		// 입력스트림 준비
		BufferedReader reader = null;
		// 출력스트림 준비
		FileWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(src));
			writer = new FileWriter(dest);
			
			String line = "";
			while ((line = reader.readLine()) != null) {
				writer.write(line + "\n");
			}
			
			System.out.println("파일 복사 완료됨!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // main

}
