package com.aiolos.designpattern.creational.factorymethod;

/**
 * @author aiolos
 * 2018-10-19
 */
public class Test {
    public static void main(String[] args) {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }
}
