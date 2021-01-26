package java0922;


abstract class Animal /* extends Object */ {
	// 멤버변수(필드)
	int age;
	String gender;
	
	Animal(int age, String gender) {
		this.age = age;
		this.gender = gender;
	}
	
//	Animal puppy = new Puppy(10, "수컷");
	
	// 메소드
	abstract void speak(); // 추상메소드
}

interface Robot {
	
	void guardAndRecord(); // 집지키고 녹화하기 추상메소드
}


class Puppy extends Animal implements Robot {
	private String color;
	
	public Puppy() {
		super(3, "암컷");
		color = "흰색";
	}
	
	public Puppy(int age, String gender) {
		super(age, gender);
		color = "흰색";
	}

	@Override // 메소드 재정의
	void speak() {
		System.out.println(age + "살 " + gender + " 강아지가 멍멍 짖는다");
	}

	@Override
	public void guardAndRecord() {
		System.out.println("집지키고 녹화하기");
	}

	@Override
	public String toString() {
		return age + "살 " + gender + " 강아지";
	}
	
}


class Cat extends Animal {

	Cat() {
		super(2, "수컷");
	}
	
	Cat(int age, String gender) {
		super(age, gender);
	}

	@Override // 메소드 재정의
	void speak() {
		System.out.println(age + "살 " + gender + " 고양이가 야옹 운다");
	}

	@Override
	public String toString() {
		return age + "살 " + gender + " 고양이";
	}
	
}


class Duck extends Animal {
	
	Duck() {
		super(1, "수컷");
	}
	
	Duck(int age, String gender) {
		super(age, gender);
	}

	@Override // 메소드 재정의
	void speak() {
		System.out.println(age + "살 " + gender + " 오리가 꽥꽥 운다");
	}
	
	@Override
	public String toString() {
		return age + "살 " + gender + " 오리";
	}
	
}


class Vet { // 수의사
	
//	void giveShot(Puppy puppy) { // 주사놓기
//		// 강아지한테 주사놓으면 강아지가 짖는다.
//		puppy.speak();
//	}
//	
//	void giveShot(Cat cat) { // 주사놓기
//		// 고양이한테 주사놓으면 고양이가 운다.
//		cat.speak();
//	}
	
	void giveShot(Animal animal) { // 주사놓기
		// 다형성을 적용해서 구현
		animal.speak();
	} // giveShot

	
}



public class Ex1 {

	public static void main(String[] args) {
		
		// Puppy객체를 Puppy참조변수로 저장해서 메소드 호출시에는
		// 정적(컴파일시간) 바인딩만 발생함.
//		Puppy puppy = new Puppy();
//		puppy.speak(); // 멍멍~!
//		
//		// Puppy객체를 Animal참조변수로 저장해서 메소드 호출시에는
//		// 정적바인딩 후 동적(실행시간) 바인딩까지 발생함.
//		Animal animal = new Puppy();
//		// ....
//		//animal = new Cat();
//	
//		animal.speak(); // 멍멍~!
//		
//		System.out.println("//////////////////////");
//		
//		Vet vet = new Vet();
//		Puppy poodle = new Puppy();
//		
//		vet.giveShot(poodle);
//		
//		Cat cat = new Cat();
//		vet.giveShot(cat);
//		
//		Duck duck = new Duck();
//		vet.giveShot(duck);
//		
//		///////
//		
//		Robot puppyRobot = new Puppy();
//		puppyRobot.guardAndRecord();
//		
//		Animal ani = (Animal) puppyRobot;
//		ani.speak();
		Puppy puppy = new Puppy(10, "수컷");
		puppy.speak();
		System.out.println(puppy.toString());
		
	} // main

}
