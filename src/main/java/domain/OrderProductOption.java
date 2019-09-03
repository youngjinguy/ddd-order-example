package domain;

public class OrderProductOption {

    private long orderProductOptionNo;

    private long deliveryNo;

    private OrderStatus orderStatus;

    public OrderProductOption(long orderProductOptionNo, OrderStatus orderStatus) {
        this.orderProductOptionNo = orderProductOptionNo;
        this.orderStatus = orderStatus;
    }

    public long getOrderProductOptionNo() {
        return orderProductOptionNo;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public long getDeliveryNo() {
        return deliveryNo;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setDeliveryNo(long deliveryNo) {
        this.deliveryNo = deliveryNo;
    }
}
