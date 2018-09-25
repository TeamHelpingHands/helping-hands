package site.hhsa.demo.services;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.math.BigDecimal;

public class SmsSender {
    // Find your Account Sid and Token at twilio.com/console
//    private Config config;

    @Value("${accountSID}")
    private String ACCOUNT_SID;

    @Value("${authTOKEN}")
    private String AUTH_TOKEN;

    @Value("${phnNUM}")
    private String Phn_num;

    public void SmsSender(String recieverNum, String txtMessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+"+recieverNum),
                new com.twilio.type.PhoneNumber(Phn_num),
                txtMessage)
                .create();

        System.out.println(message.getSid());
    }
}