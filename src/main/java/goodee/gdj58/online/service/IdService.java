package goodee.gdj58.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.IdMapper;

@Service
@Transactional
public class IdService {
	@Autowired IdMapper idMapper;
	
	// 아이디 중복 체크, null이면 사용가능, null이 아니면 사용불가능
	public String getIdCheck(String id) {
		return idMapper.selectIdCheck(id);
	}
}
