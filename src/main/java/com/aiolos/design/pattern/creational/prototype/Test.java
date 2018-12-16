package com.aiolos.design.pattern.creational.prototype;

import com.aiolos.design.pattern.creational.singleton.HungrySingleton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author aiolos
 * 2018-11-07
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Mail mail = new Mail();
//        mail.setId(new int[]{111});
//        mail.setContent("initialize template");
//        Date date = new Date(0L);
//        mail.setDate(date);
//        for (int i = 0; i < 10; i++) {
//            Mail mailTemp = (Mail)mail.clone();
//            int[] a = mailTemp.getId();
//            a[0] = 333;
//            mailTemp.setId(a);
//            mailTemp.setName("name" + i);
//            mailTemp.setEmailAddress(mailTemp.getName() + "@google.com");
//            mailTemp.setContent("mail content");
//            mailTemp.getDate().setTime(999999999999L);
//            MailUtil.sendMail(mailTemp);
//        }
//        MailUtil.saveOriginMailRecord(mail);

        // 原型模式下破坏单例模式
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        Method method = hungrySingleton.getClass().getDeclaredMethod("clone");
        method.setAccessible(true);
        HungrySingleton cloneHungrySingleton = (HungrySingleton) method.invoke(HungrySingleton.getInstance());
        System.out.println(hungrySingleton);
        System.out.println(cloneHungrySingleton);
    }
}
