package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;

@Mapper
public interface TeacherMapper {
	
	int updateTeacherPw(Map<String, Object> paramMap);
	int insertTeacher(Teacher teacher);
	Teacher login(Teacher teacher);
	int selectTeacherCount(String searchWord);
	int deleteTeacher(int teacherNo);
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);
}
