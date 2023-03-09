package goodee.gdj58.online.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Page;
import goodee.gdj58.online.vo.Test;


@RestController
public class TestRestController {
	@Autowired private TestService testService;

	@GetMapping("/testCalendar")
	public List<Test> getCalendar(){
		return testService.getAllTestList();
	}
}