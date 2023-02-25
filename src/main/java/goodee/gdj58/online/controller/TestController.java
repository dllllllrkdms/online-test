package goodee.gdj58.online.controller;

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
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Student;
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
	
	// 학생이 응시한 테스트 목록 출력
	@GetMapping("/student/test/myTestList")
	public String getTestListByStudent(HttpSession session, Model model, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
									,  @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage) {
		
		log.debug("\u001B[31m"+currentPage+"<-- myTestList currentPage");
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		
		Map<String, Object> map = testService.getTestListByStudent(currentPage, rowPerPage, loginStudent.getStudentNo());
		
		model.addAttribute("map", map);
		
		return "student/myTestList";
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
	
	// 상세보기
	@GetMapping(value={"/{path:^teacher$|^student$}/test/testOne"}) // 다중 매핑 
	public String getTestOne(Model model,@PathVariable String path, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+path+"<-- getTestOne path");
		log.debug("\u001B[31m"+testNo+"<-- getTestOne testNo");
		
		Test test = testService.getTestOne(testNo);
		
		
		// 학생은 지난 날짜 시험만 상세보기 가능
		if(path.equals("student") && (dateCompare.todayCompare(test.getTestDate()))!= 1) {
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

	// test 수정 
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
	@PostMapping("/teacher/test/addTest")
	public String addTest(HttpSession session, Model model, @RequestParam Map<String,String> map, Test test) {
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		test.setTeacherId(loginTeacher.getTeacherId());
		
		int row = testService.addTest(test);
		log.debug("\u001B[31m"+row+"<-- addTest row");
		
		String returnUrl = null;
		if(row != 1) {
			returnUrl = "test/addTest";
			model.addAttribute("test", test);
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
		} else {
			returnUrl = "redirect:/teacher/test/testList";
		}
		
		return returnUrl;
	}
	
	// 지난 시험 출력
	@GetMapping(value="/{path:^teacher$|^student$}/test/pastTestList") // 다중매핑 정규화 value="{변수명:정규식}" // ^ : 문자열의 시작을 표시 // $ : 문자열의 끝을 표시
	public String pastTestList(HttpSession session, Model model
						, @PathVariable String path // 변수명 받기
						, @RequestParam(value="teacherId", defaultValue="") String teacherId
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
						, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+path+"<--testList path");
		log.debug("\u001B[31m"+currentPage+"<--testList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--testList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--testList searchWord");

		// 오늘날짜
		String todayDate = dateCompare.getDate(0);
		
		log.debug("\u001B[31m"+todayDate+"<--testList todayDate");	
		
		Map<String, Object> map = testService.getPastTestList(currentPage, rowPerPage, searchWord, todayDate, teacherId);
		
		model.addAttribute("map", map);
		
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
		
		String teacherId = null;
		
		// 날짜
		String todayDate = dateCompare.getDate(0);
		String tomorrow = dateCompare.getDate(1);
		
		
		log.debug("\u001B[31m"+todayDate+"<--testList todayDate");	
		
		Map<String, Object> map = testService.getTestList(currentPage, rowPerPage, searchWord, todayDate, teacherId);
		
		model.addAttribute("minDate", tomorrow);
		model.addAttribute("map", map);
		
		return "test/testList";
	}
}