package com.hexagon.model1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexagon.model1.dao.BoardDAO;
import com.hexagon.model1.dto.Board;
import com.hexagon.model1.pool.PoolManager;

/*
 	글쓰기 요청을 처리할 서블릿. 
  	JSP도 서블릿이기 때문에, 당연히 글쓰기 요청을 받을 수는 있으나, 업무 목적상 디자인이 관여되지 않는다면 굳이 jsp를 쓰게되면 
  	다른 개발자들이 디자인이 포함되어 있는줄 알고 혼동...
  	
  	글쓰기의 경우, 클라이언트가 서버에 다량의 데이터를 전송하는 것이므로, POST방식으로 요청을 시도한다...따라서 doXXX형 중 doPost로
  	요청을 받을 준비를 하자!!
*/
public class RegistServlet extends HttpServlet{
	
	BoardDAO boardDAO=new BoardDAO(); //오직 데이터베이스 관련된 로직( CRUD)만을 전담하는 클래스를 사용해보기 위해 선언
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PoolManager pool=PoolManager.getInstance();
		//웹브라우저가 전송한 파라미터 받기!!
		request.setCharacterEncoding("UTF-8"); //받아온, 한글 등의 파라미터값이 깨지지 않도록 인코딩
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println(title);
		System.out.println(writer);
		System.out.println(content);
		
		//DB에 넣기!!
		//파라미터들을 DAO에 전달할때 낱개로 전달하지 말고, 하나의 바구니(DTO)에 담아서
		//그 바구니를 전달하자 (배열보다 훨씬 직관성이 있으므로..)
		Board board = new Board();
		board.setTitle(title); //배열이었으면 0번째 엿으나, 객체이기에 단어로 표현..직관적
		board.setWriter(writer);
		board.setContent(content);
		
		int rowCount = boardDAO.insert(board);
		
		//응답 정보 만들기 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		StringBuilder tag = new StringBuilder();
		
		tag.append("<script>");
		if(rowCount !=1) {
			tag.append("alert('등록실패');");
			tag.append("history.back();"); //브라우저의 뒤로가기 버튼 눌렀을때와 동일한 기능, 즉 이전 히스토리로 화면 전환(글쓰기 실패시 다시 글쓰기 폼을 보여주기) 
		}else {
			tag.append("alert('등록성공');");
			//목록으로 보낼 예정
			tag.append("location.href='/board/list.jsp';");
		}
		tag.append("</script>");
		
		out.print(tag.toString()); //사용자가 보게될 텍스트를 출력스트림에 보관... 추후 고양이가 이 정보를 이용하여  응답정보를 만들어 보낸다..
	
	}
}






