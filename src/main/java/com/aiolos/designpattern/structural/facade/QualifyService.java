package com.aiolos.designpattern.structural.facade;

/**
 * @author aiolos
 * 2018-11-15
 */
public class QualifyService {

    public boolean isAvailable(PointsGift pointsGift) {
        System.out.println("校验" + pointsGift.getName());
        return true;
    }
}
