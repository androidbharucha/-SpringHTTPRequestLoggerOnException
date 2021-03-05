package org.tiaa.chaos.chaostest;

import org.springframework.stereotype.Service;

@Service
public class ChaosTestService {

	public String chaosServiceTest(String input) {
		return input + " service";
		
	}
	
}
