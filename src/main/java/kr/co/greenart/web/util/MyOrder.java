package kr.co.greenart.web.util;

public class MyOrder {
	private String column;
	private String order;
	
	public String get정렬방식() {
		return column + " " + order;
	}
}
