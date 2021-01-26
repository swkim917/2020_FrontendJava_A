package java0922;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ex4 {

	public static void main(String[] args) {
		// Map 컬렉션: 키와 값의 쌍으로 데이터 관리하는 자료구조
		
		Map<String, Animal> animalMap = new HashMap<>();
		
		// 키와 쌍으로 값 추가
		animalMap.put("푸들", new Puppy());
		animalMap.put("고양이", new Cat());
		animalMap.put("오리", new Duck());
		
		// 키를 통해서 값 가져오기
		Animal ani = animalMap.get("푸들");
		ani.speak();
		
		// 키값이 같으면 데이터 추가가 아니고 수정작업이 됨
		animalMap.put("푸들", new Puppy(8, "수컷"));
		ani = animalMap.get("푸들");
		ani.speak();
		
		// 맵 컬렉션의 모든 데이터를 가져오기
		Set<String> keySet = animalMap.keySet();
		
		for (String key : keySet) {
			Animal animal =  animalMap.get(key);
			System.out.println(animal.toString());
		}
		
		// 특정 데이터 삭제
		animalMap.remove("푸들");
		
		// 모든 데이터 삭제
		animalMap.clear();

	} // main

}
