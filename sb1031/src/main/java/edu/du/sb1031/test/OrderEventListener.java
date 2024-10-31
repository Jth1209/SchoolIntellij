package edu.du.sb1031.test;

import edu.du.sb1031.order.Order;
import edu.du.sb1031.shipment.Shipment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {

    final private EntityManagerFactory emf;

    @EventListener
    public void handleContextStart(OrderEvent orderEvent) {

        Order order = orderEvent.getOrder();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Shipment shipment = Shipment.builder().id(order.getId()).productName(order.getProductName()).quantity(order.getQuantity()).price(order.getPrice()).status("주문 대기중").build();

        em.persist(shipment);
        em.getTransaction().commit();
    }
}
