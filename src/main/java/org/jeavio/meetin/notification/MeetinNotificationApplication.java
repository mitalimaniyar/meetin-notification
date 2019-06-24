package org.jeavio.meetin.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MeetinNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetinNotificationApplication.class, args);
	}

}
