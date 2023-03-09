package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.TestScore;

@Mapper
public interface TestScoreMapper {
	
	int selectAvgCount(Map<String, Object> map);
	List<Map<String, Object>> selectAvgByTest(Page page);
	int selectTestCountByStudent(Map<String, Object> map);
	TestScore paperCheckByStudent(TestScore testScore);
	List<Map<String, Object>> selectTestScoreList(Page page);
	int insertScore(TestScore testScore);
}
