package it.stage.microservices.order.service;

import it.stage.microservices.order.entity.Order;
import it.stage.microservices.order.exception.ImpossibleAddOrderException;
import it.stage.microservices.order.exception.OrderNotFoundException;
import it.stage.microservices.order.payload.request.OrderRequest;
import it.stage.microservices.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    private static final String KAFKA_TOPIC = "mail-service";


    public Order insertOrder(OrderRequest orderRequest) throws ImpossibleAddOrderException {

        Order newOrder = new Order();
        newOrder.setProductId(orderRequest.getProductId());
        newOrder.setCustomerEmail(orderRequest.getCustomerEmail());
        newOrder.setQuantity(orderRequest.getQuantity());
        newOrder.setOrderPay(false);

        newOrder.setDateOrder(Calendar.getInstance().getTime());

        newOrder = orderRepository.save(newOrder);

        if(newOrder == null)
            throw new ImpossibleAddOrderException("Impossible to add this order");

        return newOrder;
    }

    public Order getOrderById(Long orderId) throws OrderNotFoundException {

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty())
            throw new OrderNotFoundException("This order not exist.");

        return order.get();
    }

    public void updateOrderPaymentStatus(Long orderId) throws OrderNotFoundException {

        Order order = getOrderById(orderId);

        order.setOrderPay(true);

        orderRepository.save(order);

        // Send the order with kafka...
        kafkaTemplate.send(KAFKA_TOPIC, order);
        LOGGER.info("Customer will receive email: {}", order.getCustomerEmail());

    }
}
