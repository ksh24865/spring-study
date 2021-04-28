package org.springframework.samples.petclinic.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.aspect.LogExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@Autowired // Bean으로 등록된 애들만 의존성을 관리해준다.
	String seongho;

	@LogExecutionTime
	@GetMapping("/testbean")
	public String testbean() {
		return "hello " + seongho;
	}

}
