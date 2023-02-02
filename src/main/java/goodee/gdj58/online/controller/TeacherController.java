package goodee.gdj58.online.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	
	
	/*
	 * 사원기능 
	 */
	// 강사 삭제
	@GetMapping("/employee/teacher/removeTeacher")
	public String removeTeacher(HttpSession session, int teacherNo) {
		int row = teacherService.removeTeacher(teacherNo);
		log.debug("\u001B[31m"+row+"<--removeTeacher row, TeacherController");
		return "redirect:/employee/teacher/teacherList";
	}
	
	// 강사 목록 출력 
	@GetMapping("/employee/teacher/teacherList")
	public String teacherList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue="1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<--teacherList currentPage, TeacherController");
		log.debug("\u001B[31m"+rowPerPage+"<--teacherList rowPerPage, TeacherController");
		log.debug("\u001B[31m"+searchWord+"<--teacherList searchWord, TeacherController");
		
		// 페이징 
		int count = teacherService.getTeacherCount(searchWord); // 강사 수
		log.debug("\u001B[31m"+count+"<--teacherList count, TeacherController");
		if(count==0) {
			String searchMsg = "검색결과가 없습니다.";
			if(searchWord.equals("")) {
				searchMsg = "등록된 강사가 없습니다.";
			}
			model.addAttribute("searchWord", searchWord);
			model.addAttribute("searchMsg", searchMsg);
			return "teacher/teacherList";
		}
		int lastPage = count/rowPerPage;
		if(count%rowPerPage!=0) {
			lastPage+=1;
		}
		if(currentPage<1) {
			currentPage = 1;
		} 
		if(currentPage>=lastPage) {
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
		
		log.debug("\u001B[31m"+lastPage+"<--teacherList lastPage, TeacherController");
		log.debug("\u001B[31m"+startPage+"<--teacherList startPage, TeacherController");
		log.debug("\u001B[31m"+endPage+"<--teacherList endPage, TeacherController");
		
		
		List<Teacher> list = teacherService.getTeacherList(currentPage, rowPerPage, searchWord);
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		
		return "teacher/teacherList";
	}
}