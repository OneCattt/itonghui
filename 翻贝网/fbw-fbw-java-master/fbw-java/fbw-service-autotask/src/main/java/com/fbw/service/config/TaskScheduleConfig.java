package com.fbw.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.fbw.service.task")
@EnableScheduling
public class TaskScheduleConfig
{

}
