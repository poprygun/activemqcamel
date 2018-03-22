package io.microsamples.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderSenderTest {

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private OrderConsumer orderConsumer;


    @Test
    public void shouldReceive() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        orderConsumer.setCountDownLatch(countDownLatch);

        Order myMessage = new Order("Sending JMS Message using Embedded activeMQ", new Date());
        orderSender.send(myMessage);

        countDownLatch.await();

        assertThat(orderConsumer.received).isNotNull();
    }

}