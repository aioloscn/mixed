package com.aiolos.designpattern.creational.abstractfactory;

/**
 * Created by aiolos on 2018-10-29.
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }
}
