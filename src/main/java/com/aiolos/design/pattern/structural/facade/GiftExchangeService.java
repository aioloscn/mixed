package com.aiolos.design.pattern.structural.facade;

/**
 * @author aiolos
 * 2018-11-15
 */
public class GiftExchangeService {
    private QualifyService qualifyService = new QualifyService();
    private PointsPaymentService pointsPaymentService = new PointsPaymentService();
    private ShippingService shippingService = new ShippingService();

    public void giftExchange(PointsGift pointsGift) {
        if (qualifyService.isAvailable(pointsGift)) {
            // 资格校验通过
            if (pointsPaymentService.pay(pointsGift)) {
                // 支付积分成功
                String shippingOrderNo = shippingService.shipGift(pointsGift);
                System.out.println("下单成功，订单号：" + shippingOrderNo);
            }
        }
    }
}
