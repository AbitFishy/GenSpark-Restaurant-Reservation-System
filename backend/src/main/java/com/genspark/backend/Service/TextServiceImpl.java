package com.genspark.backend.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

public class TextServiceImpl {
    // Find your Account Sid and Token at twilio.com/console
    static Dotenv dotenv = Dotenv.configure()
            .directory("src/main/resources")
            .filename("twilio.env")
            .load();

    public static boolean sendSMS(String number, String text) {
        System.out.println(System.getProperty("user.dir"));
        try{
            Twilio.init(dotenv.get("TWILIO_ACCOUNT_SID"), dotenv.get("TWILIO_AUTH_TOKEN"));
            Message message = Message.creator(
                            new PhoneNumber(number),
                            "MGb89b592734846740498f96a9a8e54979",
                            text)
                    .create();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}