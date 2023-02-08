package goodee.gdj58.online.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class TestService {
	@Autowired TestMapper testMapper;
	@Autowired QuestionMapper questionMapper;
	
	// 시험 삭제
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
	
	// test 수
	public int getTestCount(String searchWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// test_title, test_date 검색 --> 수정
		paramMap.put("searchWord", searchWord);
		return testMapper.selectTestCount(paramMap);
	}

	// testList 출력 - 강사, 학생
	public List<Test> getTestList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return testMapper.selectTestList(paramMap);
	}
}
