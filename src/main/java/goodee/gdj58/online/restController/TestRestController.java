package goodee.gdj58.online.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Test;

@RestController
public class TestRestController {
	@Autowired TestService testService;
	
	@GetMapping("/testOne")
	public Test getTestOne(int testNo) {
		return testService.getTestOne(testNo);
	}
}