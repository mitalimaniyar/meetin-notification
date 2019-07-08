package org.jeavio.meetin.notification.service;

import org.jeavio.meetin.notification.model.Event;
import org.jeavio.meetin.notification.model.NotificationRequest;

import java.util.stream.Collectors;

import org.jeavio.meetin.notification.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHandler {

	@Autowired
	EmailUtility emailUtility;
	
	public ApiResponse processRequest(NotificationRequest request) {
		String triggerType = request.getTriggerType();
		if (triggerType.equals("create") || triggerType.equals("cancel") || triggerType.equals("modify")) {
			Event event = request.getEvent();
			emailUtility.sendNotification(triggerType,event,request.getEmailIds().stream().collect(Collectors.toSet()));
			ApiResponse response = new ApiResponse(200, "Success");
			return response;
		} else {
			ApiResponse response = new ApiResponse(400, "Bad Request. Event type not supported.");
			return response;
		}
	}
}
