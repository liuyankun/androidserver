package com.lyk.myspring.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//@Scheduled(cron = "*/10 * * * * *")
    private void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
