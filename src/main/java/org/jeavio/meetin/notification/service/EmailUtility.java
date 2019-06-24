package org.jeavio.meetin.notification.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.time.DateUtils;
import org.jeavio.meetin.notification.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtility {

	private static Logger log = LoggerFactory.getLogger(EmailUtility.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String mailFrom;

	public void sendNotification(String triggerType, Event event, String emailId) {

		String subject = getSubject(triggerType, event);
		String body = getBody(triggerType, event);
		sendMail(emailId, subject, body);
	}

	public void sendMail(String mailTo, String subject, String body) {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);

			helper.setFrom(new InternetAddress("noreply@yahoo.com", "Do not reply"));
			helper.setTo(mailTo);

			helper.setSubject(subject);

			helper.setText(body, true);

			javaMailSender.send(message);

		} catch (MessagingException e) {
			log.error("Error sending mail");
			log.error("Exception : ", e);
		} catch (Exception e) {
			log.error("Error sending mail");
			log.error("Exception : ", e);
		}

	}

	public String getBody(String type, Event event) {
		String emailBody = null;
		if (type.equals("create")) {
			emailBody = getCreateEventBody(event);
		} else {
			emailBody = getCancelEventBody(event);
		}
		return emailBody;
	}

	public String getCreateEventBody(Event event) {
		StringBuilder body = new StringBuilder();
		body.append("You have been invited to the following event.\n");
		body.append(event.toString());
		return new String(body);
	}

	public String getCancelEventBody(Event event) {
		StringBuilder body = new StringBuilder();
		body.append("The following event has been canceled.\n");
		body.append(event.toString());
		return new String(body);

	}

	public String getSubject(String type, Event event) {

		String emailSubject = null;
		if (type.equals("create")) {
			emailSubject = getCreateEventSubject(event);
		} else {
			emailSubject = getCancelEventSubject(event);
		}
		return emailSubject;
	}

	private String getCancelEventSubject(Event event) {

		StringBuilder subject = new StringBuilder();
		subject.append("Canceled: ");
		subject.append(event.getSubject());
		subject.append("@");
		subject.append(getEventTime(event));
		return new String(subject);
	}

	private String getCreateEventSubject(Event event) {

		StringBuilder subject = new StringBuilder();
		subject.append("Invitation: ");
		subject.append(event.getSubject());
		subject.append("@");
		subject.append(getEventTime(event));
		return new String(subject);

	}

	public String getEventTime(Event event) {
		Date startDate = event.getStartDate();
		Date endDate = event.getEndDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd,yyyy hh:mm a");
		String date = null;
		if (DateUtils.isSameDay(startDate, endDate)) {
			String start = dateFormat.format(startDate);
			String end = new SimpleDateFormat("hh:mm a").format(endDate);
			date = start + "-" + end;
		} else {
			String start = dateFormat.format(startDate);
			String end = dateFormat.format(endDate);
			date = start + "-" + end;
		}
		return date;

	}
}
