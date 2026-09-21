<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 이 영역에 코드를 작성하면 Tomcat이 작성하는 서블릿의 service() 메서드의 코드안에 작성되어 진다
    
    // DataSource 의 주소값을 얻어오기 이해서는 server.xml에 명시된 이름을 검색해서 조회해야 한다..
    // 이 때 사용되는 검색 저허ㅣ객체가 바로 InitialContext (JNDI 로 선언된 외부 자원을 검색하는 객체)이다!!!
    InitialContext ctx = new InitialContext();

    // JNDI 는 꼭 커넥션풀만을 위해 사용되는 기술이 아니기 때문에, lookup() 의 반환형이 무엇이 될지 예측할 수 없으므로
    // 개발자가 반환된 Object 형을 적절한 자료형으로 casting 해야 함
    DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle"); // 커넥션풀 객체 선언 java:comp/env는 반드시 붙음
    
    // Pool 로 부터 Connection 하나 꺼내기!!!
    Connection con = ds.getConnection();
    
    // JSP에서는 out 이라는 이름으로 PrintWriter 가 이미 메모리에 준비되어 있다.. 이렇게 jsp 에서 자주쓰는 객체들을 
    // 정해진 이름으로 미리 만들어놓았다고 하여 내장객체(bult-in object)라 한다...
    out.print("접속 객체는 "+con);
    
    //Tomcat에게 빌려온 Connection 을 다시 반납
    con.close();//여기서의 close()를 접속해제가 아닌,풀로 반납하는 효과를 냄..
    //이유는? 우리가 빌려온 Connectoin 은 커넥션풀에 의해 Proxy로 감싸져 있기 때문이다. 즉
%>