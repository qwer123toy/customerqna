package kr.co.greenart.web.util;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerQNA {
	int article_id;
	String title;
	String content;
	String username;
	String password;
	Integer views;
	Timestamp times;
	Timestamp updated_at;
	Integer is_secure;
	Integer is_deleted;	
}
