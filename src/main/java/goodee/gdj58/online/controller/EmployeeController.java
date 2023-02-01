package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession; // servlet session 사용

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.vo.Employee;

@Controller
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// 로그인 폼
	@GetMapping("/employee/login")
	public String loginEmp(HttpSession session) {
		// 이미 로그인 상태라면
		if(session.getAttribute("loginEmp")!=null) { 
			return "redirect:/employee/empList";
		} 
		return "employee/login";
	}
	
	/*
	 * 로그인 후에 사용가능한 기능
	 */
	
	// 로그인 action 
	@PostMapping("/employee/login")
	public String loginEmp(HttpSession session, Employee employee) {
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")!=null) { 
			return "redirect:/employee/empList";
		} 
		
		Employee resultEmp = employeeService.login(employee);
		System.out.println(resultEmp+"<-- login resultEmp, EmployeeController");
		if(resultEmp==null) { // 로그인 실패
			return "employee/login";
		}
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/employee/empList";
	}
	
	// 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/login";
	}
	
	// 비밀번호 수정 폼 
	@GetMapping("employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session) {
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) { 
			return "redirect:/employee/login";
		} 
		return "employee/modifyEmpPw";
	}
	
	// 비밀번호 수정 action
	@PostMapping("employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session, @RequestParam(value="oldPw", required=true) String oldPw, @RequestParam(value="newPw", required=true) String newPw) { // required=true : null이 들어오지 못함. 기본값
		// 로그인 유효성 검사
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp==null) { 
			return "redirect:/employee/login";
		} 
		int row = employeeService.modifyEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		System.out.println(row+"<-- modifyEmpPw row, EmployeeController"); 
		return "redirect:/employee/empList";
	}
	
	// 사원 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(HttpSession session, @RequestParam("empNo") int empNo) {
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) { 
			return "redirect:/employee/login";
		} 
		
		int row = employeeService.removeEmployee(empNo); 
		System.out.println(row+"<-- deleteEmp row, EmployeeController"); 
		return "redirect:/employee/empList";
	}
	
	// 사원 등록 폼
	@GetMapping("/employee/addEmp")
	public String addEmp(HttpSession session) {
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) { 
			return "redirect:/employee/login";
		} 
		return "employee/addEmp"; // forward
	}
	
	// 사원 등록 action
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session, Model model, Employee employee) { // 오버로딩
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) { 
			return "redirect:/employee/login";
		} 
		
		// 아이디 중복 확인
		String idCheck = idService.getIdCheck(employee.getEmpId());
		System.out.println(idCheck);
		if(idCheck!=null) {
			model.addAttribute("errorMsg", "중복된 아이디입니다.");
			return "employee/addEmp";
		}
		int row = employeeService.addEmployee(employee); // row == 1 이면 입력성공
		System.out.println(row+"<-- addEmp row, EmployeeController"); 
		if(row==1) {
			model.addAttribute("errorMsg", "등록을 실패했습니다. 다시 시도해주세요.");
			return "employee/addEmp";
		}
		return "redirect:/employee/empList"; // sendRedirect , CM -> C 
	}
	
	// 사원리스트 출력
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) { 
	/*
	 * @RequestParam(value="x") int y -> int y = request.getParameter("x") 
	 * defaultValue : null일 시 기본값 설정
	 * 다양한 타입의 parameter 받기 가능 (자동으로 형 변환됨)
	 */
		// 로그인 유효성 검사
		if(session.getAttribute("loginEmp")==null) { 
			return "redirect:/employee/login";
		} 
		
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage); // model
		model.addAttribute("list", list); // request.setAttribute("list", list); 와 동일한 결과. ModelAndView 타입으로 반환하는 방법도 있음.
		model.addAttribute("currentPage", currentPage);
		return "employee/empList"; // String 메서드의 리턴값은 뷰 이름 -> /WEB-INF/view/online-test/test.jsp 
	}
}
