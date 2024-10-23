
create table if not exists customerqna(

article_id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(200) NOT NULL,
content TEXT NOT NULL,
username VARCHAR(20) NOT NULL,
password VARCHAR(64) NOT NULL,
views INT DEFAULT 0,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
is_secure bit DEFAULT 0,
is_deleted bit DEFAULT 0	
);

insert into customerqna(title, content, username, password) values(title, content, username, password);

select article_id, title, content, username from customerqna;

select title, content, username from customerqna where article_id = ?;

select title, content, username from customerqna where article_id = ? and is_secure=false;

select is_secure from customerqna where article_id = ?;

update customerqna SET views=views+1 where article_id =?;

update customerqna SET is_deleted=1 where article_id =? and password=?;

-- 1. 익명 고객센터 문의게시판 테이블을 생성하는 쿼리문을 작성해주세요
-- 2. 글 작성
-- 3. 게시글 목록(id, title, username, is_secure)
-- 4. 게시글 조회(id로 검색, title, content, username)
-- 4-1. 게시글 조회 시, is_secure 값이 false인 행만 조회
-- 5. 게시글의 비밀 여부 조회 (is_secure)
-- 6. views count 수정(1 증가)
-- 7. 글 논리 삭제(pk 및 password 일치) : is_deleted => 1로 수정
