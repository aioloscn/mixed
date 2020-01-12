package com.aiolos.designpattern.structural.facade;

/**
 * @author aiolos
 * 2018-11-15
 */
public class Test {

    public static void main(String[] args) {
        PointsGift pointsGift = new PointsGift("fabric");
        GiftExchangeService giftExchangeService = new GiftExchangeService();

        giftExchangeService.giftExchange(pointsGift);
    }
}
