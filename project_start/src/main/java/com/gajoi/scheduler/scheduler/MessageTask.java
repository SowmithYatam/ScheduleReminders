package com.gajoi.scheduler.scheduler;

import com.gajoi.scheduler.dao.Reminder;
import com.gajoi.scheduler.dao.ReminderDAO;
import com.gajoi.scheduler.model.ReminderTO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.TimerTask;

@ApplicationScoped
public class MessageTask extends TimerTask {

    private static final String ACCOUNT_SID = "";
    private static final String AUTH_TOKEN = "";
    private ReminderTO to;

    @Inject
    private ReminderDAO dao;

    public MessageTask(){

    }

    public MessageTask(ReminderTO to){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        this.to = to;
    }

    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        builder.append(to.getTitle());
        builder.append(System.lineSeparator());
        builder.append(to.getMessage());
        Message msg = Message.creator(new PhoneNumber(to.getPhoneNumber()), "", builder.toString()).create();
        dao.changeStatus(to.getId(), Reminder.Status.DELIVERED);
    }
}
