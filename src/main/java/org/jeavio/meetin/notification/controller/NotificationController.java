package org.jeavio.meetin.notification.controller;

import org.jeavio.meetin.notification.model.NotificationRequest;
import org.jeavio.meetin.notification.model.Response;
import org.jeavio.meetin.notification.service.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

	@Autowired
	RequestHandler requestHandler;
	
	@RequestMapping(method = RequestMethod.POST,path = "/api/notify")
	public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest body){
		Response response = requestHandler.processRequest(body);
		
		if(response.getCode()==200)
			return ResponseEntity.status(HttpStatus.OK).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
}
