package com.aiolos.design.pattern.structural.facade;

/**
 * @author aiolos
 * 2018-11-15
 */
public class PointsPaymentService {

    public boolean pay(PointsGift pointsGift) {
        System.out.println("支付" + pointsGift.getName() + "积分");
        return true;
    }
}
