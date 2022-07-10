package com.gajoi.scheduler.message;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SenderConfig {

    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";
    public static final String currNumber = "+15005550006";


    public SenderConfig(){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    public void sendMessage(String title, String message, String phoneNumber){
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        builder.append(System.lineSeparator());
        builder.append(message);
        Message msg = Message.creator(new PhoneNumber(phoneNumber), "", builder.toString()).create();
        msg.getSid();
    }
}
