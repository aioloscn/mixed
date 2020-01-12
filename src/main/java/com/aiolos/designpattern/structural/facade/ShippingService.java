package com.aiolos.designpattern.structural.facade;

/**
 * @author aiolos
 * 2018-11-15
 */
public class ShippingService {

    public String shipGift(PointsGift pointsGift) {
        System.out.println(pointsGift.getName() + "进入物流系统");
        String shippingOrderNo = String.valueOf(System.currentTimeMillis());
        return shippingOrderNo;
    }
}
