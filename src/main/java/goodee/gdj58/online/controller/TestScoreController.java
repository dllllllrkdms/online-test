package goodee.gdj58.online.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.TestScoreService;
import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestScoreController {
	@Autowired TestScoreService testScoreService;
	

	// 선생님 - 시험 별 학생들 평균점수 보기
	@GetMapping("/teacher/test/avg")
	public String getTestAvg(HttpSession session) {
		
		return "test/score/avgTest";
	}
	
	// 학생이 응시한 테스트 목록, 점수 출력
	@GetMapping("/student/test/myTestList")
	public String getTestListByStudent(HttpSession session, Model model
									, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
									, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage
									, @RequestParam(value="searchWord", defaultValue = "") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<-- myTestList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<-- myTestList rowPerPage");
		
		// 1. 로그인 학생 가져오기
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		log.debug("\u001B[31m"+studentNo+"<--myTestList studentNo");
		
		// 2. 페이징
		int pageSize = 3; 
		Page page = testScoreService.getTestCountByStudent(currentPage, rowPerPage, pageSize, studentNo, searchWord);
		log.debug("\u001B[31m"+page+"<--myTestList page");
		model.addAttribute("page", page);
		
		List<Map<String, Object>> testList;
		int avg = 0;
		// 3. 목록 출력
		// 카운트가 0이면 목록 출력 x
		if(page.getTotalCount() == 0) {
			testList = Collections.emptyList();
		} else {
			testList = testScoreService.getTestScoreList(page);
			avg = testScoreService.selectAvgByStudent(studentNo);
			log.debug("\u001B[31m"+testList+"<--myTestList testList");
			log.debug("\u001B[31m"+avg+"<--myTestList avg");
		}
		
		model.addAttribute("avg", avg);
		model.addAttribute("testList", testList);
		
		return "student/myTestList";
	}
	
	// 학생이 응시한 테스트 목록, 점수 출력 (페이징없이)
	@GetMapping("/student/score")
	public String getScoreList() {
		return "test/score/score";
	}
	
	
}
