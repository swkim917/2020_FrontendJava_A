package java0924;

public class Ex4 {

	public static void main(String[] args) {
		
		Thread mainThread = Thread.currentThread();
		
		System.out.println(mainThread.getName()); // main
		System.out.println(mainThread.getPriority()); // 5(±âº»°ª)
		
		System.out.println(Thread.MAX_PRIORITY); // 10
		System.out.println(Thread.NORM_PRIORITY); // 5
		System.out.println(Thread.MIN_PRIORITY);  // 1
		
		mainThread.setPriority(7);
		

	} // main

}
