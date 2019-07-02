package org.jeavio.meetin.notification.service;

import org.jeavio.meetin.notification.model.Event;
import org.jeavio.meetin.notification.model.NotificationRequest;
import org.jeavio.meetin.notification.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHandler {

	@Autowired
	EmailUtility emailUtility;
	
	public Response processRequest(NotificationRequest request) {
		String triggerType = request.getTriggerType();
		if (triggerType.equals("create") || triggerType.equals("cancel") || triggerType.equals("modify")) {
			Event event = request.getEvent();
			emailUtility.sendNotification(triggerType,event,request.getEmailIds());
			Response response = new Response(200, "Success");
			return response;
		} else {
			Response response = new Response(400, "Bad Request. Event type not supported.");
			return response;
		}
	}
}
