package goodee.gdj58.online.service;


import java.util.Collections;
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
	
	// 학생이 응시한 테스트 목록만 출력
	public Map<String, Object> getTestListByStudent(int currentPage, int rowPerPage, int studentNo){
		// 페이징
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("studentNo", studentNo);
		
		Map<String, Object> map = testMapper.selectTestCountByStudent(paramMap);
		int count = Integer.parseInt(String.valueOf(map.get("count")));
		log.debug("\u001B[31m"+count+"<--getTestListByStudent count");
		log.debug("\u001B[31m"+map.get("testNo")+"<--getTestListByStudent testNo");
		
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
		
		log.debug("\u001B[31m"+currentPage+"<--getTestList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--getTestList rowPerPage");
		
		log.debug("\u001B[31m"+lastPage+"<--getTestList lastPage");
		log.debug("\u001B[31m"+startPage+"<--getTestList startPage");
		log.debug("\u001B[31m"+endPage+"<--getTestList endPage");
		
		paramMap.put("beginRow", beginRow);
		paramMap.put("currentPage", currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("startPage", startPage);
		paramMap.put("endPage", endPage);
		paramMap.put("lastPage", lastPage);
		paramMap.put("testNo", map.get("testNo"));
		
		List<Map<String, Object>> testList = testMapper.selectTestListByStudent(paramMap);
		for(Map<String, Object> m : testList) {
			m.put("score", paperService.getPaperScore((int)m.get("testNo"), studentNo));
		}
		paramMap.put("testList", testList);
		
		return paramMap;
	}
	// 지난 시험 출력 
	public Map<String, Object> getPastTestList(int currentPage, int rowPerPage, String searchWord, String todayDate, String teacherId){
		
		// 페이징
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchWord", searchWord);
		paramMap.put("todayDate", todayDate);
		paramMap.put("teacherId", teacherId);
		
		// test 수 조회
		int count = testMapper.selectTestCount(paramMap);
		log.debug("\u001B[31m"+count+"<--testList count");
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
		
		log.debug("\u001B[31m"+currentPage+"<--getTestList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--getTestList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--getTestList searchWord");
		
		log.debug("\u001B[31m"+lastPage+"<--getTestList lastPage");
		log.debug("\u001B[31m"+startPage+"<--getTestList startPage");
		log.debug("\u001B[31m"+endPage+"<--getTestList endPage");
		
		
		paramMap.put("beginRow", beginRow);
		paramMap.put("currentPage", currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("startPage", startPage);
		paramMap.put("endPage", endPage);
		paramMap.put("lastPage", lastPage);
		
		List<Test> testList = testMapper.selectPastTestList(paramMap);
		
		paramMap.put("testList", testList);
		
		return paramMap;
	}
	
	// testList 
	public Map<String, Object> getTestList(int currentPage, int rowPerPage, String searchWord, String todayDate, String teacherId){
		
		// 페이징
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchWord", searchWord);
		paramMap.put("todayDate", todayDate);
		paramMap.put("teacherId", teacherId);
		
		// test 수 조회
		int count = testMapper.selectTestCount(paramMap);
		log.debug("\u001B[31m"+count+"<--testList count");
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
		
		log.debug("\u001B[31m"+currentPage+"<--getTestList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--getTestList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--getTestList searchWord");
		
		log.debug("\u001B[31m"+lastPage+"<--getTestList lastPage");
		log.debug("\u001B[31m"+startPage+"<--getTestList startPage");
		log.debug("\u001B[31m"+endPage+"<--getTestList endPage");
		
		
		paramMap.put("beginRow", beginRow);
		paramMap.put("currentPage", currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("startPage", startPage);
		paramMap.put("endPage", endPage);
		paramMap.put("lastPage", lastPage);
		
		List<Test> testList = testMapper.selectTestList(paramMap);
		
		paramMap.put("testList", testList);
		
		return paramMap;
	}
}
