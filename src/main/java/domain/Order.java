package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private String orderNo;

    private List<OrderProductOption> orderProductOptions;

    private LocalDateTime confirmDoneYmdt;

    public Order(String orderNo, List<OrderProductOption> orderProducts) {
        this.orderNo = orderNo;
        this.orderProductOptions = orderProducts;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public List<OrderProductOption> getOrderProductOptions() {
        return orderProductOptions;
    }

    public void setConfirmDoneYmdt(LocalDateTime confirmDoneYmdt) {
        this.confirmDoneYmdt = confirmDoneYmdt;
    }
}
