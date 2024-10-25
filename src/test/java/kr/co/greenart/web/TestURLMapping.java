package kr.co.greenart.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestURLMapping {
	@Autowired
	private MockMvc mock;
	
	@Test
	public void testQnaPage() throws Exception {
		mock.perform(get("/qna"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("qnaList"))
			.andExpect(view().name("qna"));
	}
}


