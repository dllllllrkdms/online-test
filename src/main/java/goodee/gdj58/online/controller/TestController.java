package goodee.gdj58.online.controller;

import java.util.Collections;
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

import goodee.gdj58.online.service.DateCompare;
import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.PaperService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@Autowired DateCompare dateCompare;
	@Autowired TestService testService;
	@Autowired QuestionService questionService;
	@Autowired ExampleService exampleService;
	@Autowired PaperService paperService;
	
	// 시험 응시 페이지
	@GetMapping(value={"/{path:^teacher$|^student$}/paper"}) // 다중 매핑 
	public String getAnswer(Model model
							, @PathVariable String path
							, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+path+"<-- getTestOne path");
		log.debug("\u001B[31m"+testNo+"<-- getTestOne testNo");
		
		Test test = testService.getTestOne(testNo);
		
		// 학생은 지난 날짜 시험만 답안 보기 가능
		log.debug("\u001B[31m"+ dateCompare.todayCompare(test.getTestDate()) +"<-- getAnswer dateCompare");
		if(path.equals("student") && (dateCompare.todayCompare(test.getTestDate())) < 1) {
			return "redirect:/student/calendar";
		}
		
		List<Map<String,Object>> exampleList = exampleService.getAnswer(testNo);
		int questionCount = questionService.getQuestionCount(testNo);
		
		model.addAttribute("exampleList", exampleList);
		model.addAttribute("test", test);
		model.addAttribute("questionCount", questionCount);
		
		return "test/paper";
	}
	
	
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
	
	// 시험지 상세보기
	@GetMapping(value={"/{path:^teacher$|^student$}/test/testOne"}) // 다중 매핑 
	public String getTestOne(Model model,@PathVariable String path, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+path+"<-- getTestOne path");
		log.debug("\u001B[31m"+testNo+"<-- getTestOne testNo");
		
		Test test = testService.getTestOne(testNo);
		
		// 학생은 지난 날짜 시험만 상세보기 가능
		log.debug("\u001B[31m"+ dateCompare.todayCompare(test.getTestDate()) +"<-- getTestOne dateCompare");
		if(path.equals("student") && (dateCompare.todayCompare(test.getTestDate())) < 1) {
			return "redirect:/student/calendar";
		}
		
		List<Question> questionList = questionService.getQuestionList(testNo);
		List<Example> exampleList = exampleService.getExampleList(testNo);
		int questionCount = questionService.getQuestionCount(testNo);
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("exampleList", exampleList);
		model.addAttribute("test", test);
		model.addAttribute("questionCount", questionCount);
		
		return "test/testOne";
	}
	
	// test 관리 
	@GetMapping("/teacher/test/modifyTest")
	public String modifyTest(HttpSession session, Model model, @RequestParam(value="testNo", required=true) int testNo) {
		
		// 1. 로그인한 teacherId와 수정할 시험을 작성한 teacherId가 같은지 확인
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		
		Test test = testService.getTestOne(testNo); // 2. 시험 출력
		log.debug("\u001B[31m"+test.getTeacherId()+"<--modifyTest test.getTeacherId()");
		log.debug("\u001B[31m"+loginTeacher.getTeacherId()+"<--modifyTest loginTeacher.getTeacherId()");
		
		if(!test.getTeacherId().equals(loginTeacher.getTeacherId())) { 
			log.debug("\u001B[31m"+"<--modifyTest, 같지 않음");
			return "redirect:/teacher/test/testList";
		}
		
		// 2. 문제 출력
		List<Question> questionList = questionService.getQuestionList(testNo);
		
		model.addAttribute("test", test);
		model.addAttribute("questionList", questionList);
		
		return "test/modifyTest";
	}
	
	// test 수정 
	@PostMapping("/teacher/test/modifyTest")
	public String modifyTest(Model model, Test test) {
		
		int row = testService.modifyTest(test);
		log.debug("\u001B[31m"+row+"<-- modifyTest row");
		
		if(row != 1) {
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
		}
		return "redirect:/teacher/test/testList";
	}
	
	// test 추가
	@PostMapping("/teacher/test/addTest")
	public String addTest(HttpSession session, Model model, @RequestParam Map<String,String> map, Test test) {
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		test.setTeacherId(loginTeacher.getTeacherId());
		
		int row = testService.addTest(test);
		log.debug("\u001B[31m"+row+"<-- addTest row");
		
		if(row != 1) {
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
		}
		
		return "redirect:/teacher/test/testList";
	}
	
	// 지난 시험 출력(학생 : 지난 모든 시험 출력, 선생님 : 나의 지난 시험 출력)
	@GetMapping(value="/{path:^teacher$|^student$}/test/pastTestList") // 다중매핑 정규화 value="{변수명:정규식}" // ^ : 문자열의 시작을 표시 // $ : 문자열의 끝을 표시
	public String pastTestList(HttpSession session, Model model
						, @PathVariable String path // 변수명 받기 
						, @RequestParam(value="teacherId", defaultValue="") String teacherId
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
						, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		// 파라메타값 디버깅
		log.debug("\u001B[31m"+path+"<--pastTestList path");
		log.debug("\u001B[31m"+currentPage+"<--pastTestList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--pastTestList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--pastTestList searchWord");
		
		// 1. 경로 확인
		if(path.equals("student")) { // 학생이 들어왔을 경우 : null
			teacherId = "";
		}
		log.debug("\u001B[31m"+teacherId+"<--pastTestList teacherId");
		
		// 2. 오늘날짜
		String todayDate = dateCompare.getDate(0);
		log.debug("\u001B[31m"+todayDate+"<--pastTestList todayDate");	
		
		// 3. 페이징
		int pageSize = 5; // 한번에 보여질 페이지 개수 설정
		Page page = testService.getPastTestCount(currentPage, rowPerPage, pageSize, searchWord, todayDate, teacherId);
		log.debug("\u001B[31m"+page+"<--pastTestList page");
		model.addAttribute("page", page);
		
		// 3-1 카운트가 0이면 목록 출력 x
		if(page.getTotalCount() == 0) {
			model.addAttribute("testList", Collections.EMPTY_LIST); // 빈 list 반환
			return "test/pastTestList";
		}

		// 4. 목록 출력
		List<Test> testList = testService.getPastTestList(page);
		log.debug("\u001B[31m"+testList+"<--pastTestList testList");
		
		model.addAttribute("todayDate", todayDate);
		model.addAttribute("page", page);
		model.addAttribute("testList", testList);
		
		return "test/pastTestList";
	}
	
	// 예정 시험 출력
	@GetMapping(value="/teacher/test/testList") 
	public String testList(HttpSession session, Model model
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
						, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<--testList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--testList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--testList searchWord");
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		
		// 1. 날짜
		String todayDate = dateCompare.getDate(0);
		String tomorrow = dateCompare.getDate(1);
		log.debug("\u001B[31m"+todayDate+"<--testList todayDate");	
		log.debug("\u001B[31m"+tomorrow+"<--testList tomorrow");	
		
		// 2. 페이징
		int pageSize = 5;
		Page page = testService.getTestCount(currentPage, rowPerPage, pageSize, searchWord, todayDate, loginTeacher.getTeacherId());
		log.debug("\u001B[31m"+page+"<--testList page");
		model.addAttribute("page", page);
		
		// 2-1 카운트가 0이면 목록 출력 x
		if(page.getTotalCount() == 0) { 
			model.addAttribute("testList", Collections.EMPTY_LIST); // 빈 list 반환
			return "test/testList";
		}
		
		// 3. 목록 출력
		List<Test> testList = testService.getTestList(page);
		log.debug("\u001B[31m"+testList+"<--testList testList");
		
		model.addAttribute("minDate", tomorrow);
		model.addAttribute("testList", testList);
		
		return "test/testList";
	}
}