package com.hexagon.model1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexagon.model1.pool.PoolManager;


//글 삭제 요청을 처리하는 서브릿, 글 삭제란 데이터베이스로부터 레코드를 삭제하는 것이므로, 디자인이 관여되지 않는다.
//따라서 굳이 jsp를 사용할 필요가 없다!!
public class DeleteServlet extends HttpServlet {
	PoolManager pool = PoolManager.getInstance();
//delete board where board_id=내가본글pk
	// 파라미터가 보안상 중요하지도 않고 즉 url에 노출되어도 솽관이 없고, 그 데ㅣ엍량도 크기 않으므로 get 방식으로도 처리할 수
	// 있다..(딩연히 post도 가능)

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("board_id"));

		String sql = "delete board where board_id=" + board_id;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(sql);
		/*원래 JDBC 연동 업무는 아래의 4단계로 진행을 해야 하지만, 우리의 경우 1,2단계는 Tomcat이 커넥션풀링을 이용하여 대신해 준다..
		 * 현재 우리 애플리케이션은 톰켓이 작업해놓은 Connection Pool을,JNDI를 이용하여 검색한후 DataSourse를 통해 톰켓이 마련해놓은 커넥션 풀에
		 * 1.드라이버 로드
		 * 2.접속*/
		
		
		Connection con = pool.getConnection();
		PreparedStatement pstmt=null;
				
		
		try {
			pstmt = con.prepareStatement(sql);
			int rowCount = pstmt.executeUpdate();
			
			
			out.print("<script>");
			if(rowCount==0) {
				out.print("alert('삭제 실패');");
				out.print("history.back();");
			}else {
				out.print("alert('삭제 성공');");
				out.print("location.href='/board/list.jsp';");
			}
			out.print("</script>");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con,pstmt);//자원 해제(Connection 은 풀로 돌아감)
		}
		
		
		
	}

}
