package com.example.drill.schedule;

import com.example.drill.config.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RefreshScope
public class TestScheduler {

    @Scheduled(cron = "${drill.test-schedule-period}")
    public void scheduleHang(){
        System.out.println("hang : " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
