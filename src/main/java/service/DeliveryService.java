package service;

import domain.Delivery;
import repo.DeliveryRepository;

public class DeliveryService {

    private DeliveryRepository deliveryRepository = new DeliveryRepository();

    public void createDelivery(long deliveryNo) {
        Delivery delivery = new Delivery(deliveryNo);
        deliveryRepository.save(delivery);
    }
}
