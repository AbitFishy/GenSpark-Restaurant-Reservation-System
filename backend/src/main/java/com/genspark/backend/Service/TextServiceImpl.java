package com.genspark.backend.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TextServiceImpl {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC231db42124c05a0a3a4a454b875d0bfb";
    public static final String AUTH_TOKEN = "849b464694e094528975b264de4fb8fe";

    public static boolean sendSMS(String number, String text) {
        try{
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new PhoneNumber(number),
                            "MGb89b592734846740498f96a9a8e54979",
                            text)
                    .create();

            return true;
        }catch (Exception e){
            return false;
        }
    }
}