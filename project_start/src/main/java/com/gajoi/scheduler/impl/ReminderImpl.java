package com.gajoi.scheduler.impl;

import com.gajoi.scheduler.dao.Reminder;
import com.gajoi.scheduler.dao.ReminderDAO;
import com.gajoi.scheduler.model.ReminderTO;
import com.gajoi.scheduler.cache.CacheManagerImpl;
import com.gajoi.scheduler.scheduler.Scheduler;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class ReminderImpl {

    @Inject
    private ReminderDAO dao;

    @Inject
    private CacheManagerImpl cacheImpl;

    @Inject
    private Scheduler scheduler;

    public ReminderTO createReminder(String userId, String title, String message, String phoneNumber, String email, String sendTime){
        Reminder reminder = new Reminder();
        reminder.setId(UUID.randomUUID().toString());
        reminder.setUserId(userId);
        reminder.setTitle(title);
        reminder.setMessage(message);
        reminder.setPhoneNumber(phoneNumber);
        reminder.setEmail(email);
        reminder.setSendTime(LocalDateTime.parse(sendTime));
        reminder.setCreatedOn(LocalDateTime.now());
        reminder.setModifiedOn(reminder.getCreatedOn());
        reminder.setStatus(Reminder.Status.SCHEDULED);
        dao.createReminder(reminder);
        ReminderTO to = new ReminderTO(reminder.getId(), reminder.getTitle(), reminder.getMessage(), reminder.getPhoneNumber(), reminder.getEmail(), reminder.getSendTime(), reminder.getUserId(), reminder.getCreatedOn(), reminder.getModifiedOn(),reminder.getStatus());
        //cacheImpl.put(reminder.getId(),to);
        scheduler.schedule(to);
        return to;
    }

    public ReminderTO updateRemainder(String id, String title, String message, String phoneNumber, String email, String sendTime){
        Reminder reminder = dao.getReminderById(id);
        reminder.setTitle(title);
        reminder.setMessage(message);
        reminder.setSendTime(LocalDateTime.parse(sendTime));
        reminder.setPhoneNumber(phoneNumber);
        reminder.setEmail(email);
        reminder.setModifiedOn(LocalDateTime.now());
        dao.updateBlog(reminder);
        ReminderTO to = new ReminderTO(reminder.getId(), reminder.getTitle(), reminder.getMessage(), reminder.getPhoneNumber(), reminder.getEmail(), reminder.getSendTime(), reminder.getUserId(), reminder.getCreatedOn(), reminder.getModifiedOn(),reminder.getStatus());
        cacheImpl.put(reminder.getId(),to);
       return to;
    }

    public void deleteRemainder(String id){
        dao.removeReminder(id);
        cacheImpl.remove(id);
    }

    public List<Reminder> getAllReminders(){
      return dao.getAllReminders();
    }

}
