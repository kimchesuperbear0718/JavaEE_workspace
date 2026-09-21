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

public class UpdateServlet extends HttpServlet {
	PoolManager pool = PoolManager.getInstance();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// update board set title='변경제목',writer='변경된작성자',content='변경내용'where
		// board_id=내본글board_id ;

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int board_id = Integer.parseInt(request.getParameter("board_id"));

		String sql = "update board set title=?, writer=?, content=? where board_id=?";

		// DML ( Connection,PreparedStatement)
		Connection con = pool.getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);// 쿼리문 내에서 첫번째로 발견된 ? 물음표
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			pstmt.setInt(4, board_id);

			// DmL실행
			int rowCount = pstmt.executeUpdate();

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
				

			out.print("<script>");
			if (rowCount == 0) {
				out.print("alart('수정실패');");
				out.print("history.back();");
			} else {
				out.print("alert('수정성공');");
				out.print("location.href='/board/content.jsp?x=" + board_id + "';");
			}
			out.print("</script>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
	}

}
