package java1030;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex4 {
	
	public static void listDirectory(File dir) {
		
		System.out.println("\n---" + dir.getPath() + "의 서브 리스트입니다. ---\n");
		
		File[] files = dir.listFiles();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (File file : files) {
			System.out.print(file.getName());
			System.out.print(file.isDirectory() ? "\t<DIR>" : "\t");
			System.out.print("\t파일크기: " + file.length());
			
			long milis = file.lastModified();
			Date date = new Date(milis);
			String str = sdf.format(date);
			System.out.println("\t수정한 시간: " + str);
		} // for
	} // listDirectory

	public static void main(String[] args) {
		// File 클래스
		File file1 = new File("E:/공유/수업내용/웹프로그래밍_학습.txt");
		System.out.println(file1.getPath());
		System.out.println(file1.getParent());
		System.out.println(file1.getName());
		
		if (file1.isFile()) {
			System.out.println("파일입니다.");
		} else if (file1.isDirectory()) {
			System.out.println("디렉토리입니다.");
		}
		
		File file2 = new File("E:/공유/수업내용/sample"); // 새로 만들고자 하는 디렉토리
		if (!file2.exists()) { // 디렉토리 존재 검사
			file2.mkdir(); // 디렉토리가 존재하지 않으면 생성
		}
		
		
		listDirectory(new File("E:\\공유\\수업내용"));

	} // main

}
