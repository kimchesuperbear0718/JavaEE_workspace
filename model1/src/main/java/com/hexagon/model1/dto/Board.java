package com.hexagon.model1.dto;
/*
 * 이 클래스는 로직 작성용이 아니라,오직 데이터를 모아서 전달하기 위한 용도 이다..
 * 객체지향에서는 애프릴케이션에서 다루고자 하는 데이터를 보안상 은닉화 시킨다.
 * 
 * 
 * */
public class Board {

	private Long boardId;// int 보다 더 큰 자료형인 long으로 선언해야 오래된 게시물의 숫자를 표현할 수 있으므로..
						//소문자 long으로 선언하면 기본자료형이므로,게시물이 없을때의 null을 표현할수 없음..
						//db의 컬럼명이 단어의 조합일 경우 .. (언더바)를 흔히 쓰지만,java 분야에서는 ..쓰지않고 camel기법을 사용함
						//이 기법을 준수하면 추후 
	
	private String title;
	private String writer;
	private String content;
	private String createdAt;
	private Long hit;
	
	
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	
	
}