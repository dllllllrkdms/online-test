package goodee.gdj58.online.controller;

import java.util.List;

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
	@GetMapping("employee/modifyPw")
	public String modifyPw() {
		return "employee/modifyPw";
	}
	
	// 비밀번호 수정 action
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
		// 아이디 중복 확인
		String idCheck = idService.getIdCheck(employee.getEmpId());
		log.debug("\u001B[31m"+idCheck+"<-- addEmp idCheck");
		
		if(idCheck!=null) {
			rttr.addFlashAttribute("msg", "중복된 아이디입니다.");
			return "redirect:/employee/addEmp";
		}
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
