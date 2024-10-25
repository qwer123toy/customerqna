package kr.co.greenart.web.util;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerQNAMapper {
	//2
	@Insert("insert into customerqna(title, content, username, password) values(#{title}, #{content}, #{username}, #{password})")
	void insert();

	//3
	@Select("select article_id, title, content, username from customerqna")
	List<CustomerQNA> selectAll();

	//3-1
	@Select("select title, content, username from customerqna where is_secure=false")
	List<CustomerQNA> selectSecureAll(int article_id);
	
	//4
	@Select("select title, content, username from customerqna where article_id = #{article_id}")
	CustomerQNA selectByPk(int article_id);

	
	//5
	@Select("select is_secure from customerqna where article_id = #{article_id}")
	CustomerQNA checkSecureByPk(int article_id);
	
	//6
	
	@Update("update customerqna SET views=views+1 where article_id =#{article_id}")
	int updateViews(int article_id);

	//7
	@Update("update customerqna SET is_deleted=1 where article_id =#{article_id} and password=#{password}")
	int delete(int article_id);

//	-- 1. 익명 고객센터 문의게시판 테이블을 생성하는 쿼리문을 작성해주세요
//	-- 2. 글 작성
//	-- 3. 게시글 목록(id, title, username, is_secure)
//	-- 3-1. 게시글 조회 시, is_secure 값이 false인 행만 조회
//	-- 4. 게시글 조회(id로 검색, title, content, username)
//	-- 5. 게시글의 비밀 여부 조회 (is_secure)
//	-- 6. views count 수정(1 증가)
//	-- 7. 글 논리 삭제(pk 및 password 일치) : is_deleted => 1로 수정
}
