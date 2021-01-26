package java0922;

class Parent {
	private int num;
	
//	Parent() {
//	}
	
	Parent(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

class Child extends Parent {
	String str;
	
	Child() {
		// 컴파일러는 생성자 블록 안에서
		// 무조건 첫라인에 부모클래스의 기본생성자 호출문을 삽입함.
		super(10);
		
		str = "문자열";
		
		
//		setNum(10);
	}
}


class A {
	A() {
		super();
		System.out.println("A");
	}
}
class B extends A {
	B() {
		super();
		System.out.println("B");
	}
}
class C extends B {
	C() {
		super();
		System.out.println("C");
	}
}



public class Ex5 {

	public static void main(String[] args) {
		new C();
		
		//Child child = new Child();
	}

}
