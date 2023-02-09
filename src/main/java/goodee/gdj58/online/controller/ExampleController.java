package goodee.gdj58.online.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.vo.Example;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ExampleController {
	@Autowired ExampleService exampleService;
	
	// 보기 수정 
	@GetMapping("/teacher/example/modifyExample")
	public String modifyExample(Model model, @RequestParam(value="exampleNo", required=true) int exampleNo) {
		log.debug("\u001B[31m"+exampleNo+"<-- modifyExample exampleNo");
		
		Example example = exampleService.getExampleOne(exampleNo);
		
		model.addAttribute("example", example);
		
		return "example/modifyExample";
	}
	@PostMapping("/teacher/example/modifyExample")
	public String modifyExmaple(Model model, Example example) {
		log.debug("\u001B[31m"+example+"<-- modifyExample example");
		
		int row = exampleService.modifyExample(example);
		
		log.debug("\u001B[31m"+row+"<-- modifyExample row");
		
		String returnUrl = "redirect:/teacher/test/testList";
		if(row != 1) {
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
			model.addAttribute("example", example);
			returnUrl = "example/modifyExample";
		}
		
		return returnUrl;
	}
	
	// 보기 추가
	@GetMapping("/teacher/example/addExample")
	public String addExample(Model model, @RequestParam(value="questionNo", required=true) int questionNo) {
		log.debug("\u001B[31m"+questionNo+"<-- addExample questionNo");
		
		model.addAttribute("questionNo", questionNo);
		
		return "example/addExample";
	}
	@PostMapping("/teacher/example/addExample")
	public String addExample(Model model, Example example) {
		log.debug("\u001B[31m"+example+"<-- addExample example");
		
		List<Example> exampleList = example.getExampleList();
		
		int row = 0;
		for(Example e : exampleList) {
			row += exampleService.addExample(e);
		}
		
		String returnUrl = "redirect:/teacher/question/addQuestion?questionNo="+example.getQuestionNo();
		if(row != exampleList.size()) {
			model.addAttribute("msg", "보기 등록에 실패했습니다. 다시 시도해주세요.");
			model.addAttribute("questionNo", example.getQuestionNo());
			returnUrl = "teacher/example/addExample";
		}
		return returnUrl;
	}
}