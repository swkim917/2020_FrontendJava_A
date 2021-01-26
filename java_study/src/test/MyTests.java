package test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import lombok.extern.java.Log;

// JUnit : 자바의 단위테스트 도구 (테스트 프레임워크)
//   이클립스 등의 IDE는 JUnit과 통합되어 있음.
//   JUnit의 테스트 메소드는 리턴형은 void, 매개변수는 없어야 함.
//   애노테이션 @Test가 메소드에 선언되어야 함.

// 테스트케이스 클래스: 테스트를 수행하는 클래스
@Log
@DisplayName("테스트케이스 클래스")
public class MyTests {
	
	private Person person;
	
	@BeforeAll // @BeforeAll는 static 메소드에만 붙일수 있음
	static void setUpBeforeAll() {
		log.info("@BeforeAll - static setUpBeforeAll() 호출됨");
	}
	
	@BeforeEach
	void setUp() {
		log.info("@BeforeEach - setUp() 호출됨");
		
		person = new Person("홍길동", 27, false);
	}
	
	@Test
	@DisplayName("Person 객체 테스트")
	void testPerson() {
		log.info("@Test - testPerson() 호출됨");
		assertFalse(person.isMarried());
	}
	
	//@Disabled
	@Test  // 테스트케이스 메소드
	void testHelloWorld() {
		log.info("@Test - testHelloWorld() 호출됨");
		// 객체일 경우 내용 비교
		assertEquals("Hello world!", Hello.helloWorld());
	}
	
	@Test
	void testGetNumArr() {
		log.info("@Test - testGetNumArr() 호출됨");
		int[] arr = { 10, 20, 30 };
		
		assertArrayEquals(arr, Hello.getNumArr()); // 배열객체 내용 비교
		assertNotSame(arr, Hello.getNumArr()); // 객체 주소 비교
	}
	
	@Test
	void testReceivePositiveNumber() {
		
		// 예외객체의 타입 비교
		Throwable exception = assertThrows(Exception.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				Hello.receivePositiveNumber(-1);
			}
		});
		
		// 예외객체의 메시지 비교
		assertEquals("음수는 받을 수 없습니다..", exception.getMessage());
	}
	
	@Test
	void testCalc() {
		log.info("@Test - testCalc() 호출됨");
		assertEquals(3, Calc.add(1, 2));
		assertEquals(5, Calc.sub(7, 2));
	}
	
	@AfterEach
	void tearDown() {
		log.info("@AfterEach - tearDown() 호출됨");
	}

	@AfterAll  // @AfterAll는 static 메소드에만 붙일수 있음
	static void tearDownAfterAll() {
		log.info("@AfterAll - static tearDownAfterAll() 호출됨");
	}
}




