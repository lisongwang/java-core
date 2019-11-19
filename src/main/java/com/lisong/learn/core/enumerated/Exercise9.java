package com.lisong.learn.core.enumerated;

import java.util.EnumMap;
import java.util.Map;

import static com.lisong.learn.core.util.Print.print;

public class Exercise9 {

    public static void main(String[] args) {
        PostOffice2 office = new PostOffice2();
        for(Mail mail : Mail.generate(10))
            office.handleMail(mail);
    }
}

interface Handler {
    boolean handler(Mail mail);
}

class PostOffice2 {

    enum MailHandler2 {
        General_Delivery,
        Machine_Scan,
        VISUAL_INSPECTION,
        RETURN_TO_SENDER
    }

    private EnumMap<MailHandler2, Handler> handlerMap = new EnumMap<>(MailHandler2.class);
    {
        handlerMap.put(MailHandler2.General_Delivery, (mail)->{
            switch(mail.generalDelivery) {
                case YES: print(mail + "\nUsing general delivery for Mail " + mail.id); return true;
                default: return false;
            }
        });

        handlerMap.put(MailHandler2.Machine_Scan, (mail)->{
            switch(mail.scannability) {
                case UNSCANNABLE: return false;
                default: switch(mail.address) {
                    case INCORRECT: return false;
                    default: print(mail + "\nDeliver Mail " + mail.id + " automatically"); return true;
                }
            }
        });

        handlerMap.put(MailHandler2.VISUAL_INSPECTION, (mail)->{
            switch(mail.readability) {
                case ILLEGIBLE: return false;
                default: switch(mail.address) {
                    case INCORRECT: return false;
                    default: print(mail + "\nDeliver Mail " + mail.id + " normally"); return true;
                }
            }
        });

        handlerMap.put(MailHandler2.RETURN_TO_SENDER, (mail)->{
            switch(mail.returnAddress) {
                case MISSING: return false;
                default: print(mail + "\nReturn Mail " + mail.id + " to sender"); return true;
            }
        });
    }

    void handleMail(Mail mail) {
        for(Map.Entry<MailHandler2, Handler> entry : handlerMap.entrySet()) {
            if(entry.getValue().handler(mail))
                return;
        }
        print(mail + "\nMail " + mail.id + " is a dead letter");
    }
}