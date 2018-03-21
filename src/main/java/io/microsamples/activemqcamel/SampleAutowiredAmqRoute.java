package io.microsamples.activemqcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SampleAutowiredAmqRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:foo")
                .to("log:sample");

        from("timer:bar")
                .setBody(constant("Hello from Camel"))
                .to("activemq:foo");
    }

}