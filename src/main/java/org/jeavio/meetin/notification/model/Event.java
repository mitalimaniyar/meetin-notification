package org.jeavio.meetin.notification.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

public class Event {

	private String subject;
	private String agenda;
	private String organizer;
	private String roomName;
	private List<String> members = new ArrayList<String>();
	private Date startDate;
	private Date endDate;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public Date getStartDate() {
		return startDate;

	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("E MMM dd,yyyy hh:mm a");
		String date=null;
		if(DateUtils.isSameDay(startDate,endDate)){
			String start=dateFormat.format(startDate);
			String end=new SimpleDateFormat("hh:mm a").format(endDate);
			date = start + "-" + end;
		}else {
		String start=dateFormat.format(startDate);
		String end=dateFormat.format(endDate);
		date = start + "-" + end;
		}
		
		StringBuilder event = new StringBuilder();
		event.append("\nEvent:\n");
		event.append("Subject: " + subject + "\n");
		event.append("Agenda: " + agenda + "\n");
		event.append("Organizer: "+organizer+"\n");
		event.append("When: " + date + "\n");
		event.append("Where: "+ roomName+"\n");
		event.append("Members: \n");
		for(String member:members) {
			event.append("- "+member);
			event.append("\n");
		}
		return event.toString();
	}

}
