package goodee.gdj58.online.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class QuestionService {
	@Autowired ExampleService exampleService;
	@Autowired QuestionMapper questionMapper;
	
	// 문제 수
	public int getQuestionCount(int testNo) {
		return questionMapper.selectQuestionCount(testNo);
	}
	
	// 문제 수정 : 삭제 후 입력
	public int modifyQuestion(Question question, List<Example> exampleList) {
		
		
		// 1. 보기 삭제 
		int row = exampleService.removeExample(question.getQuestionNo());
		
		// 2. 문제 수정
		row = questionMapper.updateQuestion(question);
		
		// 3. 보기 입력
		int exampleIdx = 1; // 보기 인덱스
		for(Example e : exampleList) {
			e.setExampleIdx(exampleIdx);
			e.setQuestionNo(question.getQuestionNo());
			if(e.getExampleOx() == null) {
				e.setExampleOx("오답");
			}
			
			row += exampleService.addExample(e);
			exampleIdx++;
		}
		return row; 
	}
	
	// 문제, 보기상세보기
	public Question getQuestion(int questionNo) {
		return questionMapper.selectQuestion(questionNo);
	}
	
	// 문제, 보기 추가
	public int addQuestion(Question question, List<Example> exampleList) {
		
		// 1. 문제 추가
		int row = questionMapper.insertQuestion(question);
		
		int questionNo = question.getQuestionNo(); // insert 시 pk값 받아오기
		log.debug("\u001B[31m"+questionNo+"<-- addQuestion questionNo");
		
		// 보기 추가
		int exampleIdx = 1; // 보기 인덱스
		for(Example e : exampleList) {
			e.setExampleIdx(exampleIdx);
			e.setQuestionNo(questionNo);
			if(e.getExampleOx() == null) {
				e.setExampleOx("오답");
			}
			
			row += exampleService.addExample(e);
			exampleIdx++;
		}
		return row;
	}
	
	// 목록 출력
	public List<Question> getQuestionList(int testNo) {
		return questionMapper.selectQuestionList(testNo);
	}
}
