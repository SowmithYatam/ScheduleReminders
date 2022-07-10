package com.gajoi.scheduler.scheduler;

import com.gajoi.scheduler.dao.ReminderDAO;
import com.gajoi.scheduler.model.ReminderTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

@RequestScoped
public class Scheduler {

    @Inject
    private ReminderDAO dao;


    Timer timer = null;

    public Scheduler(){
        timer = new Timer();
    }

    public void schedule(ReminderTO to){
        LocalDateTime ldt = to.getSendTime();
        Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        timer.schedule(new MessageTask(to), date);
    }
}
