package com.aiolos.design.pattern.creational.simplefactory;

/**
 * @author aiolos
 * 2018-10-19
 */
public class VideoFactory {
    public Video getVideo(String type) {
        if ("java".equalsIgnoreCase(type)) {
            return new JavaVideo();
        } else if ("python".equalsIgnoreCase(type)) {
            return new PythonVideo();
        }
        return null;
    }
}
