package com.hexagon.model1.singleton;

public class UseDog {

	public static void main(String[] args) {
		
		Dog d1 = Dog.getInstace();
		Dog d2 = Dog.getInstace();
		Dog d3 = Dog.getInstace();
		Dog d4 = Dog.getInstace();
		Dog d5 = Dog.getInstace();
		Dog d6 = Dog.getInstace();
		
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		System.out.println(d4);
		System.out.println(d5);
		System.out.println(d6);
	}

}
