package com.hexagon.basicapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{

	
//이 서블릿이 클리이언트의 요청을 처리할때 사용하는 메서드를 재정의하자!!
// 이 메서드의 매개변수인 HttpServlet
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이 쵸청에 대해 어떤 처리를 할지는 개발자가 로직을 작성하면 됨..
		//요청에 대한 응답 정보는 response 객체를 통해 문자열로 만들어 낼 수 있다..
		
		
		//개발자는 클리이언트인 브라우저가 보게될 화면의 컨텐츠 내용을 문자 기반 출력 스트림을 이용하여 준비만 해놓으면 됨
		PrintWriter out = response.getWriter();
		out.print("this is my result!!");
		
		
	}//쓰레드가 이 메서드를 싱행하다가 이 닫는 브레이스를 만나면,자신의 요청 처리 여갈이 끝나게 됨
	//이때 부터는 Tomcat이 respons 객체에 들어있는 응답 정보들을 이용하여 html 컨텐츠를 생성해 냄
	//그리고 이 html 컨탠츠를
}
