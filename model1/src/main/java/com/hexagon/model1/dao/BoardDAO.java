package com.hexagon.model1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hexagon.model1.dto.Board;
import com.hexagon.model1.pool.PoolManager;

/*
 * DAO 란?
 * 1)Data Access Object의 약어이다
 * 2)오직 데이터베이스 관련한 작업(Create=insert,Read=select, Update, Delete=delete)만을 전담하는 객체
 * 3)중립적이어야 모든 플랫폼에서 재사용 가능성이 높다..
 * 4)애플리케이션 설계 분야의 용어이기 때문에 javaEE 분야에 국한된 개념이 아니다!!
 * 
 * */

public class BoardDAO {
	PoolManager pool=PoolManager.getInstance();	
	
	//insert 업무를 수행하는 메서드
	//매게변수로 배열을 사용해도 되지만,각 데이터 접근 시 인덱스0,1,2
	public int insert(Board board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=pool.getConnection();
		String sql="insert into board(board_id,title,writer,content) Values(seq_board.nextval, ?,?,?)";
		
		int rowCount=0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return rowCount;
	}
	
	public ResultSet selectAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // select 문이 실행 후 그 결과인 표를 받는 객체
        List list = new ArrayList();//비어있는 리스트 생성
        							//리스트는 사실 배열과 거의 같다..
        
        
        
        con = pool.getConnection();
        String sql = "select * from board order by board_id desc ";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery(); // select 실행 및 표 반환!!
            // 이 DAO 는 오직 DB 관련 업무만 담당하므로, rs 를 이용한 표 만들기는 여기서 하지 말자!!
            //rs 는 곧 finally에서 죽을 예정이므로,죽기전에 rs와 거의 흡사한 형태의 java객체로 옮겨담자!!
            //1)rs 표의 순서있는 집합은 java.util의 List로 모방하고
            //2)rs의 레코드 한건은 Board 클래스의 인스턴스 1개로 모방하자
            
            while(rs.next()) {
            	Board board = new Board();
            	board.setBoardId(rs.getLong("board_id"));
            	board.setTitle(rs.getString("title"));
            	board.setWriter(rs.getString("writer"));
            	board.setCreatedAt(rs.getString("crated_at"));
            	board.setHit(rs.getLong("hit"));
            }
            
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.release(con, pstmt, rs);
        }
        return rs;
    }
	public void uqdate() {
		String sql="update board set title=?,writer=?,content=? where board_id=?";
	}
	
	
	public void delete() {
		String sql="delate board where board_id=?";
	}
	
}
