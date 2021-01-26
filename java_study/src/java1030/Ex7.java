package java1030;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex7 {

	public static void main(String[] args) {
		// 한글 텍스트 파일 복사하기 - InputStreamReader "utf-8"적용
		File src = new File("E:/공유/수업내용", "hobbang.jpg");
		File dest = new File("E:/공유/수업내용", "hobbang - 복사본.jpg");
		
		if (!src.exists()) {
			System.out.println("복사할 파일 " + src.getName() + " 이 존재하지 않습니다.");
			return;
		}
		
		// 입력스트림 준비
		BufferedInputStream bis = null;
		// 출력스트림 준비
		FileOutputStream fos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			fos = new FileOutputStream(dest);
			
			int data;
			while ((data = bis.read()) != -1) { // 파일끝을 만나면 -1 을 리턴함
				fos.write(data);
			}
			
			System.out.println("파일 복사 완료됨!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // main

}
