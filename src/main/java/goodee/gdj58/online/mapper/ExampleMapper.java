package goodee.gdj58.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;

@Mapper
public interface ExampleMapper {
	
	int deleteExample(int questionNo);
	int updateExample(Example example);
	int insertExample(Example example);
	Example selectExampleOne(int exampleNo);
	List<Example> selectExampleListByQuestion(int questionNo);
	List<Example> selectExampleList(int testNo);
	
}
