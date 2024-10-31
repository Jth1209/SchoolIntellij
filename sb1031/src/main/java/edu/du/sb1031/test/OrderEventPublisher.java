package edu.du.sb1031.test;

import edu.du.sb1031.order.Order;
import edu.du.sb1031.shipment.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderEventPublisher {

    final ApplicationEventPublisher eventPublisher;

    public void doStuffAndPublishAnEvent(final Order order){
        OrderEvent orderEvent = new OrderEvent(this,order);
        eventPublisher.publishEvent(orderEvent);
    }
}
