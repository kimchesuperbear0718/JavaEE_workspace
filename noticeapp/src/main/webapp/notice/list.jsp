<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%!// 이 영역은 선언부라고 불리고, 여기에 작성한 코드는 이 jsp가 고양이에 의해 서블릿으로 코딩되어질때,
	// 멤버영역으로 작성됨
	int age = 30; // 멤버변수

	public String getName() {
		return "고양이";
	}%>
<!-- 
	page의 지시영역은 사실 아래의 서블릿에서의 코드와 같다..
	response.setContentType("text/html;cjarset=utf-8")
	
	즉 jsp를 이용하면 어려운 서블릿 코드를 이용하지 않고도 서블릿을 작성할 수 있다..
	결론 JSP는 곧 서블릿(made by tomcat)이다!!
	
	1. JSP란?
		Java Server Page 의 줄임말 = java 기술로 작성된 서버측에서 실행되는 페이지 = 서블릿
		
	2. JSP 작성 영역 (JSP 코드를 작성할 수 있는 영역)
		1) 지시영역
		
		2) 선언부
		
		3) 스크립틀릿 영역 <{%  %}> 영역은 서블릿의 service() 메서드 영역이었다!!! (doXXX형의 영역으로 생각해도 된다) - {} 는 무시할 것
		
		4) 표현식
			개발자가 out.print() 를 굳이 사용하지 않고도 단축된 표현으로 출력을 처리하는 표현식
			[%=데이터%]	 [] 을 => <> 로 바꿔쓰기
 -->
<%
// 우리가 전에 사용했던 ListServlet.java도 리스트를 처리할 수 있었지만, 디자인 표현에는 너무 비효율적이었으므로,
// 처리할 파일이 디자인 표현이 포함되어있다면 jsp가 서블릿보단 더 나은 선택이 될 수 있다
// jsp에서는 개발 시 필수적으로 사용되는 객체들을 미리 tomcat이 가동되는 시점부터
// 메모리에 올려놓고 그 이름 마저도 정해놓았는데, 이러한 이미 정해진 이름을
// 갖는 내장된 객체들을 가리켜 JSP의 내장(bulit-in) 객체라 한다
// 1) out 내장객체 - 이미 메모리에 올려놓았다 out 이란 이름으로..
out.print("AAAA");
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

Class.forName("oracle.jdbc.driver.OracleDriver");
String dbUrl = System.getenv("ORACLE_DB_URL");
String dbUser = System.getenv("ORACLE_DB_USER");
String dbPassword = System.getenv("ORACLE_DB_PASSWORD");
con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

StringBuffer sql = new StringBuffer();
sql.append("select * from notice order by notice_id desc");

pstmt = con.prepareStatement(sql.toString());

rs = pstmt.executeQuery();
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>

	<h2>Zebra Striped Table</h2>
	<p>For zebra-striped tables, use the nth-child() selector and add a
		background-color to all even (or odd) table rows:</p>

	<table>
		<tr>
			<th>notice_id</th>
			<th>title</th>
			<th>writer</th>
			<th>create_at</th>
			<th>hit</th>
		</tr>
		<%
		// 이 영역은 스크립틀릿 영역이며, service() 메서드의 영역이다
		%>
		<%
		while (rs.next()) {
		%>
		<tr>
			<!-- 주의할 점 = 은 out.print() 를 대신하는 표현식이지만 out.print(); 뒤에 붙는 세미콜론은 적지 않는다.. -->
			<td><%=rs.getInt("notice_id")%></td>
			<td><%=rs.getString("title")%></td>
			<td><%=rs.getString("writer")%></td>
			<td><%=rs.getString("create_at")%></td>
			<td><%=rs.getInt("hit")%></td>
		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="3">
				<button>ê¸ì°ê¸°</button>
			</td>
		</tr>

	</table>

</body>
</html>
<%
if (rs != null)
	rs.close();
if (pstmt != null)
	pstmt.close();
if (con != null)
	con.close();
%>
