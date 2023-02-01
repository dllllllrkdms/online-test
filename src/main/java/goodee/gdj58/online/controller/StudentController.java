package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Student;

@Controller
public class StudentController {
	@Autowired StudentService studentService;
	
	
	/*
	 * 사원 기능  
	 */
	// 학생 삭제 
	@GetMapping("/student/removeStudent")
	public String removeStudent(HttpSession session, @RequestParam("studentNo") int studentNo) {
		int row = studentService.removeStudent(studentNo);
		System.out.println(row+"<-- removeStudent row, StudentController");
		return "redirect:/student/studentList";
	}
	
	// 학생 목록 출력 
	@GetMapping("/student/studentList")
	public String studentList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue="1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowperPage) {
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) { 
			return "redirect:/employee/login";
		} 
		List<Student> list = studentService.getStudentList(currentPage, rowperPage);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "student/studentList";
	}
}
