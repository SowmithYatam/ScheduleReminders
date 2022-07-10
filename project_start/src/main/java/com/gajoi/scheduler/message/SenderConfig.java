package com.gajoi.scheduler.message;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SenderConfig {

    public static final String ACCOUNT_SID = "AC49501e99525ca4614abe0ae479e57ae8";
    public static final String AUTH_TOKEN = "2e88ca43fab999840a474e6b2a794e5b";
    public static final String currNumber = "+15005550006";


    public SenderConfig(){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    public void sendMessage(String title, String message, String phoneNumber){
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        builder.append(System.lineSeparator());
        builder.append(message);
        Message msg = Message.creator(new PhoneNumber(phoneNumber), "MG45c7a50755afa5aa2c11f65c30bac8bc", builder.toString()).create();
        msg.getSid();
    }
}
