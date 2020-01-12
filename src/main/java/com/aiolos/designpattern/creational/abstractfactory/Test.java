package com.aiolos.designpattern.creational.abstractfactory;

/**
 * Created by aiolos on 2018-10-29.
 */
public class Test {
    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();;
        article.produce();
    }
}
