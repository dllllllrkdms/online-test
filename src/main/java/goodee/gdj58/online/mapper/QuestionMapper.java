package goodee.gdj58.online.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Question;

@Mapper
public interface QuestionMapper {
	
	int deleteQuestion(int questionNo);
	Question selectQuestion(int questionNo);
	int updateQuestion(Question question);
	int insertQuestion(Question question);
	List<Question> selectQuestionList(int testNo);
}
