package goodee.gdj58.online.restController;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.QuestionExample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class QuestionRestController {
	@Autowired QuestionService questionService;
	
	@PostMapping(value="/teacher/test/question")
	public String addQuestion(@RequestBody QuestionExample questionExample) { 
		
		Question question = questionExample.getQuestion();
		List<Example> exampleList = questionExample.getExampleList();
		
		log.debug("\u001B[31m"+question+"<-- addQuestion question");
		log.debug("\u001B[31m"+exampleList+"<-- addQuestion exampleList");
		
		int row = questionService.addQuestion(question, exampleList);
		log.debug("\u001B[31m"+row+"<-- addQuestion row");
		
		int size = 1+exampleList.size(); 
		
		log.debug("\u001B[31m"+size+"<-- addQuestion size");
		
		String result = "fail";
		if(row == size) {
			result = "success";
		}
		
		return result;
	}
}
