package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class QNA_ServiceImpl implements QNA_Service {
	
	@Autowired
	private QNA_Mapper mapper;
	
	@Override
	public int save(QNA qna) {
		// TODO Auto-generated method stub
		return mapper.save(qna);
	}
	
	@Override
	public List<QNA> findAll(int pageSize, int offset) {
		
		return mapper.findAll(pageSize, offset);
	}

	@Override
	public QNA findByPk(Integer articleId) {
		// TODO Auto-generated method stub
		return mapper.findByPk(articleId);
	}

	@Override
	public int updateCount(Integer articleId) {
		// TODO Auto-generated method stub
		return mapper.updateCount(articleId);
	}

	@Override
	public int updateDelete(Integer articleId, String password) {
		// TODO Auto-generated method stub
		return mapper.updateDelete(articleId, password);
	}

	@Override
	public List<QNA> findBySecureIsTrue(int pageSize, int offset) {
		// TODO Auto-generated method stub
		return mapper.findBySecureIsTrue(pageSize, offset);
	}

	@Override
	public List<QNA> findBySecureIsFalse(int pageSize, int offset) {
		// TODO Auto-generated method stub
		return mapper.findBySecureIsFalse(pageSize, offset);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return mapper.count();
	}

	
}
