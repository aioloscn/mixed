package com.aiolos.design.pattern.creational.simplefactory;

/**
 * @author aiolos
 * 2018-10-19
 */
public class Test {
    public static void main(String[] args) {
        VideoFactory vf = new VideoFactory();
        Video video = vf.getVideo("java");
        if (video == null)
            return;
        video.produce();
    }
}
