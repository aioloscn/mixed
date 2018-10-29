package com.aiolos.design.pattern.creational.builder.v2;

/**
 * Created by aiolos on 2018-10-29.
 */
public class Test {

    public static void main(String[] args) {
        Course course = new Course.CourseBuilder().buildCourseName("java course").buildCoursePPT("java ppt").buildCourseVideo("java video").build();
        System.out.println(course);
    }
}
