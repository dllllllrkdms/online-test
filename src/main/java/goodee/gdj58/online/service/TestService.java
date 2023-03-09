package goodee.gdj58.online.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TestService {
	@Autowired PaperService paperService;
	@Autowired TestMapper testMapper;
	@Autowired QuestionMapper questionMapper; 
	
	// 시험 삭제  트랜잭션 처리 필요.
	public int removeTest(int testNo) {
		return testMapper.deleteTest(testNo);
	}
	
	// 상세보기
	public Test getTestOne(int testNo) {
		return testMapper.selectTestOne(testNo);
	}
	
	// test 수정
	public int modifyTest(Test test) {
		return testMapper.updateTest(test);
	}
	
	// test 추가
	public int addTest(Test test) {
		return testMapper.insertTest(test);
	}

	// 지난 시험 페이징
	public Page getPastTestCount(int currentPage, int rowPerPage, int pageSize, String searchWord, String todayDate, String teacherId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("searchWord", searchWord);
		m.put("todayDate", todayDate);
		m.put("teacherId", teacherId);
		
		int count = testMapper.selectPastTestCount(m);
		
		Page page = new Page(count, currentPage, rowPerPage, pageSize, m);
		
		return page;
	}
	// 지난 시험 출력 
	public List<Test> getPastTestList(Page page){
		return testMapper.selectPastTestList(page);
		
	}
	// 예정 test 페이징
	public Page getTestCount(int currentPage, int rowPerPage, int pageSize, String searchWord, String todayDate, String teacherId){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("searchWord", searchWord);
		m.put("todayDate", todayDate);
		m.put("teacherId", teacherId);
		
		int count = testMapper.selectTestCount(m);
		
		Page page = new Page(count, currentPage,rowPerPage, pageSize, m);
		
		return page;
	}
	
	// testList 
	public List<Test> getTestList(Page page){
		return testMapper.selectTestList(page);
	}
	
	// 달력 - 모두 출력
	public List<Test> getAllTestList(){
		return testMapper.selectAllTestList();
	}
}
