package service;

import domain.condition.BuyConfirmOrderStatusCondition;
import domain.OrderStatus;
import domain.condition.DeliveringOrderStatusCondition;
import domain.condition.OrderStatusCondition;

import java.util.List;
import java.util.Objects;

// 한 주문에는 여러 배송이 있고 한 배송에는 여러 옵션이 있다.
// 옵션 상태가 변경된다.
// 그 옵션의 상태가 배송중으로 바뀔때 같은 배송에 남아있는 옵션중 배송중으로 바뀌지 않는 애들이 나눔배송된다. 상태 바뀌는 애를 배송을 새로 따서 넣어준다.
// 모든 옵션이 구매화정되었을때 해당 주문의 확정일자를 오늘로 업데이트 쳐준다.

public class OrderProductOptionService {

    public void changeOrderStatus(String orderNo, List<Long> orderProductOptionNos, OrderStatus orderStatus) {

        OrderStatusCondition orderStatusCondition = getOrderStatusCondition(orderStatus);
        orderProductOptionNos.forEach(Objects.requireNonNull(orderStatusCondition)::changeOrderStatus);
        orderStatusCondition.process(orderNo);

    }

    private OrderStatusCondition getOrderStatusCondition(OrderStatus orderStatus) {
        switch (orderStatus) {
            case DELIVERY_ING:
                return new DeliveringOrderStatusCondition();
            case BUY_CONFIRM:
                return new BuyConfirmOrderStatusCondition();
        }
        return null;
    }
}
