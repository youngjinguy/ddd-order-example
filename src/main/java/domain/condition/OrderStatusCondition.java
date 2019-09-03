package domain.condition;

public interface OrderStatusCondition {
    void changeOrderStatus(long orderProductOptionNo);
    void process(String orderNo);
}
