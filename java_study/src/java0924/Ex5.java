package java0924;


class SharedData {
	
	private int num;
	
	// synchronized : 스레드 동기화. 객체단위로 동기화.
	//  한 스레드가 synchronized 메소드를 가진 객체의 메소드를
	//  호출하면 그즉식 이 객체에 Lock(잠금)이 걸림.
	//  메소드 호출완료후 Lock이 풀릴때까지 다른스레드는
	//  대기하게 됨.
	synchronized public void up() {
		String threadName = Thread.currentThread().getName();
		System.out.print(threadName + "가 " + num + "을 ");
		num++;
		System.out.println(num + "로 증가시킴");
	}
	
	synchronized public void down() {
		String threadName = Thread.currentThread().getName();
		System.out.print(threadName + "가 " + num + "을 ");
		num--;
		System.out.println(num + "로 감소시킴");
	}
} // class SharedData


class UpThread extends Thread {

	private SharedData sharedData;
	
	public UpThread(SharedData sharedData) {
		super();
		this.sharedData = sharedData;
	}

	@Override
	public void run() {
		// sharedData의 up() 호출
		for (int i=0; i<10; i++) {
			try {
				Thread.sleep(500); // 1초 = 1000밀리초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sharedData.up();
		} // for
	} // run()
} // class UpThread


class DownThread extends Thread {

	private SharedData sharedData;
	
	public DownThread(SharedData sharedData) {
		super();
		this.sharedData = sharedData;
	}

	@Override
	public void run() {
		// sharedData의 down() 호출
		for (int i=0; i<10; i++) {
			try {
				Thread.sleep(500); // 1초 = 1000밀리초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sharedData.down();
		} // for
	} // run()
} // class DownThread


public class Ex5 {

	public static void main(String[] args) {
		// 객체 주입방법 2가지
		//  1. 생성자를 통한 주입
		//  2. 메소드를 통한 주입
		
		// upThread와 downThread 객체는 둘다
		// 동일한 sharedData객체를 공유함(sharedData객체의 필드값을 공유함)
		
		SharedData sharedData = new SharedData();
		
		UpThread upThread = new UpThread(sharedData);
		upThread.setName("Up스레드");
		
		DownThread downThread = new DownThread(sharedData);
		downThread.setName("down스레드");
		
		upThread.start(); // JVM에 의해서 run()이 호출됨
		downThread.start();
	} // main

}
