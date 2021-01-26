package java0113;


// 요리사
interface Chef {
	void cook();
}

class ChineseChef implements Chef {
	@Override
	public void cook() {
		System.out.println("중식 요리하기");
	}
}

class JapaneseChef implements Chef  {
	@Override
	public void cook() {
		System.out.println("일식 요리하기");
	}
	
}

class KoreanChef implements Chef  {
	@Override
	public void cook() {
		System.out.println("한식 요리하기");
	}
	
}

// 식당
class Restaurant {
	
	private Chef chef;
	
	// 포함관계로 필요한 객체를 외부에서 전달받는 방법 2가지 : 생성자, setter	
	
	public Restaurant(Chef chef) {
		this.chef = chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
		
		// instanceof 연산자: 실제 객체의 타입이 
		// 오른쪽에 명시한 타입과 일치하면 true, 아니면 false
		if (chef instanceof JapaneseChef) {
			
		}
		
	}
	
	// 요리사 객체를 이용해서 식당 메소드 구현...
}



public class Ex2 {

	public static void main(String[] args) {
		// 객체 조립기의 역할 : 이 역할을 스프링이 대신 해줌.
		// 스프링 DI(Dependency Injection) 의존관계 주입.
		
		JapaneseChef japaneseChef = new JapaneseChef();
		
		Restaurant rest = new Restaurant(japaneseChef);
		
		KoreanChef koreanChef = new KoreanChef();
		
		rest.setChef(koreanChef);
	}

}
