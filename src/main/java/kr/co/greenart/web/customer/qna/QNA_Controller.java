package kr.co.greenart.web.customer.qna;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.greenart.web.util.QNA_NotFoundException;

@Controller
public class QNA_Controller {

	@Autowired
	private QNA_Service service;

	
	@GetMapping("/qna")
	public String qna(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, 
			Model model) {

		
		int limit = size;
		int offset = page * size;
		int totalItems = service.count();
//		int totalPages = totalItems % size == 0 ? totalItems / size : totalItems / size + 1;
		int totalPages = (int) Math.ceil((double) totalItems / size);
		List<QNA> list = service.findAll(limit, offset);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		return "qna";
	}

	
	@GetMapping("/qnaSecure")
	public String qnaSecure(Model model) {
		List<QNA> qnaList = service.findBySecureIsTrue(10, 0);
		model.addAttribute("qnaList", qnaList);

		return "qnaSecure";
	}

	@GetMapping("/qnaForm")
	public String qnaForm(Model model) {
		
		return "qnaForm";
	}
	
    @PostMapping("/qnaForm")
    public String qnaFormPost(@RequestParam String title,
    		@RequestParam String content, @RequestParam String username,
    		@RequestParam String password) {
    	QNA qna = QNA.builder().title(title).content(content).username(username).password(password).build();
        int result = service.save(qna);
        return "redirect:/qna";
    }
    
	@GetMapping("/qna/{articleId}")
	public String qnaDetail(Model model, @PathVariable Integer articleId) {
		QNA qna = service.findByPk(articleId);
		if(qna==null) {
			throw new QNA_NotFoundException(articleId);
		}
		
		int rows = service.updateCount(articleId);
		if(rows==1) {//새로고침을 해도 뷰가 오르도록
			qna.setViews(qna.getViews()+1);
		}
		model.addAttribute("qna", qna);
		model.addAttribute("articleId", articleId); 
		return "/qnaDetail";
	}
	
    @GetMapping("/qna/{articleId}/delete")
	public String qnaDelete(Model model, @PathVariable Integer articleId) {
		QNA qna = service.findByPk(articleId);
		model.addAttribute("qna", qna);
		model.addAttribute("articleId", articleId);

		return "/qnaDelete";
	}
    @PostMapping("/qna/{articleId}/delete")
  	public String qnaDeletePost(@RequestParam Integer articleId, @RequestParam String password) {
  		
  		service.updateDelete(articleId, password);
  		
        return "redirect:/qna";
  	}
	

}