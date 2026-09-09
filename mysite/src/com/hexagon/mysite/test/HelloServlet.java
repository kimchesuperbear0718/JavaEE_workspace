/*오직 웹애플리케이션에서만 실행 및 해석될수 있는 클래스를 가리켜
	서블릿이라 한다(Servlet)
	*/
package com.hexagon.mysite.test;

//javaSE 가 아닌 javaEE패키지에서 가져와야 함...
//javaEE 관련 라이브러리는 우리가 이미 다운로드 받은 tomcat/lib/servlet-api.jar로 지원됨
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
//아래의 일반클래스를 서버에서 실행될 수 있는 서블릿으로 정의하려면 반드시 HttpServlet 클래스를
//상속받아야 함
//javase 와는 달리 별도의 실행을 하지 않는다. 즉 개발자 웹서버에 자신의 소스를 올려놓고 기다리고 있으면 됨
//즉 웹브라우저로 사용자가 방문할때 실행됨..
//주의!! 웹브라우저로 서버에 접근할때 파일의 유형이.html, css , js,image(png.jpg..)는 가능하지만
//.class 는 브라우저로 그 결과를 바꿀수있다
public class  HelloServlet extends HttpServlet{
	//서블릿 클래스는 오직 웹서버에서만 해석 및 생행될 수 있다.
	//클라이언트인 웹브라우저에 응답을 처리한다..
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		//웹브라우저로 서버에 요청을 시도한 클라이언트에게 문자열을 응답 정보로 만들기
		
		PrintWriter out=response.getWriter();//출력스트림 얻기!!
		
		for(int i=1;i<=10;i++){
		out.print("My name is Park");
		}
	}
}

