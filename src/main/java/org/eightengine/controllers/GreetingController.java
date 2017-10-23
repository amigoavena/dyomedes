package org.eightengine.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eightengine.domain.Greeting;
import org.eightengine.mapper.DBMapper;
import org.eightengine.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	Logger log = LogManager.getLogger(GreetingController.class);

	@Value("${test.message}")
	private String message;
	
    @Autowired
    private Service service;
	
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

	@RequestMapping(value = "/events/{id}",method=RequestMethod.GET)
	public Map<String,Object> getObject(@PathVariable String id) {
		Map<String, Object> results = service.getDocument(id);
		return results;
	}
    
	@RequestMapping(value = "/events",method=RequestMethod.POST)
	public Map<String,Object> postObject(@RequestBody Map<String,Object> id) {
		log.debug(ReflectionToStringBuilder.toString(id));
		String resultId = service.indexDocument(id);
		Map<String,Object> results = new HashMap<String,Object>();
		results.put("id", resultId);
		return results;
	}
	
	@RequestMapping(value = "/events",method=RequestMethod.PUT)
	public Map<String,Object> putObject(@RequestBody Map<String,Object> id) {
		log.debug(ReflectionToStringBuilder.toString(id));
		String resultId = service.indexDocument(id);
		Map<String,Object> results = new HashMap<String,Object>();
		results.put("id", resultId);
		return results;
	}
	
	@RequestMapping(value = "/events/{id}",method=RequestMethod.DELETE)
	public Map<String,String> deleteObject(@PathVariable  String id) {
		Map<String,String> results = new HashMap<String,String>();
		results.put("id", id);
		results.put("operation", service.deleteDocument(id));
		return results;
	}
	
}
