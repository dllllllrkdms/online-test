package goodee.gdj58.online.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TeacherMapper;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TeacherService {
	@Autowired TeacherMapper teacherMapper;
	
	// 비밀번호 변경
	public int modifyTeacherPw(int teacherNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return teacherMapper.updateTeacherPw(paramMap);
	}
	
	// 회원가입 
	public int addTeacher(Teacher teacher) {
		return teacherMapper.insertTeacher(teacher);
	}
	// 로그인 
	public Teacher login(Teacher teacher) {
		return teacherMapper.login(teacher);
	}
	
	// 강사 수 
	public int getTeacherCount(String searchWord) {
		return teacherMapper.selectTeacherCount(searchWord);
	}
	
	// 강사 삭제 
	public int removeTeacher(int teacherNo) {
		return teacherMapper.deleteTeacher(teacherNo);
	}
	
	// 강사 목록 출력
	public Map<String, Object> getTeacherList(int currentPage, int rowPerPage, String searchWord) {
		
		// 페이징
		int count = teacherMapper.selectTeacherCount(searchWord);
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
		
		
		log.debug("\u001B[31m"+lastPage+"<--getTeacherList lastPage");
		log.debug("\u001B[31m"+startPage+"<--getTeacherList startPage");
		log.debug("\u001B[31m"+endPage+"<--getTeacherList endPage");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("beginRow", beginRow);
		paramMap.put("searchWord", searchWord);
		paramMap.put("currentPage", currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("startPage", startPage);
		paramMap.put("endPage", endPage);
		paramMap.put("lastPage", lastPage);
		
		List<Teacher> teacherList = teacherMapper.selectTeacherList(paramMap);
		
		log.debug("\u001B[31m"+teacherList+"<--getTeacherList teacherList");
		
		paramMap.put("list", teacherList);
		
		return paramMap;
	}
}
