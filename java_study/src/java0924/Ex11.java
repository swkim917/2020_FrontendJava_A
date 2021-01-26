package java0924;

public class Ex11 {
	
	public static void methodA(int a, int b, int c) {
		
	}
	
	public static void methodB(int... num) { // 가변인자 메소드
		// 가변인자 메소드의 매개변수는 배열이 됨
		System.out.println(num.length);
		for (int n : num) {
			System.out.print(n + " ");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		methodA(10, 20, 30);
		
		methodB();
		methodB(100);
		methodB(100, 200, 300, 400, 500);

	}

}
