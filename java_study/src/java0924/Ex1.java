package java0924;


class Go {
	
	void go() {
		while (true) {
			System.out.println("go");
		}
	}
}

class Come {
	
	void come() {
		while (true) {
			System.out.println("come");
		}
	}
}

public class Ex1 {

	public static void main(String[] args) {
		// 태스크 : 작업
		// 멀티태스킹 : 한번에 여러개의 작업을 수행하는것.
		
		// 프로세스 : 실행중인 프로그램 한개
		// 스레드 : 프로그램 한개 안에서 실행흐름 한개 단위.
		
		Go g = new Go();
		Come c = new Come();
		
		g.go();
		c.come();
		
	} // main

}
