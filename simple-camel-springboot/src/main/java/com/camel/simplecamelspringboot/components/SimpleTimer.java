package com.camel.simplecamelspringboot.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleTimer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //2000 means 2 seconds, prints the log every 2 seconds
        //route started and stopped -> reason apache camel runs within springboot context, camel has the ability to prevent JVM from terminationg
        //route ID are generally best practice
        from("timer:simpletimer?period=2000")
                .routeId("simpleTimerId")
                .setBody(constant("Hello World"))
                .log(LoggingLevel.INFO, "S{body}");

    }
}
