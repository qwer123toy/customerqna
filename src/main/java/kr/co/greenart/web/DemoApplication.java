package kr.co.greenart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kr.co.greenart.web.customer.qna.QNA;
import kr.co.greenart.web.customer.qna.QNA_Mapper;
import kr.co.greenart.web.customer.qna.QNA_Service;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
//메인 메소드가 존재하여 프로그램 시작점이 정해져있음
	
	@Autowired
	private QNA_Service service;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		for(int i=2; i<546; i++) {
			service.save(QNA.builder().title("title"+i).content("content" +i).username("username"+i).password("password"+i).build());
		}
		
	}

}
