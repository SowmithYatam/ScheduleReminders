package com.gajoi.scheduler.model;

import com.gajoi.scheduler.dao.Reminder;

import java.time.LocalDateTime;

public class ReminderTO {
    String id;
    String title;
    String message;
    String phoneNumber;
    String email;
    LocalDateTime sendTime;
    String userId;
    LocalDateTime createdOn;
    LocalDateTime modifiedOn;
    Reminder.Status status;

    public ReminderTO(String id, String title,
                      String message,
                      String phoneNumber,
                      String email,
                      LocalDateTime sendTime,
                      String userId,
                      LocalDateTime createdOn,
                      LocalDateTime modifiedOn,
                      Reminder.Status status){
        this.id = id;
        this.createdOn = createdOn;
        this.message = message;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.modifiedOn = modifiedOn;
        this.sendTime = sendTime;
        this.status = status;
        this.title = title;
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public Reminder.Status getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public void setStatus(Reminder.Status status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
