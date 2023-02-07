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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// 로그인 폼
	@GetMapping("/loginEmp")
	public String loginEmp(HttpSession session) { 
		session.invalidate();
		return "employee/login";
	}
	// 로그인 action 
	@PostMapping("/loginEmp")
	public String loginEmp(HttpSession session, Employee employee) {
		Employee resultEmp = employeeService.login(employee);
		System.out.println(resultEmp+"<-- loginEmp resultEmp, EmployeeController");
		if(resultEmp==null) { // 로그인 실패
			return "redirect:/loginEmp";
		}
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/index";
	}
	
	/*
	 * 로그인 후에 사용가능한 기능
	 */
	// 인덱스
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	// 비밀번호 수정 폼 
	@GetMapping("employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session) {
		return "employee/modifyEmpPw";
	}
	
	// 비밀번호 수정 action
	@PostMapping("employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session, @RequestParam(value="oldPw", required=true) String oldPw, @RequestParam(value="newPw", required=true) String newPw) { // required=true : null이 들어오지 못함. 기본값
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		int row = employeeService.modifyEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		System.out.println(row+"<-- modifyEmpPw row, EmployeeController"); 
		return "redirect:/employee/empList";
	}
	
	// 사원 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(HttpSession session, @RequestParam("empNo") int empNo) {
		int row = employeeService.removeEmployee(empNo); 
		System.out.println(row+"<-- deleteEmp row, EmployeeController"); 
		return "redirect:/employee/empList";
	}
	
	// 사원 등록 폼
	@GetMapping("/employee/addEmp")
	public String addEmp(HttpSession session) {
		return "employee/addEmp"; // forward
	}
	
	// 사원 등록 action
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session, Model model, Employee employee) { // 오버로딩
		// 아이디 중복 확인
		String idCheck = idService.getIdCheck(employee.getEmpId());
		System.out.println(idCheck);
		if(idCheck!=null) {
			model.addAttribute("errorMsg", "중복된 아이디입니다.");
			return "employee/addEmp";
		}
		int row = employeeService.addEmployee(employee); // row == 1 이면 입력성공
		System.out.println(row+"<-- addEmp row, EmployeeController"); 
		
		String msg = "등록을 실패했습니다. 다시 시도해주세요.";
		if(row==1) { // 가입 성공
			msg = "가입을 환영합니다. 로그인 후 이용해주세요.";
			model.addAttribute("msg", msg);
			return "redirect:/employee/empList";
		}
		model.addAttribute("errorMsg", msg);
		return "employee/addEmp"; // sendRedirect , CM -> C 
	}
	
	// 사원리스트 출력
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model
						, @RequestParam(value="currentPage", defaultValue="1") int currentPage
						, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
						, @RequestParam(value="searchWord", defaultValue="") String searchWord) { 
	/*
	 * @RequestParam(value="x") int y -> int y = request.getParameter("x") 
	 * defaultValue : null일 시 기본값 설정
	 * 다양한 타입의 parameter 받기 가능 (자동으로 형 변환됨)
	 */
		log.debug("\u001B[31m"+currentPage+"<--empList currentPage, EmployeeController");
		log.debug("\u001B[31m"+rowPerPage+"<--empList rowPerPage, EmployeeController");
		log.debug("\u001B[31m"+searchWord+"<--empList searchWord, EmployeeController");
		
		// 페이징 
		int count = employeeService.getEmployeeCount(searchWord); // 사원 수 
		log.debug("\u001B[31m"+count+"<--empList count, EmployeeController");
		if(count==0) {
			String searchMsg = "검색결과가 없습니다.";
			if(searchWord.equals("")) {
				searchMsg = "등록된 사원이 없습니다.";
			}
			model.addAttribute("searchWord", searchWord);
			model.addAttribute("searchMsg", searchMsg);
			return "employee/empList";
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
		
		log.debug("\u001B[31m"+lastPage+"<--empList lastPage, EmployeeController");
		log.debug("\u001B[31m"+startPage+"<--empList startPage, EmployeeController");
		log.debug("\u001B[31m"+endPage+"<--empList endPage, EmployeeController");
		
		// model
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage, searchWord); 
		model.addAttribute("list", list); // request.setAttribute("list", list); 와 동일한 결과. ModelAndView 타입으로 반환하는 방법도 있음.
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		
		return "employee/empList"; // String 메서드의 리턴값은 뷰 이름 -> /WEB-INF/view/online-test/test.jsp 
	}
}
