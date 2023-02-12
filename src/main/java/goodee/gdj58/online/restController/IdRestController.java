package goodee.gdj58.online.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.IdService;

@RestController
public class IdRestController {
	@Autowired IdService idService;
	
	@PostMapping("/idck")
	public String idck(@RequestParam(value="id") String id) {
		String returnMsg = "NO";
		if(idService.getIdCheck(id)==null) {
			returnMsg = "YES";
		}
		return returnMsg;
	}
}
