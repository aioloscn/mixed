package com.aiolos.designpattern.creational.factorymethod;

/**
 * Created by aiolos on 2018-10-29.
 */
public class PythonVideoFactory extends VideoFactory {

    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
