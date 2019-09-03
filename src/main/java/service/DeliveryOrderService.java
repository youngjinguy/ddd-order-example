package service;

import domain.OrderProductOption;
import repo.OrderProductOptionRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DeliveryOrderService {

    private DeliveryService deliveryService = new DeliveryService();
    private OrderProductOptionRepository orderProductOptionRepository = new OrderProductOptionRepository();

    public void changeDelivery(List<Long> orderProductOptionNos) {
        AtomicLong deliveryNo = new AtomicLong();
        orderProductOptionNos.forEach(orderPrductOptionNo -> {
            deliveryService.createDelivery(deliveryNo.get());
            OrderProductOption orderProductOption = orderProductOptionRepository.findBy(orderPrductOptionNo);
            orderProductOption.setDeliveryNo(deliveryNo.get());
            deliveryNo.getAndIncrement();
        });


    }
}
