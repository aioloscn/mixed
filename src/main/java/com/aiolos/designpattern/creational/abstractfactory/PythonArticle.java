package com.aiolos.designpattern.creational.abstractfactory;

/**
 * Created by aiolos on 2018-10-29.
 */
public class PythonArticle extends Article {
    @Override
    public void produce() {
        System.out.println("python article");
    }
}
