package repo;

import domain.OrderProductOption;
import domain.OrderStatus;

public class OrderProductOptionRepository {

    public OrderProductOption findBy(long orderProductOptionNo) {
        return new OrderProductOption(orderProductOptionNo, OrderStatus.DELIVERY_PREPARE);
    }

}
