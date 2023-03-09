package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;

@Mapper
public interface ExampleMapper {
	
	List<Map<String, Object>> selectAnswer(int testNo);
	int deleteExample(int questionNo);
	int insertExample(Example example);
	Example selectExampleOne(int exampleNo);
	List<Example> selectExampleListByQuestion(int questionNo);
	List<Example> selectExampleList(int testNo);
	
}
