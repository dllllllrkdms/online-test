package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Employee;

@Mapper // spring이 부팅되면 @Mapper : interface의 구현체(EmployeeMapperClass)를 생성함
public interface EmployeeMapper { 
	// @Select("") @Insert("") 등 쿼리 작성 --> mybatis xml페이지에
	
	int selectEmployeeCount(String searchWord);
	int updateEmployeePw(Map<String, Object> paramMap);
	Employee login(Employee employee);
	int deleteEmployee(int empNo);
	int insertEmployee(Employee employee);
	List<Employee> selectEmployeeList(Map<String, Object> paramMap); // 추상메서드 생성
}