package org.eightengine.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eightengine.domain.Greeting;
import org.eightengine.mapper.DBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	Logger log = LogManager.getLogger(GreetingController.class);

	@Value("${test.message}")
	private String message;
	
	@Autowired
	DBMapper mapper;
	
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(message, name));
    }
    
    @RequestMapping("/test")
    public Greeting getDBGreeting(@RequestParam(value="id") Long id) {
    		log.debug(id);
    		Greeting greeting = mapper.getGreeting(id);
        return greeting;
    }
    
}
