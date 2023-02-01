package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Teacher;

@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	/*
	 * 사원기능 
	 */
	// 강사 삭제
	@GetMapping("/teacher/removeTeacher")
	public String removeTeacher(HttpSession session, int teacherNo) {
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) {
			return "redirect:/employee/loginEmp";
		}
		int row = teacherService.removeTeacher(teacherNo);
		System.out.println(row+"<--removeTeacher row, teacherService");
		return "redirect:/teacher/teacherList";
	}
	
	// 강사 목록 출력 
	@GetMapping("/teacher/teacherList")
	public String teacherList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue="1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) {
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) {
			return "redirect:/employee/loginEmp";
		}
		List<Teacher> list = teacherService.getTeacherList(currentPage, rowPerPage);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "teacher/teacherList";
	}
}