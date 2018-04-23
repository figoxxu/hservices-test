package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HystrixServicesController {
	
	private boolean down = false;
	
	@RequestMapping("/getNames1")
	public @ResponseBody List<String> getNames() {
		System.out.println("down - > " +  down);
		if (down) {
			throw new IllegalStateException("WTF");
		}
		return Arrays.asList("fei", "sv", "tw", "jg", "mou", "1");
	}

	@RequestMapping("/getNames2")
	public @ResponseBody List<String> getNames2() throws InterruptedException {
		System.out.println("called");
		Thread.sleep(30000L);
		if (down) {
			
			throw new IllegalStateException("WTF2");
		}
		return Arrays.asList("fei", "sv", "tw", "jg", "mou", "2");
	}
	
	@RequestMapping("/switch")
	public @ResponseBody Boolean switchDown() {
		down = !down;
		return down;
	}

}
