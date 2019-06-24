package org.jeavio.meetin.notification.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationRequest {

	private String triggerType;
	private List<String> emailIds = new ArrayList<String>();
	private Event event;
	
	public NotificationRequest() {    }
	
	public NotificationRequest(String triggerType, List<String> emailIds, Event event) {
		super();
		this.triggerType = triggerType;
		this.emailIds = emailIds;
		this.event = event;
	}
	
	public String getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	public List<String> getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(List<String> emailIds) {
		this.emailIds = emailIds;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
