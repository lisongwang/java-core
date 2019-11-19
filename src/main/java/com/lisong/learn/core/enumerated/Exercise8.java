package com.lisong.learn.core.enumerated;

import com.lisong.learn.core.enumerated.util.Enums;

import java.util.Iterator;

import static com.lisong.learn.core.util.Print.print;

public class Exercise8 {

    public static void main(String[] args) {
        PostOffice office = new PostOffice();
        for(Mail mail : Mail.generate(10))
            office.handleMail(mail);
    }
}

class PostOffice {

    enum MailHandler {
        General_Delivery {
            @Override
            boolean handle(Mail mail) {
                switch(mail.generalDelivery) {
                    case YES: print(mail + "\nUsing general delivery for Mail " + mail.id); return true;
                    default: return forward(mail);
                }
            }
        },

        Machine_Scan {
            @Override
            boolean handle(Mail mail) {
                switch(mail.scannability) {
                    case UNSCANNABLE: return forward(mail);
                    default: switch(mail.address) {
                        case INCORRECT: return forward(mail);
                        default: print(mail + "\nDeliver Mail " + mail.id + " automatically"); return true;
                    }
                }
            }
        },

        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail mail) {
                switch(mail.readability) {
                    case ILLEGIBLE: return forward(mail);
                    default: switch(mail.address) {
                        case INCORRECT: return forward(mail);
                        default: print(mail + "\nDeliver Mail " + mail.id + " normally"); return true;
                    }
                }
            }
        },

        RETURN_TO_SENDER {
            @Override
            boolean handle(Mail mail) {
                switch(mail.returnAddress) {
                    case MISSING: return forward(mail);
                    default: print(mail + "\nReturn Mail " + mail.id + " to sender"); return true;
                }
            }
        },

        DEAD_LETTER {
            @Override
            boolean handle(Mail mail) {
                print(mail + "\nMail " + mail.id + " is a dead letter");
                return true;
            }
        };

        abstract boolean handle(Mail mail);
        boolean forward(Mail mail) {
            int next = this.ordinal() + 1;
            MailHandler[] handlers = MailHandler.values();
            if(next < handlers.length)
                return handlers[next].handle(mail);
            throw new NoMoreHandlerException();
        }
    }

    void handleMail(Mail mail) {
        MailHandler.General_Delivery.handle(mail);
    }
}

class Mail {

    enum GeneralDelivery { YES, NO1, NO2, NO3, NO4, NO5 }
    enum Scannability { UNSCANNABLE, YES1, YES2, YES3, YES4}
    enum Readability { ILLEGIBLE, YES1, YES2, YES3}
    enum Address { INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}
    enum ReturnAddress { MISSING, OK1, OK2, OK3, OK4}
    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;

    private static long count = 0;
    final long id = count++;
    @Override
    public String toString() {
        return "Mail " + id + ":"
                + "\nGeneralDelivery: " + generalDelivery
                + "\nScannability: " + scannability
                + "\nReadability: " + readability
                + "\nAddress: " + address
                + "\nReturnAddress: " + returnAddress;
    }

    static Mail randomMain() {
        Mail mail = new Mail();
        mail.generalDelivery = Enums.random(GeneralDelivery.values());
        mail.scannability = Enums.random(Scannability.values());
        mail.readability = Enums.random(Readability.values());
        mail.address = Enums.random(Address.values());
        mail.returnAddress = Enums.random(ReturnAddress.values());
        return mail;
    }
    static Iterable<Mail> generate(int count) {
        return new Iterable<Mail>() {
            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    int num = count;
                    @Override
                    public boolean hasNext() {
                        return num-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMain();
                    }
                };
            }
        };
    }
}

class NoMoreHandlerException extends RuntimeException {}