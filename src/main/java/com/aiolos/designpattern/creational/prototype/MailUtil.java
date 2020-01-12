package com.aiolos.designpattern.creational.prototype;

import java.text.MessageFormat;

/**
 * @author aiolos
 * 2018-11-07
 */
public class MailUtil {

    public static void sendMail(Mail mail) {
        String outputContent = "send to {0}, address is {1}, email content is {2}";
        System.out.println(MessageFormat.format(outputContent, mail.getName(), mail.getEmailAddress(), mail.getContent()));
    }

    public static void saveOriginMailRecord(Mail mail) {
        System.out.println("save origin mail record, origin mail content = " + mail.getContent());
    }
}
