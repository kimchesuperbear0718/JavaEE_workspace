게시판 테이블 만들기
create table board2(
	board_id number primary key 
	,title varchar2(100)
	,writer varchar2(25)
	,content clob
	,created_at date default sysdate
	,hit number default 0
);

시퀀스 만들기
create sequence seq_board2
increment by 1
start with 1;