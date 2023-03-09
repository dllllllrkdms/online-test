package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.PaperMapper;
import goodee.gdj58.online.mapper.TestScoreMapper;
import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.TestScore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PaperService {
	@Autowired private PaperMapper paperMapper;
	@Autowired private QuestionService questionService;
	@Autowired private TestScoreMapper testScoreMapper;
	
	
	// 응시자 수
	public int getPaperCount(int testNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		int count = paperMapper.selectPaperCount(testNo);
		return count;
	}
	
	// 답안 입력
	public int addPaper(int studentNo, Paper paper, int testNo) {
		int row = 0; // 리턴할 변수
		int questionCount = questionService.getQuestionCount(testNo); // 문항 수
		
		List<Paper> paperList = paper.getPaperList();
		if(paperList.size() != questionCount) { // 답안 수 == 문항수 
			return row;
		}
		
		// 1. 답안 입력
		for(Paper p : paperList) {
			p.setStudentNo(studentNo);
			row += paperMapper.insertPaper(p);
		}
		
		// 2. 점수 입력
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		paramMap.put("studentNo", studentNo);
		
		int totalScore = 100; // 총 점수
		double scorePerQuestion = totalScore/questionCount; // 문제 당 점수
		log.debug("\u001B[31m"+scorePerQuestion+"<-- getPaperOx scorePerQuestion");
		
		int myScore = 0;
		List<Map<String, Object>> scoreList = paperMapper.getPaperScoreList(paramMap);
		for(Map<String, Object> m : scoreList) {
			String exampleOx = (String)m.get("exampleOx");
			if(exampleOx.equals("정답")) {
				myScore += scorePerQuestion;
			} 
		}
		log.debug("\u001B[31m"+myScore+"<--getPaperScore myScore");
		
		// 3. 점수 입력 
		TestScore testScore = new TestScore(testNo, studentNo, myScore);

		int scoreRow = testScoreMapper.insertScore(testScore);
		log.debug("\u001B[31m"+scoreRow+"<--getPaperScore scoreRow");
		
		
		return row;
	}
}