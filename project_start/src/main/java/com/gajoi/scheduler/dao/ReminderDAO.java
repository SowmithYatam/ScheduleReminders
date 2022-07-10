package com.gajoi.scheduler.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class ReminderDAO {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public void createReminder(Reminder reminder){
        em.persist(reminder);
    }

    public void updateBlog(Reminder reminder){
        em.merge(reminder);
    }

    public Reminder getReminderById(String reminderId){
        return em.find(Reminder.class,reminderId);
    }

    public void removeReminder(String reminderId){
        em.remove(getReminderById(reminderId));
    }

    public List<Reminder> getAllReminders(){
        return em.createNamedQuery("Reminder.findAll").getResultList();
    }

    public void changeStatus(String id, Reminder.Status status){
        Reminder reminder = getReminderById(id);
        reminder.setStatus(status);
        updateBlog(reminder);
    }

    public EntityManager getEm() {
        return em;
    }

}
