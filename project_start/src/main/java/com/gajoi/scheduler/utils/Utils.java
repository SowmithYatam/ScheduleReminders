package com.gajoi.scheduler.utils;

import com.gajoi.scheduler.dao.Reminder;
import com.gajoi.scheduler.model.ReminderTO;
import jakarta.json.*;

import java.util.List;

public class Utils {

    public static JsonObject convert(ReminderTO reminder){
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("id",reminder.getId());
        json.add("user", reminder.getUserId());
        json.add("title",reminder.getTitle());
        json.add("message", reminder.getMessage());
        json.add("phoneNumber", reminder.getPhoneNumber());
        json.add("email",reminder.getEmail());
        json.add("sendTime", reminder.getSendTime().toString());
        json.add("createdOn", reminder.getCreatedOn().toString());
        json.add("modifiedOn", reminder.getModifiedOn().toString());
        json.add("status", reminder.getStatus().toString());
        return json.build();
    }

    public static JsonArray convertAll(List<Reminder> reminders){
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for(Reminder reminder : reminders){
            arr.add(convert(new ReminderTO(reminder.getId(), reminder.getTitle(), reminder.getMessage(), reminder.getPhoneNumber(), reminder.getEmail(), reminder.getSendTime(), reminder.getUserId(), reminder.getCreatedOn(), reminder.getModifiedOn(),reminder.getStatus())));
        }
        return arr.build();
    }
}
