package com.aiolos.designpattern.creational.factorymethod;

/**
 * @author aiolos
 * 2018-10-19
 */
public class PythonVideo extends Video {
    @Override
    public void produce() {
        System.out.println("Python");
    }
}
