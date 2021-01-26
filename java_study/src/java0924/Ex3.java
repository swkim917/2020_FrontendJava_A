package java0924;


class GoTask implements Runnable {
	
	@Override
	public void run() { // 스레드의 시작메소드
		Thread goTaskThread = Thread.currentThread(); // thread1과 동일한 객체임
		System.out.println(goTaskThread.getName());
		//go();
	}

	void go() {
		while (true) {
			System.out.println("go");
		}
	}
}

class ComeTask implements Runnable {
	
	@Override
	public void run() {
		//come();
	}

	void come() {
		while (true) {
			System.out.println("come");
		}
	}
}

public class Ex3 {

	public static void main(String[] args) {
		// 멀티스레드 생성방법
		// 2. java.lang.Runnable 인터페이스 구현한 작업클래스 정의.
		//    run() 메소드 오버라이딩
		//    스레드객체 생성 후 Runnable 구현객체를 전달하고
		//    start() 호출해서 스레드 시작시킴
		
		// 스레드객체(일꾼)을 준비하고 할일(Runnable구현객체)를 전달함
		GoTask goTask = new GoTask(); // 할일
		Thread thread1 = new Thread(goTask); // 일꾼에게 할일을 전달함
		thread1.setName("Go스레드");
		
		Thread thread2 = new Thread(new ComeTask());
		
		thread1.start();
		thread2.start();
		
	} // main
	
	
	

}
