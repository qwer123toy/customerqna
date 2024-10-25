package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface QNA_Mapper {
	
	@Select("Select Count(*) from customerqna")
	int count();
	
	@Insert({
		"INSERT INTO customerqna (title, content, username, password)"
		, "VALUES (#{title}, #{content}, #{username}, #{password})"
	})
//	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyColumn = "article_id", keyProperty = "articleId"
//		, resultType = Integer.class, before = false) //MYSQL용
	@Options(useGeneratedKeys = true, keyProperty = "articleId")
	int save(QNA qna);
	
	@Select({"select article_id, title, content, username, views, is_secure, created_at, updated_at from customerqna"
		,"where is_deleted=false"
		,"ORDER BY article_id DESC"," LIMIT #{pageSize} OFFSET #{offset}"
		
	})
	@Results(id="qnaList", value= {
			@Result(column = "article_id", property="articleId")
			, @Result(column = "title", property="title")
			, @Result(column = "content", property="content")
			, @Result(column = "username", property="username")
			, @Result(column = "views", property="views") 
			, @Result(column = "is_secure", property="secure")
			, @Result(column = "created_at", property="createdAt")
			, @Result(column = "updated_at", property="updatedAt")
	})
	List<QNA> findAll(int pageSize, int offset);
	
	@Select({"select article_id, title, content, username, views, is_secure, password from customerqna"
		,"WHERE is_secure = false"
		,"ORDER BY article_id DESC"
		,"  LIMIT #{pageSize} OFFSET #{offset}"
	})
	@ResultMap("qnaList")
	List<QNA> findBySecureIsFalse(int pageSize, int offset);
	
	@Select({"select article_id, title, content, username, views, is_secure, password from customerqna"
		,"WHERE is_secure = true"
		,"ORDER BY article_id DESC"
		,"  LIMIT #{pageSize} OFFSET #{offset}"
	})
	@ResultMap("qnaList")
	List<QNA> findBySecureIsTrue(int pageSize, int offset);
	
	@Select("SELECT article_id,title, content, username, views, is_secure,password FROM customerqna WHERE article_id = #{articleId}")
	QNA findByPk(Integer articleId);

	
	@Select("select article_id, title, content, username, views, is_secure from customerqna where article_id = #{article_id}")
	void findSecureByPk();
	
	@Update("update customerqna SET views=views+1 where article_id =#{articleId}")
	int updateCount(int articleId);
	
	@Update("update customerqna SET is_deleted=1 where article_id =#{articleId} and password=#{password}")
	int updateDelete(int articleId, String password);
	
}
