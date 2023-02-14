package goodee.gdj58.online.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.PaperService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaperController {
	@Autowired QuestionService questionService;
	@Autowired PaperService paperService;
	
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
	@PostMapping("/student/test/paper")
	public String addPaper(HttpSession session, Paper paper) {
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		
		int row = paperService.addPaper(loginStudent.getStudentNo(), paper);
		log.debug("\u001B[31m"+row+"<-- addPaper row");
		
		String returnUrl = "redirect:/student/test/testList";
		if(row != 2) { // 문항 수만큼 답
			returnUrl = "redirect:/student/test/paper";
		}
		return returnUrl;
	}
}