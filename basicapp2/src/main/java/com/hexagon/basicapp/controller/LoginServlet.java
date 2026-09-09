package com.hexagon.basicapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	//개발자가 이 서블릿 클래스를 서버에 배포한 후, 최초의 브라우저의 요청에 의해 서블릿의 인스턴스가 Tomcat에 의해 만들어 지는데,
	//인스턴스 생성 직후,이 서블릿의 init() 초기화 메서드가 무조건 호출된다..
	//생성자 아님!! 즉 생성후에 초기화를 위해 호출되므로 생성자 보다 호출시점도 늦다..

	public void init() throws ServletException {
		System.out.println("저 아까 태어나서 지금 울어요 ㅠㅠ");
		
	}
	//이 메서드는 서블릿 클래스가 보유한 doXXXX형 중, 클라이언트의 요청 method가 post인 경우 동작하는 메서드이다..
	// 즉 메서드가
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트의 웹 브라우저ㅓ에서 전송된 id,password 알아맞춰보자!!
		//클라이언트의 요청 정보는 당연히 요청 객체에 들어있으므로, 꺼내보자!!
		
		String v1=request.getParameter("id");
		String v2=request.getParameter("password");
		
		System.out.println("전송된 아이디는"+v1);
		System.out.println("전송된 패스워드는"+v2);
		
		PrintWriter out=response.getWriter();
		String tag="<table>";
		tag+="<tr>";
		tag+="<tr>ID</td>";
		tag+="<tr>"+v1+"</td>";
		tag+="</tr>";
		
		tag+="<tr>";
		tag+="<td>Password</td>";
		tag+="<tr>"+v2+"</td>";
		tag+="</tr>";
		tag+="</table>";
		
		
		
		
		
		
		
	}
}
