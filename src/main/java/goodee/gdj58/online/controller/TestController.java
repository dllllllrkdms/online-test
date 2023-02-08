package goodee.gdj58.online.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.ExampleService;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
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
	
	// 상세보기 - 강사, 학생
	@GetMapping(value={"/teacher/test/testOne", "/student/test/testOne"}) // 다중 매핑 
	public String getTestOne(Model model, @RequestParam(value="testNo", required=true) int testNo) {
		log.debug("\u001B[31m"+testNo+"<-- modifyTest testNo");
		
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
		model.addAttribute("test", test);
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
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);
		int date = today.get(Calendar.DATE)+1; // 내일 일자부터 시험 등록가능
		
		String monthFormat = month+1 < 10 ? "0" + String.valueOf(month+1) : String.valueOf(month+1);
		String dateFormat = date < 10 ? "0" + String.valueOf(date) : String.valueOf(date);

		
		String minDate = year + "-" + monthFormat + "-" + dateFormat;
		log.debug("\u001B[31m"+minDate+"<-- addTest minDate");
		
		model.addAttribute("minDate", minDate);
		
		return "test/addTest";
	}
	@PostMapping("/teacher/test/addTest")
	public String addTest(HttpSession session, Model model, Test test) {
		int row = testService.addTest(test);
		log.debug("\u001B[31m"+row+"<-- addTest row");
		
		String returnUrl = "redirect:/teacher/test/testOne?testNo=";
		if(row != 1) {
			returnUrl = "test/addTest";
			model.addAttribute("msg", "수정 실패했습니다. 다시 시도해주세요.");
			model.addAttribute("test", test);
		}
		return returnUrl;
	}
	
	// 리스트 출력
	@GetMapping("/teacher/test/testList") // 강사와 학생 볼 수 있게 다중 맵핑
	public String testList(HttpSession session, Model model
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
						, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<--testList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--testList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--testList searchWord");
		
		// 페이징
		int count = testService.getTestCount(searchWord);
		log.debug("\u001B[31m"+count+"<--testList count");
		if(count==0) {
			String searchMsg = "검색결과가 없습니다.";
			if(searchWord.equals("")) {
				searchMsg = "등록된 시험이 없습니다.";
			}
			model.addAttribute("searchWord", searchWord);
			model.addAttribute("searchMsg", searchMsg);
			return "test/testList";
		}
		int lastPage = count/rowPerPage;
		if(count%rowPerPage!=0) {
			lastPage+=1;
		}
		if(currentPage<1) {
			currentPage = 1;
		} else if(currentPage>lastPage) {
			currentPage = lastPage;
		}
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		if(startPage<1) {
			startPage = 1;
		} 
		if(endPage>lastPage) {
			endPage = lastPage;
		}
		
		log.debug("\u001B[31m"+lastPage+"<--testList lastPage");
		log.debug("\u001B[31m"+startPage+"<--testList startPage");
		log.debug("\u001B[31m"+endPage+"<--testList endPage");
		
		// model 
		List<Test> list = testService.getTestList(currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list); 
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		
		return "test/testList";
	}
}