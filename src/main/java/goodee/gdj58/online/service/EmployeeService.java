package goodee.gdj58.online.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.EmployeeMapper;
import goodee.gdj58.online.vo.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional // db단이 아닌 spring단에서 commit과 rollback을 시킴
public class EmployeeService {
	@Autowired EmployeeMapper employeeMapper; // 외부에서 객체를 생성해 주입 -> DI
	

	// 사원 수
	public int getEmployeeCount(String searchWord) {
		return employeeMapper.selectEmployeeCount(searchWord);
	}
	
	// emp 비밀번호 수정
	public int modifyEmployeePw(int empNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("empNo", empNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return employeeMapper.updateEmployeePw(paramMap);
	}
	
	// emp 로그인
	public Employee login(Employee employee) {
		return employeeMapper.login(employee);
	}
	
	// emp 삭제
	public int removeEmployee(int empNo) {
		return employeeMapper.deleteEmployee(empNo);
	}
	
	// emp 추가
	public int addEmployee(Employee employee) {
		return employeeMapper.insertEmployee(employee);
	}
	
	// empList 출력
	public Map<String, Object> getEmployeeList(int currentPage, int rowPerPage, String searchWord) {
		
		// 페이징
		
		// test 수 조회
		int count = employeeMapper.selectEmployeeCount(searchWord);
		log.debug("\u001B[31m"+count+"<--getEmployeeList count");
		if(count==0) {
			return Collections.emptyMap(); // 비어있는 map 반환
		}
		int lastPage = count/rowPerPage; // 마지막 목록페이지
		if(count%rowPerPage!=0) {
			lastPage+=1;
		}
		if(currentPage<1) {
			currentPage = 1;
		} 
		if(currentPage>lastPage) {
			currentPage = lastPage;
		}
		
		int beginRow = (currentPage-1)*rowPerPage;
		int pageSize = 10;
		int startPage = (currentPage-1)/pageSize*pageSize + 1; 
		int endPage = startPage + pageSize - 1;
		if(startPage<1) {
			startPage = 1;
		} 
		if(endPage>lastPage) {
			endPage = lastPage;
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		List<Employee> list = employeeMapper.selectEmployeeList(paramMap);
		
		paramMap.put("startPage", startPage);
		paramMap.put("endPage", endPage);
		paramMap.put("lastPage", lastPage);
		paramMap.put("currentPage", currentPage);
		paramMap.put("empList", list);
		
		return paramMap;
	}
}
