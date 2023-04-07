package goodee.gdj58.online.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goodee.gdj58.online.mapper.TestScoreMapper;
import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.TestScore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestScoreService {
	@Autowired TestScoreMapper testScoreMapper;

	// 평균 개수
	public Page getAvgCount(int currentPage, int rowPerPage, int pageSize, String teacherId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("teacherId", teacherId);
		
		int count = testScoreMapper.selectAvgCount(m);
		
		Page page = new Page(count, currentPage, rowPerPage, pageSize, m);
		
		return page;
	}
	
	// 등록한 시험 평균 확인
	public List<Map<String, Object>> getScoreAvg(Page page){
		return testScoreMapper.selectAvgByTest(page);
	}
	
	// 학생 시험 응시 여부 확인 
	public TestScore getPaperCheckByStudent(int testNo, int studentNo){
		TestScore ts = new TestScore(testNo, studentNo, 0);
		return testScoreMapper.paperCheckByStudent(ts);
	}
	
	// 학생의 총 평균 점수
	public int selectAvgByStudent(int studentNo) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("studentNo", studentNo);
		
		return testScoreMapper.selectAvgByStudent(m);
	}
	
	// 학생이 응시한 테스트 목록, 점수 출력
	public List<Map<String, Object>> getTestScoreList(Page page){
		return testScoreMapper.selectTestScoreList(page);
	}

	// 학생이 응시한 테스트 페이징
	public Page getTestCountByStudent(int currentPage, int rowPerPage, int pageSize, int studentNo, String searchWord){
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("studentNo", studentNo);
		m.put("searchWord", searchWord);
		
		int count = testScoreMapper.selectTestCountByStudent(m);
		Page page = new Page(count, currentPage, rowPerPage, pageSize, m);
		
		return page;
	}
}
