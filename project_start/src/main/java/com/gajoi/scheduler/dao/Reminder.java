package com.gajoi.scheduler.dao;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reminder")
@NamedQuery(name = "Reminder.findAll", query = "SELECT e FROM Reminder e")
public class Reminder {

    public enum Status {
        NOTDELIVERED,SCHEDULED, DELIVERED, ERROR, REJECTED;
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "Title")
    private String title;

    @Lob
    @Column(name = "message")
    private String message;

    @Column(name = "send_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime sendTime;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "user_id")
    private String userId;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
