package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestMapper {
	
	Map<String, Object> selectTestCountByStudent(Map<String, Object> paramMap);
	List<Map<String, Object>> selectTestListByStudent(Map<String, Object> paramMap);
	Test selectTestOne(int testNo);
	int deleteTest(int testNo);
	int updateTest(Test test);
	int insertTest(Test test);
	int selectTestCount(Map<String, Object> paramMap);
	List<Test> selectTodayTestList(Map<String, Object> paramMap);
	List<Test> selectPastTestList(Map<String, Object> paramMap);
	List<Test> selectTestList(Map<String, Object> paramMap);
}