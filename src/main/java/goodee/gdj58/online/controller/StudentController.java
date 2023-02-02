package goodee.gdj58.online.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;

	
	/*
	 * 사원 기능  
	 */
	// 학생 삭제 
	@GetMapping("/employee/student/removeStudent")
	public String removeStudent(HttpSession session, @RequestParam("studentNo") int studentNo) {
		int row = studentService.removeStudent(studentNo);
		System.out.println(row+"<-- removeStudent row, StudentController");
		return "redirect:/employee/student/studentList";
	}
	
	// 학생 목록 출력 
	@GetMapping("/employee/student/studentList")
	public String studentList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue="1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<--studentList currentPage, StudentController");
		log.debug("\u001B[31m"+rowPerPage+"<--studentList rowPerPage, StudentController");
		log.debug("\u001B[31m"+searchWord+"<--studentList searchWord, StudentController");
		
		// 페이징 
		int count = studentService.getStudentCount(searchWord); // 학생 수
		log.debug("\u001B[31m"+count+"<--studentList count, StudentController");
		if(count==0) {
			String searchMsg = "검색결과가 없습니다.";
			if(searchWord.equals("")) {
				searchMsg = "등록된 학생이 없습니다.";
			}
			model.addAttribute("searchWord", searchWord);
			model.addAttribute("searchMsg", searchMsg);
			return "student/studentList";
		}
		int lastPage = count/rowPerPage;
		if(count%rowPerPage!=0) {
			lastPage+=1;
		}
		if(currentPage<1) {
			currentPage = 1;
		} 
		if(currentPage>lastPage) {
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
			
		log.debug("\u001B[31m"+lastPage+"<--studentList lastPage, StudentController");
		log.debug("\u001B[31m"+startPage+"<--studentList startPage, StudentController");
		log.debug("\u001B[31m"+endPage+"<--studentList endPage, StudentController");
		
		List<Student> list = studentService.getStudentList(currentPage, rowPerPage, searchWord);
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		
		return "student/studentList";
	}
}
