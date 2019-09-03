package repo;

import domain.Order;
import domain.OrderProductOption;
import domain.OrderStatus;

import java.util.Arrays;

public class OrderRepository {

    public Order findBy(String orderNo) {

        OrderProductOption orderProductOption1 = new OrderProductOption(1L, OrderStatus.DELIVERY_PREPARE);
        OrderProductOption orderProductOption2 = new OrderProductOption(2L, OrderStatus.DELIVERY_PREPARE);

        return new Order(orderNo, Arrays.asList(orderProductOption1, orderProductOption2));
    }
}
