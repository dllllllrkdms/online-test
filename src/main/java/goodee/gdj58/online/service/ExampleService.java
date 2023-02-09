package goodee.gdj58.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.vo.Example;

@Service
@Transactional
public class ExampleService {
	@Autowired ExampleMapper exampleMapper;
	
	// 삭제
	public int removeQuestion(int questionNo) {
		return exampleMapper.deleteExample(questionNo);
	}

	// 보기 수정
	public int modifyExample(Example example) {
		return exampleMapper.updateExample(example);
	}
	
	// 보기 추가
	public int addExample(Example example) {
		return exampleMapper.insertExample(example);
	}
	
	// 보기 하나 출력
	public Example getExampleOne(int exampleNo) {
		return exampleMapper.selectExampleOne(exampleNo);
	}
	
	// 한 문제의 모든 보기 출력
	public List<Example> getExampleListByQuestion(int questionNo) {
		return exampleMapper.selectExampleListByQuestion(questionNo);
	}
	
	// 한 시험의 모든 보기 출력
	public List<Example> getExampleList(int testNo) {
		return exampleMapper.selectExampleList(testNo);
	}
}
