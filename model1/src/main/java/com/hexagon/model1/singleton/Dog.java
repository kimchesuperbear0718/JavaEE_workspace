package com.hexagon.model1.singleton;

public class Dog {
	private static Dog instance;	
	//싱글턴을 구현하기 위한 첫단계 (외부에서 인스턴스를 직접 생성하는 것을 막자)
	//생성자를 Private 으로 묶어버린다.
	
	private Dog() {
			
		}
	
	public static Dog getInstace() {/
		
		return instance;
	
	}
}
