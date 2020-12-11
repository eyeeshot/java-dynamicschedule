package com.eyeeshot.dynamicschedule.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ScheduleTaskService {
  private final SimpleDateFormat cronDataFormat;
  // Task Scheduler
  TaskScheduler scheduler;

  // A map for keeping scheduled tasks
  Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

  public ScheduleTaskService(TaskScheduler scheduler) {
    this.scheduler = scheduler;
    this.cronDataFormat = new SimpleDateFormat("ss mm HH dd MM ?");
  }

  // Schedule Task 등록
  public void addTaskToScheduler(int id, Runnable task, Date scheduleTime) {
    log.info("task 요청 들어옴 : " + cronDataFormat.format(scheduleTime));
    ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(cronDataFormat.format(scheduleTime), TimeZone
      .getTimeZone(TimeZone.getDefault().getID())));
    jobsMap.put(id, scheduledTask);
  }

  // Remove scheduled task
  public void removeTaskFromScheduler(int id) {
    ScheduledFuture<?> scheduledTask = jobsMap.get(id);
    if(scheduledTask != null) {
      scheduledTask.cancel(true);
      jobsMap.put(id, null);
    }
  }

  // A context refresh event listener
  @EventListener({ ContextRefreshedEvent.class })
  void contextRefreshedEvent() {
    // Get all tasks from DB and reschedule them in case of context restarted
  }
}
