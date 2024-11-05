package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.stereotype.Service;

public interface QNA_Service {
	
	int count();
	
	int countSecure();

	int countSearch(String search);

	
	int save(QNA qna);

	List<QNA> findAll(int pageSize, int offset);
	
	List<QNA> findBySearch(String search, int pageSize, int offset);

	
	QNA findByPk(Integer articleId);
	
	int updateCount(Integer articleId);

	int updateDelete(Integer articleId, String password);
	
	int updateInfo(QNA qna, String inputPassword);

	
	List<QNA> findBySecureIsTrue(int pageSize, int offset);

	List<QNA> findBySecureIsFalse(int pageSize, int offset);

}
