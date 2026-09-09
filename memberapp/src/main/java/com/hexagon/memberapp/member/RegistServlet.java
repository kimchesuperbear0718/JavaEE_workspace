package com.hexagon.memberapp.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.javadoc.internal.doclets.formats.html.markup.Script;

/*-----------------------------------
 * 클라이언트(브라우저)의 회원가입 요청을 처리할 서블릿 정의
 * -----------------------------------*/
public class RegistServlet extends HttpServlet {

	// 클라이언트의 요청 유형이, 회원가입이므로, 즉 서버에 데이터를 전송함이 목적이므로 HTTP 전송 메서드 중 POST 로 요청이 들어올
	// 예정임
	// 따라서 개발자는 doXXX() 형 메서드 중 doPost() 를 준비해놓으면 된다...
	// 실질적으로 요청을 처리하는 메서드는 doXXX형이므로, 요청에 의해 생성된 request와 response 객체가 아래의 doPost()
	// 까지 전달되어 진

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 클라이언트가 Post 방식으로 전송한 데이터들은 Body에 실어져서 날아오고, 그 정보가 '요청' 정보를 표현한 request 객체에
		// 들어있다.
		// 회원가입을 위해서 이 정보들을 꺼내보자
		// 만일 사용자가 입력한 값이 숫자라 할지라도, 웹상의 전송은 모두 문자열로 취급함
		// ex) 5555 ==>"5555" 로 인식됨
		request.setCharacterEncoding("utf-8");//
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		// 서버의 콘솔 즉 톰캣의 로그에 꺼내온 파라미터 값 출력해보기
		System.out.println("login_id =" + login_id);
		System.out.println("password =" + password);
		System.out.println("name =" + name);
		System.out.println("email =" + email);
		System.out.println("phone =" + phone);
		System.out.println("address =" + address);

		// 파라미터값들이 전송되었음을 확인했으므로,이 시점부터는
		/*
		 * 파라미터값들이 전송되었음을 확인했으므로,이 시점부터는 mysql에 넣어보자!!! java 언어로 DB를 제어하기 위해서는 해당 기종에 맞는
		 * JDBC 드라이버가 이 프로젝트에 존재해야한다..(jar) apache 단체에서 운영중인 maven 저장소에서 다운로드 받거나, db
		 * 밴더시에서 직접 받아도 됨.. [일반적인 jdbc 프로그래밍 순서] 1.드라이버 로드 2.접속 3.퀴리 수행 4.접속 해제 및 자원해체
		 */
		Connection con = null;// finally에서 닫기 위해 밖으로 빼놓자,지역변수이므로 반드시 초기화가 요구됨
		PreparedStatement pstmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8", "root",
					"1234");
			if (con == null) {
				System.out.println("실패");
			} else {
				System.out.println("성공");
			}

			// 3)쿼리실행 - 쿼리샐행을 담당하는 객체가 PreparedStatement 이며 접속이 먼저 선행되어야 하므로
			// String sql="insert into member(login_id,password,name,email,phone,addr)";
			// sql+="
			// values('"+login_id+"','"+password+"','"+name+"','"+email+"','"+email+"','"+address+"')";

			// 위의 SQL 퀴리문을 처리하는 방법보다 개선된 방법을 이용해보자
			// 1)성능에 지장을 준다
			// 2)홑따옴표 처리가 피곤하다...
			// 해결책?바인드 변수를 이용하면 됨
			String sql = "INSERT INTO member(login_id,password,name,email,phone,addr) values(?,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);// 퀴리수행 객체 얻기!!(아직 수행 안함)

			// 쿼리 실행전에 ?로 표현된 바인드 변수의 값을 할당해야 한다..
			pstmt.setString(1, login_id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, address);

			// DML 즉 조작어(insert,update,delete)의 경우엔 executeUpdate() 메서드로 퀴리를 실행...
			int resultRow = pstmt.executeUpdate();

			// DML 서옥ㅇ했을 경우엔 성공메시지,실패하면 실패메시지를 보여주자!!
			// 유저의 브라우저가 보게될 컨텐츠를 response(응답)객체가 보유한 출력스트림을 얻어서,그 스트림에 넣어두자
			PrintWriter out = response.getWriter();
			// HTTP통신은 우편시스템을 따르는데,즉 클라이언트와 서버가 데이터를 주고 받을때 머리(header),몸(body)로 형식을 갖추어서
			// 데이ㅓㅌ
			// 한글이 깨지지 않으려면 서버는 이 문서의 인코딩 방식등을 header에 명시..
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");

			if (resultRow == 1) {
				out.print("<script>alert('regist success 성공');</script>");
			} else {
				out.print("regist failed 실패");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // 개발자 또는 엔지니어를 위한 로그..
			System.out.println("Driver has not found Please chack again.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connecting Failed");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
