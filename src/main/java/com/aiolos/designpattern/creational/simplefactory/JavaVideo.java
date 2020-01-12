package com.aiolos.designpattern.creational.simplefactory;

/**
 * @author aiolos
 * 2018-10-19
 */
public class JavaVideo extends Video {
    @Override
    public void produce() {
        System.out.println("Java");
    }
}
