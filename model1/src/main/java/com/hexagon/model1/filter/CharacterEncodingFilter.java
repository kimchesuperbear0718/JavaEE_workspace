package com.hexagon.model1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	String encoding;
	
	//필터도 Servlet과 마찬가지로 생성된 직후 반드시  init() 이  호출됨. 
	//따라서 개발자가 init() 에서 무언가 하고 싶은 작업이 있다면 init() 오버라이딩 하면 된다...
	//태어나자마자 Tomcat 에 의해 호출됨...
	public void init(FilterConfig filterConfig) throws ServletException {
		// xml에서 <init-param> 에 명시된 변수명을 통해 데이터 가져옴..
		encoding = filterConfig.getInitParameter("encoding");
		System.out.println("init-param에서 읽어들인 인코딩은 "+encoding);
	}
	
	//이 메서드는 서블릿의 service() 메서드가 동작하기도 전에 동작하는 필터의 메서드이다.
	//따라서 모든 요청은 반드시 이 필터를 거쳐서 가므로, request  객체에 한글 인코딩 처리를 해놓자
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		System.out.println("필터가 먼저 동작함");
		
		// 필터가 이 요청을 막아세웠으므로, 원하는 처리가 끝나면 이 요청이 다시 가던길을 가게하자
		chain.doFilter(request, response);
	}
}