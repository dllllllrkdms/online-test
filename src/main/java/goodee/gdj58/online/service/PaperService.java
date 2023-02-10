package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.PaperMapper;
import goodee.gdj58.online.vo.Paper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PaperService {
	@Autowired PaperMapper paperMapper;
	
	// 점수 확인
	public int getPaperScore(int testNo, int studentNo, int questionCount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		paramMap.put("studentNo", studentNo);
		
		int totalScore = 100;
		double scorePerQuestion = totalScore/questionCount;
		log.debug("\u001B[31m"+scorePerQuestion+"<-- getPaperOx scorePerQuestion");
		
		int myScore = 0;
		List<Map<String, Object>> paperList = paperMapper.getPaperScoreList(paramMap);
		for(Map<String, Object> m : paperList) {
			String exampleOx = (String)m.get("exampleOx");
			if(exampleOx.equals("정답")) {
				myScore += scorePerQuestion;
			} 
		}
		return myScore;
	}
	
	// 응시자 수, 응시했는지 여부를 알기 위한 답안 count
	public int getPaperCount(int testNo, int studentNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		paramMap.put("studentNo", studentNo);
		int count = paperMapper.selectPaperCount(paramMap);
		if(studentNo!=0) {
			
		}
		return count;
	}
	
	// 답안 입력
	public int addPaper(int studentNo, Paper paper) {
		List<Paper> paperList = paper.getPaperList();
		int row = 0;
		for(Paper p : paperList) {
			p.setStudentNo(studentNo);
			row += paperMapper.insertPaper(p);
		}
		return row;
	}
}