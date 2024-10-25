package kr.co.greenart.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.greenart.web.customer.qna.QNA;
import kr.co.greenart.web.customer.qna.QNA_Mapper;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private QNA_Mapper mapper;
	
	@Test
	void testInsert() {
		QNA qna = QNA.builder().title("제목").content("내용").username("유저네임").password("비밀번호").build();
		int rows = mapper.save(qna);
		
		assertEquals(1, rows);
		assertNotNull(qna.getArticleId());
	}

	@Test
	void testSelect() {
		List<QNA> all = mapper.findAll(10, 0);
		
		assertNotEquals(0, all.size());
	}
}
