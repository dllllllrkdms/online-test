package goodee.gdj58.online.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class QuestionService {
	@Autowired QuestionMapper questionMapper;
	@Autowired ExampleMapper exampleMapper;
	
	// 삭제
	public int removeQuestion(int questionNo) {
		int row = exampleMapper.deleteExample(questionNo);
		row += questionMapper.deleteQuestion(questionNo);
		return row;
	}
	
	// 문제 + 보기 수정
	public int modifyQuestion(Question question, List<Example> exampleList) {
		log.debug("\u001B[31m"+exampleList+"<-- addQuestion exampleList");
		
		int row = questionMapper.updateQuestion(question);
		int questionNo = question.getQuestionNo();
		log.debug("\u001B[31m"+questionNo+"<-- addQuestion questionNo");
		
		for(Example e : exampleList) {
			e.setQuestionNo(questionNo);
			row += exampleMapper.updateExample(e);
		}
		
		return row; // row == 6 이면 모두 수정 성공
	}
	
	// 상세보기
	public Question getQuestion(int questionNo) {
		return questionMapper.selectQuestion(questionNo);
	}
	
	// 문제 + 보기 추가
	public int addQuestion(Question question, List<Example> exampleList) {
		log.debug("\u001B[31m"+exampleList+"<-- addQuestion exampleList");
		
		int row = questionMapper.insertQuestion(question);
		int questionNo = question.getQuestionNo();
		log.debug("\u001B[31m"+questionNo+"<-- addQuestion questionNo");
		
		for(Example e : exampleList) {
			e.setQuestionNo(questionNo);
			row += exampleMapper.insertExample(e);
		}
		return row; // row == 6 이면 모두 추가 성공
	}
	
	// 목록 출력
	public List<Question> getQuestionList(int testNo) {
		return questionMapper.selectQuestionList(testNo);
	}
}
