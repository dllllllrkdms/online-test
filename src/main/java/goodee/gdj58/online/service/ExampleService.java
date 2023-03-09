package goodee.gdj58.online.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.vo.Example;

@Service
@Transactional
public class ExampleService {
	@Autowired ExampleMapper exampleMapper;
	
	// 답 출력
	public List<Map<String, Object>> getAnswer(int testNo){
		return exampleMapper.selectAnswer(testNo);
	}
	
	// 삭제
	public int removeExample(int questionNo) {
		return exampleMapper.deleteExample(questionNo);
	}
	
	// 보기 추가
	public int addExample(Example example) {
		return exampleMapper.insertExample(example);
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
