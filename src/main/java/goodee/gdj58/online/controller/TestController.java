package goodee.gdj58.online.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@Autowired TestService testService;
	@Autowired QuestionService questionService;
	@Autowired ExampleService exampleService;
	
	// test 삭제 
	@GetMapping("/teacher/test/removeTest")
	public String removeTest(RedirectAttributes rttr, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+testNo+"<-- removeTest testNo");
		
		int row = testService.removeTest(testNo);
		log.debug("\u001B[31m"+row+"<-- removeTest testNo");
		
		String msg = "삭제를 실패했습니다. 다시 시도해주세요.";
		if(row == 1) {
			msg = "삭제되었습니다.";
		}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/teacher/test/testList";
	}
	
	// 상세보기 
	@GetMapping(value={"/teacher/test/testOne", "/student/test/testOne"}) // 다중 매핑 
	public String getTestOne(Model model, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+testNo+"<-- getTestOne testNo");
		
		Test test = testService.getTestOne(testNo);
		List<Question> questionList = questionService.getQuestionList(testNo);
		List<Example> exampleList = exampleService.getExampleList(testNo);
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("exampleList", exampleList);
		model.addAttribute("test", test);
		
		return "test/testOne";
	}
	
	// test 수정
	@GetMapping("/teacher/test/modifyTest")
	public String modifyTest(Model model, @RequestParam(value="testNo", required=true) int testNo) { 
		log.debug("\u001B[31m"+testNo+"<-- modifyTest testNo");
		Test test = testService.getTestOne(testNo);
		List<Question> questionList = questionService.getQuestionList(testNo);
		List<Example> exampleList = exampleService.getExampleList(testNo);
		
		model.addAttribute("test", test);
		model.addAttribute("questionList", questionList);
		model.addAttribute("exampleList", exampleList);
		
		return "test/modifyTest";
	}
	@PostMapping("/teacher/test/modifyTest")
	public String modifyTest(Model model, Test test) {
		int row = testService.modifyTest(test);
		log.debug("\u001B[31m"+row+"<-- modifyTest row");
		
		String returnUrl = "redirect:/teacher/test/testOne?testNo="+test.getTestNo();
		if(row != 1) {
			returnUrl = "test/modifyTest";
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
			model.addAttribute("test", test);
		}
		return returnUrl;
	}
	
	// test 추가
	@GetMapping("/teacher/test/addTest")
	public String addTest(Model model) {
		// 오늘날짜
		Calendar today = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		today.add(Calendar.DATE, +1); // 내일부터 등록가능
		String minDate = sdf.format(today.getTime());
		
		log.debug("\u001B[31m"+minDate+"<-- addTest minDate");
		
		model.addAttribute("minDate", minDate);
		
		return "teacher/test/addTest";
	}
	@PostMapping("/teacher/test/addTest")
	public String addTest(HttpSession session, Model model, @RequestParam Map<String,String> map, Test test) {
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		test.setTeacherId(loginTeacher.getTeacherId());
		
		int row = testService.addTest(test);
		log.debug("\u001B[31m"+row+"<-- addTest row");
		
		String returnUrl = null;
		if(row != 1) {
			returnUrl = "test/addTest";
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
		} else {
			returnUrl = "question/addQuestion";
			model.addAttribute("map", map);
		}
		
		model.addAttribute("test", test);
		return returnUrl;
	}
	
	// teacher : 본인이 작성한 시험 출력, student : 지난 시험 출력
	@GetMapping(value="/{path:^teacher$|^student$}/test/testList") // 다중매핑 정규화 value="{변수명:정규식}" // ^ : 문자열의 시작을 표시 // $ : 문자열의 끝을 표시
	public String testList(HttpSession session, Model model
						, @PathVariable String path // 변수명 받기
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
						, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+path+"<--testList path");
		log.debug("\u001B[31m"+currentPage+"<--testList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--testList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--testList searchWord");
		
		String teacherId = null;
		
		// 오늘날짜
		String paramTodayDate = null;
		Calendar today = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String todayDate = sdf.format(today.getTime());
		
		if(path.equals("student")) {
			paramTodayDate = todayDate;
		} else if(path.equals("teacher")){
			teacherId = ((Teacher)session.getAttribute("loginTeacher")).getTeacherId();
		}
		
		log.debug("\u001B[31m"+paramTodayDate+"<--testList paramTodayDate");
		log.debug("\u001B[31m"+todayDate+"<--testList todayDate");	
		
		Map<String, Object> map = testService.getTestList(currentPage, rowPerPage, searchWord, paramTodayDate, teacherId);
		
		model.addAttribute("todayDate", todayDate);
		model.addAttribute("map", map);
		
		return "test/testList";
	}
}