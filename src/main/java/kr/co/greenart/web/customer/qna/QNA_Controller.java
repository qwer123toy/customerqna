package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.greenart.web.util.QNA_NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class QNA_Controller {

	@Autowired
	private QNA_Service service;

	@GetMapping("/qna")
	public String qna(@RequestParam(defaultValue = "0") int page,
	                  @RequestParam(defaultValue = "10") int size,
	                  @RequestParam(required = false) String search, // 검색어 파라미터
	                  Model model, HttpSession session) {

	    session.invalidate();

	    int limit = size;
	    int offset = page * size;
	    int totalItems;
	    List<QNA> list;

	    if (search != null && !search.isEmpty()) {
	        totalItems = service.countSearch(search);
	        list = service.findBySearch(search, limit, offset);
	        model.addAttribute("search", search); // 검색어를 모델에 추가
	    } else {
	        totalItems = service.count();
	        list = service.findAll(limit, offset);
	    }

	    int totalPages = (int) Math.ceil((double) totalItems / size);
	    int pageSize = 10; // 한 번에 표시할 페이지 수

	    // 현재 페이지가 속한 구간의 시작과 끝 페이지 계산
	    int currentGroup = (page / pageSize); 
	    int startPage = currentGroup * pageSize;
	    int endPage = Math.min(startPage + pageSize - 1, totalPages - 1); // 총 페이지를 넘지 않도록 설정

	    // 모델에 페이지 관련 정보 추가
	    model.addAttribute("list", list);
	    model.addAttribute("page", page);
	    model.addAttribute("size", size);
	    model.addAttribute("totalItems", totalItems);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);

	    return "qna";
	}


	@PostMapping("/qna")
	public String qnaPost(@PageableDefault(size = 10) Pageable pagable, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam String search, Model model) {
//		int page = pagable.getPageNumber();
//		int size = pagable.getPageSize();
		int limit = size;
		int offset = page * size;
		int totalItems = service.countSearch(search);
//		int totalPages = totalItems % size == 0 ? totalItems / size : totalItems / size + 1;
		int totalPages = (int) Math.ceil((double) totalItems / size);
		List<QNA> list = service.findBySearch(search, limit, offset);
		model.addAttribute("search", search);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		return "qna";
	}

	@GetMapping("/qnaSecure")
	public String qnaSecure(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			Model model) {
		int limit = size;
		int offset = page * size;
		int totalItems = service.countSecure();
//		int totalPages = totalItems % size == 0 ? totalItems / size : totalItems / size + 1;
		int totalPages = (int) Math.ceil((double) totalItems / size);

	    int pageSize = 10; // 한 번에 표시할 페이지 수

		  // 현재 페이지가 속한 구간의 시작과 끝 페이지 계산
	    int currentGroup = (page / pageSize); 
	    int startPage = currentGroup * pageSize;
	    int endPage = Math.min(startPage + pageSize - 1, totalPages - 1); // 총 페이지를 넘지 않도록 설정

		
		
		List<QNA> list = service.findBySecureIsTrue(limit, offset);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		 model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);

		return "qnaSecure";
	}

	@GetMapping("/qnaForm")
	public String qnaForm(Model model) {

		return "qnaForm";
	}

	@PostMapping("/qnaForm")
	public String qnaFormPost(@RequestParam String title, @RequestParam String content, @RequestParam String username,
			@RequestParam String password) {
		QNA qna = QNA.builder().title(title).content(content).username(username).password(password).build();
		int result = service.save(qna);
		return "redirect:/qna";
	}

	@GetMapping("/qna/{articleId}")
	public String qnaDetail(Model model, @PathVariable Integer articleId, HttpSession session) {
		QNA qna = service.findByPk(articleId);
		if (qna == null) {
			throw new QNA_NotFoundException(articleId);
		}

		// 비밀번호가 필요한 게시물인지 확인
		if (qna.getSecure()) {
			Boolean hasAccess = (Boolean) session.getAttribute("qnaAccess_" + articleId);
			if (hasAccess == null || !hasAccess) {
				// 비밀번호 검증이 안 되어 있으면 비밀번호 입력 페이지로 리다이렉트
				return "redirect:/qna/checkPasswordPage?articleId=" + articleId;
			}
		}

		// 조회수 증가 로직
		int rows = service.updateCount(articleId);
		if (rows == 1) {
			qna.setViews(qna.getViews() + 1);
		}

		model.addAttribute("qna", qna);
		model.addAttribute("articleId", articleId);
		return "/qnaDetail";
	}

	@PostMapping("/qna/checkPassword")
	public String checkPassword(@RequestParam Integer articleId, @RequestParam String password, HttpSession session,
			Model model) {
		QNA qna = service.findByPk(articleId);
		if (qna == null) {
			throw new QNA_NotFoundException(articleId);
		}

		// 비밀번호가 일치하는지 확인
		if (qna.getSecure() && qna.getPassword().equals(password)) {
			session.setAttribute("qnaAccess_" + articleId, true); // 세션에 접근 권한 저장
			// 성공하면 리다이렉트할 URL을 반환
			return "redirect:/qna/" + articleId;
		} else {
			model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("articleId", articleId);
//        	        return "redirect:/qna/checkPasswordPage?articleId="+articleId;
			return "checkPassword";
		}
	}

	@GetMapping("/qna/checkPasswordPage")
	public String checkPasswordPage(@RequestParam Integer articleId, Model model) {
		model.addAttribute("articleId", articleId);
		return "/checkPassword";
	}

	@GetMapping("/qna/{articleId}/delete")
	public String qnaDelete(Model model, @PathVariable Integer articleId) {
		QNA qna = service.findByPk(articleId);
		model.addAttribute("qna", qna);
		model.addAttribute("articleId", articleId);

		return "/qnaDelete";
	}

	@PostMapping("/qna/{articleId}/delete")
	public String qnaDeletePost(@RequestParam Integer articleId, @RequestParam String password, Model model) {
		QNA qna = service.findByPk(articleId);
		if (qna.getPassword().equals(password)) {
			service.updateDelete(articleId, password);
			return "redirect:/qna";
		} else {
			model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("qna", qna);
			model.addAttribute("articleId", articleId);
			return "/qnaDelete";

		}

	}

	@GetMapping("/qna/{articleId}/update")
	public String qnaUpdate(Model model, @PathVariable Integer articleId) {
		QNA qna = service.findByPk(articleId);
		model.addAttribute("qna", qna);
		model.addAttribute("articleId", articleId);

		return "/qnaUpdate";
	}

	@PostMapping("/qna/{articleId}/update")
	public String qnaUpdatePost(@RequestParam Integer articleId, @RequestParam String title,
			@RequestParam String content, @RequestParam String username,
			@RequestParam String inputPassword, Model model) {
		QNA qna = service.findByPk(articleId);
		if (qna.getPassword().equals(inputPassword)) {
			QNA updateQNA = QNA.builder().title(title).content(content).username(username).articleId(articleId).build();
			service.updateInfo(updateQNA, inputPassword);
			return "redirect:/qna/" + articleId;
		} else {
			model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("qna", qna);
			model.addAttribute("articleId", articleId);
			return "/qnaUpdate";

		}

	}

}