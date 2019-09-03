package domain.condition;

import domain.Order;
import domain.OrderProductOption;
import domain.OrderStatus;
import repo.OrderProductOptionRepository;
import repo.OrderRepository;

import java.time.LocalDateTime;

public class BuyConfirmOrderStatusCondition implements OrderStatusCondition {

    private OrderRepository orderRepository = new OrderRepository();
    private OrderProductOptionRepository orderProductOptionRepository = new OrderProductOptionRepository();

    @Override
    public void changeOrderStatus(long orderProductOptionNo) {
        OrderProductOption orderProductOption = orderProductOptionRepository.findBy(orderProductOptionNo);
        orderProductOption.setOrderStatus(OrderStatus.BUY_CONFIRM);
    }

    @Override
    public void process(String orderNo) {
        Order order = orderRepository.findBy(orderNo);
        if (order.getOrderProductOptions().stream().allMatch(orderProductOption -> orderProductOption.getOrderStatus() == OrderStatus.BUY_CONFIRM)) {
            order.setConfirmDoneYmdt(LocalDateTime.now());
        }
    }
}
