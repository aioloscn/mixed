package com.aiolos.design.pattern.creational.prototype;

import java.util.Date;

/**
 * @author aiolos
 * 2018-11-07
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Mail mail = new Mail();
        mail.setId(new int[]{111});
        mail.setContent("initialize template");
        Date date = new Date(0L);
        mail.setDate(date);
        for (int i = 0; i < 10; i++) {
            Mail mailTemp = (Mail)mail.clone();
            int[] a = mailTemp.getId();
            a[0] = 333;
            mailTemp.setId(a);
            mailTemp.setName("name" + i);
            mailTemp.setEmailAddress(mailTemp.getName() + "@google.com");
            mailTemp.setContent("mail content");
            mailTemp.getDate().setTime(999999999999L);
            MailUtil.sendMail(mailTemp);
        }
        MailUtil.saveOriginMailRecord(mail);
    }
}
