package com.hexagon.noticeapp.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//아래의 클래스를 정의하는 것은 필수는 아니지만, 현재 우리의 애플리케이션의 문제점은 
//모든 클래스 또는 jsp 코드에서 lookup() 대상이 되는 코드를 하드코딩하고 있다는 점이다..
//만일, JNDI 의 이름이 변경될 경우, 모든 클래스 및 jsp에 들어있는 코드를 다 변경해야 한다..
//개발의 규모가 큰 경우 유지보수성이 현저히 떨어짐 = 비용 발생(시간, 인력)

//JNDI의 검색을 대신해주고, Connection을 필요로 하는 객체들에게 Connection 을 제공 및 반납도 여기서 알아서 처리..
public class PoolManager {
	
	InitialContext ctx;
	DataSource ds;
	
	public PoolManager() {
		try {
			ctx = new InitialContext(); //JNDI 검색 객체 생성
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//누군가에게 풀로부터 Connection 을 얻어다가 반환해주기
	public Connection getConnection() {
		
		Connection con=null;
		try {
			con=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Connection을 다 사용한 누군가가 다시 반납을 쉽게할 수 있도록 하는 메서드...
	public void release(Connection con) {
		try {
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Connection + PreparedStatement 까지 해제시키는 메서드 
	public void release(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
































