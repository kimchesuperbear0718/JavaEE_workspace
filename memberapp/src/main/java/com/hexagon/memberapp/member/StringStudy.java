package com.hexagon.memberapp.member;

public class StringStudy {

	
	
	public static void main(String[] args) {
		/*
		 * java에서 String 객체를 생서앟는 방법은 일반 변수처럼 사용하는 방법도 있지만,일반클래스이기 때문에 new 연산자에 의한 생성방법도 있다.
		 * 1)일반 변수처럼 생성하는 방법은 - 상수풀(Constant Pool)에 생성되므로, 같은 내용의 String 객체가 존재할 경우 중복 생서응ㄹ 하지 않음
		 * 2)new 연산자에 의한 생성 방법은 일반 인스턴스 처럼 생성되므로 new할때 마다 인스턴스가 생성되므로,같은 객체를 가리키지 않는다.
		 * 
		 * 문제제기)문자열의 주소가 아니라,내용을 비교하려면??
		 * 	모든 클래스의 부모인 
		 * */

		String s1="korea";
		String s2="korea";
		
		System.out.println(s1==s2);
		
		String k1=new String("korea");
		String k2=new String("korea");
		System.out.println(k1==k2);
		System.out.println(k1.equals(k2));
		
		/*
		 * String 객체 immutable이다!! = 불변
		 * String 객체는 상수로 취급되므로, 한번 생성된 이후엔 수정 자체가 불가능함
		 * 따라서 아래의 겨웅 상수풀에 총 만들어진 문자리터럴의 수는?
		 * 2개
		 * 1)"우리는"이라는 문자 객체 리터럴
		 * 2)"우리는 개발자다" 문자 객체 리터럴
		 * 따라서,절대로 String을 대상으로 누적 반복문을 돌려서는 안된다!!!
		 * 
		 * 
		 * */
		String str="우리는";
		for(int i=1;i<=20;i++) {
			str=str+"개발자다";
		}
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert int member(c1,c2...)");
		sql.append(" values(?,?)");
		
		System.out.println(sql.toString());
	}

}
