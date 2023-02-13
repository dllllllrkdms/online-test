package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession; // servlet session 사용

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.vo.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// 로그인 폼
	@GetMapping("/loginEmp")
	public String loginEmp(HttpSession session) { 
		session.invalidate(); // 강사나 학생으로 로그인한 상태에서 loginEmp를 들어올 때 두 세션을 사용하지 않기 위함.
		return "employee/login";
	}
	// 로그인 action 
	@PostMapping("/loginEmp")
	public String loginEmp(HttpSession session, RedirectAttributes rttr, Employee employee) {
		Employee resultEmp = employeeService.login(employee);
		log.debug("\u001B[31m"+resultEmp+"<-- loginEmp resultEmp");
		
		String msg = "아이디 또는 비밀번호를 잘못입력했습니다. 다시 시도해주세요.";
		String redirectUrl = "redirect:/loginEmp";
		if(resultEmp!=null) { // 로그인 성공
			msg = "";
			session.setAttribute("loginEmp", resultEmp);
			redirectUrl = "redirect:/index";
		}
		rttr.addFlashAttribute("msg", msg);
		return redirectUrl;
	}
	
	/*
	 * 로그인 후에 사용가능한 기능
	 */
	
	// 비밀번호 변경
	@PostMapping("employee/modifyPw")
	public String modifyPw(HttpSession session, RedirectAttributes rttr, @RequestParam(value="oldPw", required=true) String oldPw, @RequestParam(value="newPw", required=true) String newPw) { // required=true : null이 들어오지 못함. 기본값
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		int row = employeeService.modifyEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		log.debug("\u001B[31m"+row+"<-- modifyPw row");
		
		String msg = "변경실패했습니다. 다시 시도해주세요.";
		String returnUrl = "redirect:/employee/modifyPw";
		if(row == 1) { // 변경 성공
			msg = "변경되었습니다.";
			returnUrl = "redirect:/index";
		}
		rttr.addFlashAttribute("msg", msg);
		return returnUrl;
	}
	
	// 사원 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(@RequestParam("empNo") int empNo) {
		int row = employeeService.removeEmployee(empNo); 
		log.debug("\u001B[31m"+row+"<-- deleteEmp row");
		return "redirect:/employee/empList";
	}
	
	// 사원 등록 폼
	@GetMapping("/employee/addEmp")
	public String addEmp() {
		return "employee/addEmp"; // forward
	}
	
	// 사원 등록 action
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session, RedirectAttributes rttr, Employee employee) { // 오버로딩
	
		int row = employeeService.addEmployee(employee); // row == 1 이면 입력성공
		log.debug("\u001B[31m"+row+"<-- addEmp row");
		
		String msg = "등록을 실패했습니다. 다시 시도해주세요.";
		String returnUrl = "redirect:/employee/addEmp";
		if(row==1) { // 가입 성공
			msg = "";
			returnUrl = "redirect:/employee/empList";
		}
		rttr.addFlashAttribute("msg", msg);
		return returnUrl; // sendRedirect , CM -> C 
	}
	 
	//
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
						, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<--empList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--empList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--empList searchWord");
		
		Map<String, Object> map = employeeService.getEmployeeList(currentPage, rowPerPage, searchWord); 
		
		model.addAttribute("map", map);
		
		return "employee/empList";
	}
}
