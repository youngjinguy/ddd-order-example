package domain.condition;

import domain.Order;
import domain.OrderProductOption;
import domain.OrderStatus;
import repo.OrderProductOptionRepository;
import repo.OrderRepository;
import service.DeliveryOrderService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveringOrderStatusCondition implements OrderStatusCondition {

    private final OrderStatus BEFORE_ORDER_STATUS = OrderStatus.DELIVERY_PREPARE;

    private OrderRepository orderRepository = new OrderRepository();

    private OrderProductOptionRepository orderProductOptionRepository = new OrderProductOptionRepository();

    private DeliveryOrderService deliveryOrderService = new DeliveryOrderService();

    @Override
    public void changeOrderStatus(long orderProductOptionNo) {
        OrderProductOption orderProductOption = orderProductOptionRepository.findBy(orderProductOptionNo);
        orderProductOption.setOrderStatus(OrderStatus.DELIVERY_ING);
    }

    @Override
    public void process(String orderNo) {
        Order order = orderRepository.findBy(orderNo);
        // 배송번호 기준으로 옵션 그룹핑
        Map<Long, List<OrderProductOption>> deliveryNoWithOrderProductOptionMap = order.getOrderProductOptions()
                .stream()
                .collect(Collectors.groupingBy(OrderProductOption::getDeliveryNo, Collectors.toList()));

        // 배송번호는 같지만 배송상태가 배송준비중인 것들은 새로운 배송세팅.
        // 배송부분 도메인 나눠야 할 거 같은데 마땅히 떠오르지 않음.
        for (Long deliveryNo : deliveryNoWithOrderProductOptionMap.keySet()) {
            List<OrderProductOption> orderProductOptions = deliveryNoWithOrderProductOptionMap.get(deliveryNo);
            List<Long> orderProductOptionNos = orderProductOptions
                    .stream()
                    .filter(orderProductOption -> orderProductOption.getOrderStatus() == BEFORE_ORDER_STATUS)
                    .map(OrderProductOption::getOrderProductOptionNo)
                    .collect(Collectors.toList());

            deliveryOrderService.changeDelivery(orderProductOptionNos);
        }

    }
}
