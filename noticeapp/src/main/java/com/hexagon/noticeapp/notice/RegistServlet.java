package com.hexagon.noticeapp.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.hexagon.noticeapp.pool.PoolManager;

// 글쓰기 요청을 처리하는 서블릿 클래스
public class RegistServlet extends HttpServlet {
	//앞으로 커넥션풀로부터 Connection을 얻거나 반납하는 코드는 각 클래스나 jsp에서 직접하지 말고, poolManager를 이용하자!!!
	PoolManager pool;
	
	
	
	
	
	// 글쓰기 요청은 POST 방식으로 처리해야 하므로 doXXX 형 메서드 중 doPost를 재정의 하자
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");//MIME 타입
		PrintWriter out=response.getWriter();

		// 파라미터값에 한글, 일본어 등의 영미권 이외의 문자열들은 깨져보일 수 있으므로, 인코딩을 미리 지정하자
		request.setCharacterEncoding("UTF-8");

		// 브라우저로 전송받은 파라미터 값들을 꺼내보자!!
		String title = request.getParameter("title"); // 제목 파라미터꺼내기
		String writer = request.getParameter("writer"); // 작성자 파라미터꺼내기
		String content = request.getParameter("content"); // 내용 파라미터꺼내기

		System.out.println("제목은 " + title);
		System.out.println("작성자는 " + writer);
		System.out.println("내용은 " + content);

		// 넘겨받은 파라미터를 오라클에 넣자!!
		/*
		 * 1.드라이버 로드 2.접속 3.쿼리실행 4.접속 해제
		 */
		
		
		pool=new PoolManager();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con=pool.getConnection();
			//Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			//DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			
			//풀 사용하기 위해서,JNDI

			//con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			if (con == null) {
				System.out.println("뻑유맨");
			} 
			else {
				System.out.println("접속성공");
			}
			//쿼리실행 DML 중 insert 수행
			//String 은 고정된 상수로 취급되므로 그 값을 수정할 수 없다(immuable)
			//따라서 누적 문자열의 경우 String을 그대로 이용하면 성능에 문제가 생긴다..
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notice(notice_id,title,writer,content) values(seq_notice.nextval,?,?,?)");
			
			//jdbc에서 쿼리를 수행하는 객체는 PreparedStatement 이다
			pstmt=con.prepareStatement(sql.toString());//쿼리실행 할 준비
			pstmt.setString(1, title);//첫번째 바인드 변수값은 title 변수값으로 지정
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			//DML의 경우 excuteUpdate()가 사용됨
			int insertdeCount = pstmt.executeUpdate();
			
			if(insertdeCount==1) {
				System.out.println("등록성공");
				//doPost() 메서드가 종료되면,Tomcat이 나서고 개발자가 response 에 작성해놓은 텍스트를 이용하여 HTML 컨탠츠를 만들어
				//클라이언트에게 응답 정보로 보내게 된다.따라서 우리의 경우 성공메시지를 보여주고 ,
				out.print("<script>");
				out.print("alert('등록성공');");
				out.print("location.href='/notice/list.jsp';");//지정한 url인 list.jsp로,웹브라우저가 다시 접속함
				out.print("</script>");
			}else {
				System.out.println("등록실패");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if(pstmt!=null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
