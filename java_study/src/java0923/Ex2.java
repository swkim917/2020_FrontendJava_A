package java0923;

public class Ex2 {

	public static void main(String[] args) {
		System.out.println("=== 프로그램 시작 ===");
		
		// 예외처리
		int a = 10, b = 0, c = 0;
		
		int[] arrNum = {1, 2, 3};
		
		String str = null;
		
		try {
			// 문장들....
			//c = a / b;
			
			System.out.println(str.length());
			// 문장들....
			
			arrNum[5] = 50;
			
			// 문장들....
		} catch (ArithmeticException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("c = " + c);
		
		System.out.println("=== 프로그램 종료 ===");
	} // main

}
 