package com.aiolos.design.pattern.creational.prototype;

import java.util.Arrays;
import java.util.Date;

/**
 * @author aiolos
 * 2018-11-07
 */
public class Mail implements Cloneable {

    private int[] id;
    private String name;
    private String emailAddress;
    private String content;
    private Date date;

    public Mail() {
        System.out.println("Mail Class Contructor");
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + Arrays.toString(id) +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}' + super.toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("clone mail object");
        Mail mailClone = (Mail)super.clone();
        mailClone.date = (Date)mailClone.date.clone();
        return mailClone;
    }
}
