package java0923;

public class Ex1 {

	public static void main(String[] args) {
		// 오류의 종류: 에러(Error), 예외(Exception)
		
		// 에러 : 컴퓨터 하드웨어 고장 등으로 인한 JVM 오류.
		//        복구 불가능한 심각한 오류.
		
		// 예외 : 프로그램 사용자의 잘못된 조작 또는
		//        잘못된 코딩으로 인해 발생하는 프로그램 오류.
		//        프로그램 실행중 발생할수 있는 예기치못한 오류를 말함.
		
		System.out.println("=== 프로그램 시작 ===");
		
		// 예외처리
		int a = 10, b = 0, c = 0;
		
		if (b == 0) {
			System.out.println("0으로 나눌수 없습니다.");
		} else { // b != 0
			c = a / b;
		}
		
		System.out.println("c = " + c);
		
		System.out.println("=== 프로그램 종료 ===");
	} // main

}
