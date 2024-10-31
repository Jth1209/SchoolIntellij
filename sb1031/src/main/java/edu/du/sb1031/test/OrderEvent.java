package edu.du.sb1031.test;

import edu.du.sb1031.order.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderEvent extends ApplicationEvent {

    private Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
}
