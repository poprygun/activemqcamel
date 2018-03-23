package io.microsamples.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:foo")
                .to("log:sample");

        from("timer://bar?repeatCount=3")
                .setBody(constant("Hello from Camel " + new Date()))
                .to("activemq:foo");
    }
}