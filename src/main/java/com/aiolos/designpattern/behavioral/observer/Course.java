package com.aiolos.designpattern.behavioral.observer;

/**
 * @author Aiolos
 * @date 2020/11/9 11:45 下午
 */
public class Course {

    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}
