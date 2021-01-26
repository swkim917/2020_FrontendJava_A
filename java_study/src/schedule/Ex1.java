package schedule;

import java.util.Timer;
import java.util.TimerTask;


class MyTask extends TimerTask {
	@Override
	public void run() {
		System.out.println("스케줄 작업 수행.....");
	}
}

class MyTask2 extends TimerTask {
	@Override
	public void run() {
		System.out.println("스케줄 작업2 수행.....");
	}
}

public class Ex1 {

	public static void main(String[] args) {
		// Timer 클래스 : 일정 시간단위로 반복되는 작업을 스레드로 처리함
		
		// 데몬(보조)스레드는 메인(주) 스레드가 종료되면
		// 자신도 같이 종료됨.
		
		Timer timer = new Timer(true); // 데몬스레드
		
		MyTask task = new MyTask(); // 타이머 작업객체 준비
		MyTask2 task2 = new MyTask2();
		
		// 3초 지연 후 1초마다 반복 실행
		timer.scheduleAtFixedRate(task, 3000, 1000);
		// 0.5초 지연 후 3초마다 반복 실행
		timer.scheduleAtFixedRate(task2, 500, 3000);
		
		// 메인(주)스레드를 10초 대기
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("=== 메인(주)스레드를 10초 대기 후 종료됨 ===");
		
		
//		task.cancel();  // 특정 작업 한개만 작업 취소함.
//		timer.cancel(); // 타이머에 등록된 모든 작업을 취소함.
		
	} // main

}




