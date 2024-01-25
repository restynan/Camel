package com.camel.simplecamelspringboot.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class QueueReceiverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:addressQueue")
                .routeId("queueReceiverId")
                .log(LoggingLevel.INFO,">>>>>>> Message received from Queue: ${body}");

    }
}
