package com.aiolos.designpattern.creational.abstractfactory;

/**
 * Created by aiolos on 2018-10-29.
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
