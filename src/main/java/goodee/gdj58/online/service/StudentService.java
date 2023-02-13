package goodee.gdj58.online.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.StudentMapper;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;
	
	// 비밀번호 변경
	public int modifyStudentPw(int studentNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("studentNo", studentNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return studentMapper.updateStudentPw(paramMap);
	}
	
	// 회원가입
	public int addStudent(Student student) {
		return studentMapper.insertStudent(student);
	}
	
	// 로그인
	public Student login(Student student) {
		return studentMapper.login(student);
	}
	
	// 학생 수 
	public int getStudentCount(String searchWord) {
		return studentMapper.selectStudentCount(searchWord);
	}
	
	// student 삭제 
	public int removeStudent(int studentNo) {
		return studentMapper.deleteStudent(studentNo);
	}
	
	// student list 출력 
	public Map<String, Object> getStudentList(int currentPage, int rowPerPage, String searchWord) {
		
		// 페이징
		int count = studentMapper.selectStudentCount(searchWord);
		log.debug("\u001B[31m"+count+"<--getStudentList count");
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
		
		
		log.debug("\u001B[31m"+lastPage+"<--getStudentList lastPage");
		log.debug("\u001B[31m"+startPage+"<--getStudentList startPage");
		log.debug("\u001B[31m"+endPage+"<--getStudentList endPage");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("beginRow", beginRow);
		paramMap.put("searchWord", searchWord);
		paramMap.put("currentPage", currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("startPage", startPage);
		paramMap.put("endPage", endPage);
		paramMap.put("lastPage", lastPage);
		
		List<Student> studentList = studentMapper.selectStudentList(paramMap);
		
		log.debug("\u001B[31m"+studentList+"<--getStudentList studentList");
		paramMap.put("list", studentList);
		
		return paramMap;
	}
}
