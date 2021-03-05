package org.tiaa.chaos.chaostest;



import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/")
public class MyChaosTestcontroller {

	@Autowired
	ChaosTestService chaosTestService;

	// https://codecentric.github.io/chaos-monkey-spring-boot/2.2.0/#_properties
	@RequestMapping(value = "/testChaos", method = RequestMethod.GET)
	public String testChoas(String message) {

		return "SUCCESS";

	}
	@GetMapping("/testChaos1/{message}")
	public String testChoas1(@PathVariable("message") String message) {

		String returnValue = null;
		try {
			returnValue = chaosTestService.chaosServiceTest(message);
		} catch (Exception e) {
			throw new HTTPException(HttpStatus.BAD_REQUEST.value());
		}

		return returnValue;

	}
	
	@PostMapping("/employee")
	  Employee newEmployee(@RequestBody Employee newEmployee) {
		
		/// here 
			System.out.println("Rest controller called ");
				newEmployee.setSurname("Shah");	
	    return  newEmployee;
	  }

}
