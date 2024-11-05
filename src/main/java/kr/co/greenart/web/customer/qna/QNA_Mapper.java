package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import kr.co.greenart.web.util.MyOrder;


@Mapper
public interface QNA_Mapper {
	
	@Select("Select Count(*) from customerqna where is_deleted = false")
	int count();
	
	@Select("Select Count(*) from customerqna where is_secure=true and is_deleted=false")
	int countSecure();
	
	@Select("Select Count(*) from customerqna where is_deleted = false and "
	        + "(title like concat('%', #{search}, '%') or content like concat('%', #{search}, '%'))")
	int countSearch(String search);

	
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
	
	@Select({
	    "select article_id, title, content, username, views, is_secure, created_at, updated_at from customerqna",
	    "where is_deleted = false and (title like concat('%', #{search}, '%') or content like concat('%', #{search}, '%'))",
	    "ORDER BY article_id DESC",
	    "LIMIT #{pageSize} OFFSET #{offset}"
	})
	@ResultMap("qnaList")
	List<QNA> findBySearch(String search, int pageSize, int offset);

	
	@Select({"select article_id, title, content, username, views, is_secure, password, created_at, updated_at from customerqna"
		,"WHERE is_secure = false and is_deleted = false"
		,"ORDER BY article_id DESC"
		,"  LIMIT #{pageSize} OFFSET #{offset}"
	})
	@ResultMap("qnaList")
	List<QNA> findBySecureIsFalse(int pageSize, int offset);
	
	@Select({"select article_id, title, content, username, views, is_secure, password, created_at, updated_at from customerqna"
		,"WHERE is_secure = true and is_deleted = false"
		,"ORDER BY article_id DESC"
		,"  LIMIT #{pageSize} OFFSET #{offset}"
	})
	@ResultMap("qnaList")
	List<QNA> findBySecureIsTrue(int pageSize, int offset);
	
	@Select("SELECT * FROM customerqna WHERE article_id = #{articleId}")
	@Results(
			id = "qnaMapping"
			, value = {
					@Result(column = "article_id", property = "articleId", id = true)
					, @Result(column = "title", property = "title")
					, @Result(column = "content", property = "content")
					, @Result(column = "username", property = "username")
					, @Result(column = "password", property = "password")
					, @Result(column = "views", property = "views")
					, @Result(column = "created_at", property = "createdAt")
					, @Result(column = "updated_at", property = "updatedAt")
					, @Result(column = "is_secure", property = "secure")
					, @Result(column = "is_deleted", property = "deleted")
			}
	)
	QNA findByPk(Integer articleId);

	
//	@Select("select * from customerqna where article_id = #{article_id}")
//	void findSecureByPk(Integer articleId);
	
	@Update("update customerqna SET views=views+1, updated_at = updated_at where article_id =#{articleId}")
	int updateCount(int articleId);
	
	@Update("update customerqna SET is_deleted=1 where article_id =#{articleId} and password=#{password}")
	int updateDelete(int articleId, String password);

	@Update("UPDATE customerqna "
			+ "SET title=#{qna.title}, content=#{qna.content}, username=#{qna.username} "
			+ "WHERE article_id=#{qna.articleId} AND password=#{inputPassword}")
	int updateInfo(@Param("qna") QNA qna, @Param("inputPassword") String inputPassword);

	class SQLProvider {
		public String selectOrderBy(MyOrder order) {
			return new SQL()
					.SELECT("columns")
					.FROM("tablename")
					.ORDER_BY(order.get정렬방식())
					.LIMIT("리밋")
					.OFFSET("오프셋")
					.toString();
		}
	}

}
