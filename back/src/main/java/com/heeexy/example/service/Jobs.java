package com.heeexy.example.service;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jobs {

    public final static long ONE_Minute =  60 * 1000;

    @Autowired
    private UserRunLogForGroupService userRunLogForGroupService;

    @Autowired
    private RunGroupService runGroupService;

    //@Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
        System.out.println(new Date()+" >>fixedDelay执行....");
    }

    //@Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
        System.out.println(new Date()+" >>fixedRate执行....");
        userRunLogForGroupService.updateRankByGroup(runGroupService.findById(1L));
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cronJob(){
        System.out.println(new Date()+" >>cron执行....");
        userRunLogForGroupService.updateRankByGroup(runGroupService.findById(1L));
    }
}
