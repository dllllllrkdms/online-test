package goodee.gdj58.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QuestionController {
	@Autowired QuestionService questionService;
	@Autowired ExampleService exampleService;
	
	// 문제 삭제
	@GetMapping("/teacher/question/removeQuestion")
	public String removeQuestion(RedirectAttributes rttr, @RequestParam(value="questionNo", required=true) int questionNo, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+questionNo+"<-- removeQuestion questionNo");
		
		int row = questionService.removeQuestion(questionNo);
		log.debug("\u001B[31m"+row+"<-- removeQuestion questionNo");
		
		String msg = "삭제를 실패했습니다. 다시 시도해주세요.";
		if(row == 1) {
			msg = "삭제되었습니다.";
		}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/teacher/test/testOne?testNo="+testNo;
		
	}
	
	// 문제 수정
	@GetMapping("/teacher/question/modifyQuestion")
	public String modifyQuestion(Model model, @RequestParam(value="questionNo", required=true) int questionNo) {
		log.debug("\u001B[31m"+questionNo+"<-- modifyQuestion questionNo");
		
		Question question = questionService.getQuestion(questionNo);
		
		model.addAttribute("question", question);
		
		return "question/modifyQuestion";
	}
	@PostMapping("/teacher/question/modifyQuestion")
	public String modifyQuestion(Model model, Question question) {
		log.debug("\u001B[31m"+question+"<-- modifyQuestion question");
		
		int row = questionService.modifyQuestion(question);
		log.debug("\u001B[31m"+row+"<-- modifyQuestion row");
		
		String returnUrl = "redirect:/teacher/test/testOne?testNo="+question.getTestNo();
		if(row != 1) {
			returnUrl = "question/modifyQuestion";
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
			model.addAttribute("question", question);
		}
		return returnUrl;
	}
	
	// 문제 상세보기
	@GetMapping("/teacher/question/questionOne")
	public String getQuestion(Model model, @RequestParam(value="questionNo", required=true) int questionNo) {
		log.debug("\u001B[31m"+questionNo+"<-- getQuestion questionNo");
		
		Question question = questionService.getQuestion(questionNo);
		List<Example> exampleList = exampleService.getExampleListByQuestion(questionNo);
		
		model.addAttribute("exampleList", exampleList);
		model.addAttribute("question", question);
		
		return "question/questionOne";
	}
	
	// 문제 추가
	@GetMapping("/teacher/question/addQuestion")
	public String addQuestion(Model model, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+testNo+"<-- addQuestion testNo");
		
		model.addAttribute("testNo", testNo);
		
		return "question/addQuestion";
	}
	@PostMapping("/teacher/question/addQuestion")
	public String addQuestion(Model model, Question question) { 
		log.debug("\u001B[31m"+question+"<-- addQuestion question");
		
		int row = questionService.addQuestion(question);
		log.debug("\u001B[31m"+row+"<-- addQuestion row");
		
		String returnUrl = "redirect:/teacher/test/testOne?testNo="+question.getTestNo();
		
		if(row != 1) {
			model.addAttribute("msg", "문제 등록에 실패했습니다. 다시 시도해주세요.");
			model.addAttribute("question", question);
			returnUrl = "question/addQuestion";
		}
		
		return returnUrl;
	}
}