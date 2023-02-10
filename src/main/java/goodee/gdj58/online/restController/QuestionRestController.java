package goodee.gdj58.online.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Question;

@RestController
public class QuestionRestController {
	@Autowired QuestionService questionService;
	
	@GetMapping("/questionList")
	public List<Question> getQuestionList(@RequestParam(value="testNo", required=true) int testNo){
		return questionService.getQuestionList(testNo);
	}
}
