package com.eyeeshot.dynamicschedule;

import com.eyeeshot.dynamicschedule.component.ScheduleTaskComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DynamicscheduleApplication {
  public static void main(String[] args) {
    SpringApplication.run(DynamicscheduleApplication.class, args);
  }

}
