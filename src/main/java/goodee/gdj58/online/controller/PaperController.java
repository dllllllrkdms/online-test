package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.DateCompare;
import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.PaperService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaperController {
	@Autowired QuestionService questionService;
	@Autowired PaperService paperService;
	@Autowired TestService testService;
	@Autowired ExampleService exampleService;
	@Autowired DateCompare dateCompare;
	
	// 답안
	@GetMapping("/student/answer")
	public String getPaperOne(Model model, @RequestParam(value="testNo", required=true) int testNo) {
		
		Test test = testService.getTestOne(testNo);
		List<Question> questionList = questionService.getQuestionList(testNo);
		List<Example> exampleList = exampleService.getExampleList(testNo);
		int questionCount = questionService.getQuestionCount(testNo);
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("exampleList", exampleList);
		model.addAttribute("test", test);
		model.addAttribute("questionCount", questionCount);

		return "student/answer";
	}
	
	// 점수 확인 
	@GetMapping("/student/score")
	public String getPaperScore(HttpSession session, Model model, @RequestParam(value="testNo", required=true) int testNo) {
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		
		int score = paperService.getPaperScore(testNo, loginStudent.getStudentNo());
		log.debug("\u001B[31m"+score+"<-- getPaperScore score");
		
		model.addAttribute("score", score);
		
		return "test/student/score";
	}
	
	// 답안 입력
	@GetMapping("/student/test/paper")
	public String addPaper(Model model, @RequestParam(value="testNo", required=true) int testNo) {
		
		Test test = testService.getTestOne(testNo);
		
		if(dateCompare.todayCompare(test.getTestDate()) != 0) {
			return "redirect:/student/calendar";
		}
		
		List<Question> questionList = questionService.getQuestionList(testNo);
		List<Example> exampleList = exampleService.getExampleList(testNo);
		int questionCount = questionService.getQuestionCount(testNo);
			
		model.addAttribute("questionList", questionList);
		model.addAttribute("exampleList", exampleList);
		model.addAttribute("test", test);
		model.addAttribute("questionCount", questionCount);
		
		return "test/paper";
	}
	@PostMapping("/student/test/paper")
	public String addPaper(HttpSession session, Paper paper, @RequestParam(value="questionCount", required=true) int questionCount) {
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		
		int row = paperService.addPaper(loginStudent.getStudentNo(), paper);
		log.debug("\u001B[31m"+row+"<-- addPaper row");
		
		String returnUrl = "redirect:/student/test/myTestList";
		if(row != questionCount) { // 문항 수만큼 답
			returnUrl = "redirect:/student/test/paper";
		}
		return returnUrl;
	}
}