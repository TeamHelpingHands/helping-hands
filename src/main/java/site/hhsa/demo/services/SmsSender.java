package site.hhsa.demo.services;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class SmsSender {
    // Find your Account Sid and Token at twilio.com/console
    private final String ACCOUNT_SID = "AC0aab0858a7c9ff5624bbbc06e5afa9ed";
    private final String AUTH_TOKEN = "f72c35ea6aa47a734b0cfab7fbb6b5a6";

    public SmsSender (String eventMessage, String recieverNum) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(recieverNum),
                new com.twilio.type.PhoneNumber("+12109412256"),
                eventMessage)
                .create();

        System.out.println(message.getSid());
    }
}