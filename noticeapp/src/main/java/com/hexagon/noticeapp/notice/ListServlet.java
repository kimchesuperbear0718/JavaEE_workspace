package com.hexagon.noticeapp.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//목록 요청을 처리하는 서블릿 클래스
public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();

		// 오라클의 notice 테이블의 들어있는 레코드들을 가져오자!!!
		/*
		 * 1.드라이버 로드 2.접속 3.쿼리수행(select) 4.접속해제
		 */
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			String dbUrl = System.getenv("ORACLE_DB_URL");
			String dbUser = System.getenv("ORACLE_DB_USER");
			String dbPassword = System.getenv("ORACLE_DB_PASSWORD");
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			StringBuffer sql = new StringBuffer();
			sql.append("select * from notice order by notice_id asc");

			pstmt = con.prepareStatement(sql.toString());
			// 쿼리실행..DML인 경우 executeUpdate() 였지만,select문의 경우 executeQuery()를 이용해야함..
			rs = pstmt.executeQuery();

			out.print("<table border='1px' width='100%'>");
			// ResultSet은 처음에 아무런 레코드(행)도 가리키지 않는 상태이므로 레코드에 접근하려면 커서를 원하는 위치로 이동시켜가며
			// 사용해야한다..
			out.print("<tr>");
			out.print("<td>notice_id</td>");
			out.print("<td>제목</td>");
			out.print("<td>작성자</td>");
			out.print("<td>등록일</td>");
			out.print("<td>조회수</td>");
			out.print("</tr>");
			while (rs.next()) {

				out.print("<tr>");
				out.print("<td>" + rs.getInt("notice_id") + "</td>");
				out.print("<td>" + rs.getString("title") + "</td>");
				out.print("<td>" + rs.getString("writer") + "</td>");
				out.print("<td>" + rs.getString("created_at") + "</td>");
				out.print("<td>" + rs.getInt("hit") + "</td>");
				out.print("</tr>");

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
