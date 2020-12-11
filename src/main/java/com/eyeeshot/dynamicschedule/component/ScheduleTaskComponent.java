package com.eyeeshot.dynamicschedule.component;

import com.eyeeshot.dynamicschedule.service.ScheduleTaskService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ScheduleTaskComponent {

  private final ScheduleTaskService scheduleTaskService;
  private final SimpleDateFormat dataFormat;


  public ScheduleTaskComponent(
    ScheduleTaskService scheduleTaskService) {
    this.scheduleTaskService = scheduleTaskService;
    this.dataFormat = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
  }

  @Scheduled(fixedDelay = 10000)
  public void runEvery10Sec() {
    Date time = new Date();
    Date scheduleTime = addSeconds(time,2);
    log.info("현재시간 : " + dataFormat.format(time));
    log.info("스케쥴 설정 시간 : " + dataFormat.format(scheduleTime));
    setSchedule(scheduleTime);
  }

  public void setSchedule(Date scheduleTime) {
    scheduleTaskService.addTaskToScheduler(1,this.doJob(),scheduleTime);
  }

  public static Date addSeconds(Date date, Integer seconds) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.SECOND, seconds);
    return cal.getTime();
  }


  public Runnable doJob(){
    Date time = new Date();
    // 스케쥴 등록 당시의 실행된 날자 변수처리됨.
    Runnable runnable = () -> log.info("Runnable task : " + dataFormat.format(time));
    return runnable;
  }
}
