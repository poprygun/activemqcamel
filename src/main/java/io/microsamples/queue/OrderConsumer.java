package io.microsamples.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Session;

import java.util.concurrent.CountDownLatch;

import static io.microsamples.config.ActiveMQConfig.ORDER_QUEUE;

@Component
public class OrderConsumer {

    private static Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    Order received;
    private CountDownLatch countDownLatch;


    @JmsListener(destination = ORDER_QUEUE)
    public void receiveMessage(@Payload Order order,
                               @Headers MessageHeaders headers,
                               Message message, Session session) {
        received = order;

        log.info("received <" + order + ">");

        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("######          Message Details           #####");
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("headers: " + headers);
        log.info("message: " + message);
        log.info("session: " + session);
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");

        if(countDownLatch != null) {
            countDownLatch.countDown();
        }

    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
