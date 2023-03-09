package goodee.gdj58.online.restController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.TestScoreService;
import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.TestScore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestScoreRestController {
	@Autowired TestScoreService testScoreService;
	
	@GetMapping("/student/paperCheck")
	public TestScore paperCheck(HttpSession session, @RequestParam(value="testNo") int testNo) {
		
		log.debug("\u001B[31m"+testNo+"<--paperCheck testNo");
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		
		return testScoreService.getPaperCheckByStudent(testNo, loginStudent.getStudentNo());
	}
	
	@PostMapping("/student/score")
	public List<Map<String, Object>> score(HttpSession session){
		// 1. 로그인한 학생 정보
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		
		// 2. 페이징
		int pageSize = 1;
		int rowPerPage = 5;
		Page page = testScoreService.getTestCountByStudent(pageSize, rowPerPage, pageSize, loginStudent.getStudentNo(), null);
		
		// 3. 모델 
		List<Map<String, Object>> list =testScoreService.getTestScoreList(page);
		log.debug("\u001B[31m"+list+"<--score list");
		
		return list; 
	}
	
	@PostMapping("/teacher/avg")
	public List<Map<String, Object>> avg(HttpSession session
										, @RequestParam(value="currentPage", defaultValue="1") int currentPage
										, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
										, @RequestParam(value="pageSize", defaultValue="1") int pageSize){
		// 1. 로그인한 강사 정보
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		
		// 2. 페이징
		pageSize = 1;
		rowPerPage = 5;
		Page page = testScoreService.getAvgCount(currentPage, rowPerPage, pageSize, loginTeacher.getTeacherId());
		log.debug("\u001B[31m"+page+"<--avg page");
		
		// 2-1 count == 0 이면 return 
		if(page.getTotalCount() == 0) {
			return Collections.EMPTY_LIST;
		}
				
		// 3. 모델 
		List<Map<String, Object>> list =testScoreService.getScoreAvg(page);
		log.debug("\u001B[31m"+list+"<--avg list");
		return list; 
	}
}
