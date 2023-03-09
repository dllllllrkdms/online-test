package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestMapper {
	
	Test selectTestOne(int testNo);
	
	int deleteTest(int testNo);
	int updateTest(Test test);
	int insertTest(Test test);
	
	int selectPastTestCount(Map<String, Object> paramMap);
	int selectTestCount(Map<String, Object> paramMap);
	
	List<Test> selectAllTestList();
	List<Test> selectPastTestList(Page page);
	List<Test> selectTestList(Page page);
}