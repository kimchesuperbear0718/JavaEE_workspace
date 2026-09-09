package com.hexagon.memberapp.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistOracle extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		/*데이터베이스 연동 장치
		 * 1)드라이버 로드 oracle driver
		 * 2)접속
		 * 3)퀴리수행
		 * 4)자원해체
		 * */
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String dbUrl = System.getenv("ORACLE_DB_URL");
			String dbUser = System.getenv("ORACLE_DB_USER");
			String dbPassword = System.getenv("ORACLE_DB_PASSWORD");
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			if(con==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print("나의 오라클 요청 서블릿 가동 테스트 성공");
	}
}
