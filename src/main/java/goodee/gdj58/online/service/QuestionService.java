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
	
	// 삭제 트랜잭션 - 보기 처리 필요
	public int removeQuestion(int questionNo) {
		return questionMapper.deleteQuestion(questionNo);
	}
	
	// 문제 수정
	public int modifyQuestion(Question question, Example example) {
		
		int row = questionMapper.updateQuestion(question);
		
		List<Example> exampleList = example.getExampleList();
		log.debug("\u001B[31m"+exampleList+"<-- addQuestion exampleList");
		
		for(Example e : exampleList) {
			row += exampleService.modifyExample(e);
		}
		
		return row; 
	}
	
	// 상세보기
	public Question getQuestion(int questionNo) {
		return questionMapper.selectQuestion(questionNo);
	}
	
	// 문제 추가
	public int addQuestion(Question question, Example example) {
		List<Example> exampleList = example.getExampleList();
		log.debug("\u001B[31m"+example+"<-- addQuestion example");
		
		int row = questionMapper.insertQuestion(question);
		
		int questionNo = question.getQuestionNo();
		
		log.debug("\u001B[31m"+questionNo+"<-- addQuestion questionNo");
		
		for(Example e : exampleList) {
			e.setQuestionNo(questionNo);
			row += exampleService.addExample(e);
		}
		return row;
	}
	
	// 목록 출력
	public List<Question> getQuestionList(int testNo) {
		return questionMapper.selectQuestionList(testNo);
	}
}
