package org.eightengine.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eightengine.controllers.GreetingController;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {
	
	Logger log = LogManager.getLogger(GreetingController.class);

	@Autowired
	private TransportClient client;

	public String indexDocument(Map<String,Object> obj) {
		IndexResponse response = client.prepareIndex("events", "activity").setSource(obj).get();
		return response.getId();
	}

	public Map<String,Object> getDocument(String id) {
		GetResponse response = client.prepareGet("events", "activity", id)
		        .setOperationThreaded(false)
		        .get();
		Map<String,Object> map =  response.getSource();
		return map;
	}	
	
	public String deleteDocument(String id) {
		DeleteResponse response = client.prepareDelete("events", "activity", id)
		        .get(); 
		return response.getResult().toString();
	}	

	
	public void updateDocument(Map<String,Object> obj, String id){
		UpdateRequest updateRequest = new UpdateRequest("events", "activity", id)
		        .doc(obj);
		try {
			UpdateResponse response = client.update(updateRequest).get();
			log.debug(ReflectionToStringBuilder.toString(response));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
