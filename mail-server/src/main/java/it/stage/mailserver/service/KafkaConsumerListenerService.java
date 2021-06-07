package it.stage.mailserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.stage.mailserver.bean.OrderBean;
import it.stage.mailserver.constant.KafkaConstant;
import it.stage.mailserver.payload.request.ConfirmationOrderRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class KafkaConsumerListenerService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MailService mailService;


    // Annotation required to listen
    // the message from Kafka server
    @KafkaListener(topics = KafkaConstant.KAFKA_TOPIC, groupId = KafkaConstant.GROUP_ID)
    public void consumeOrder(ConsumerRecord<String, String> record)
            throws JsonProcessingException, MessagingException {

        // Convert a jso string to an Object(OrderBean)
        OrderBean orderBean = new ObjectMapper().readValue(record.value(), OrderBean.class);

        // send the mail
        mailService.sendConfirmationOrderEmail(ConfirmationOrderRequest.createFrom(orderBean));

        LOGGER.info("Mail Sent to: " + orderBean.getCustomerEmail());
    }
}
