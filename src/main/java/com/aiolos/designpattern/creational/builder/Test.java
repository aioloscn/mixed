package com.aiolos.designpattern.creational.builder;

/**
 * Created by aiolos on 2018-10-29.
 */
public class Test {

    public static void main(String[] args) {
        CourseBuilder courseBuilder = new CourseActualBuilder();
        Coach coach = new Coach();
        coach.setCourseBuilder(courseBuilder);
        Course course = coach.makeCourse("java ", "java ppt", "java video", "java article", "java qa");
        System.out.println(course);
    }
}
