package com.aiolos.designpattern.behavioral.observer;

/**
 * @author Aiolos
 * @date 2020/11/10 12:01 上午
 */
public class Question {

    private String username;

    private String questionContent;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
