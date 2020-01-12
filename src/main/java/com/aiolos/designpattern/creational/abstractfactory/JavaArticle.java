package com.aiolos.designpattern.creational.abstractfactory;

/**
 * Created by aiolos on 2018-10-29.
 */
public class JavaArticle extends Article {
    @Override
    public void produce() {
        System.out.println("java article");
    }
}
