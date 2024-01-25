package com.camel.simplecamelspringboot.routes;

import com.camel.simplecamelspringboot.beans.Address;
import com.camel.simplecamelspringboot.processor.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BatchJPAProcessingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // camel route runs every 10s ,is going to get the named quey from the named object
        //toD notifies  camel that this url is dynamic
       /* from("timer:readDB?period=10000")
                .routeId("readDbId")
                .to("jpa:"+ Address.class.getName()+"?namedQuery=fetchAllRows")
                .split(body())
                .process(new InboundMessageProcessor())
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "${body}")

                .to("file:src/data/output?fileName=sampleOutput.csv&fileExist=append&appendChars=\\n")
                .toD("jpa:"+Address.class.getName()+"?nativeQuery=DELETE FROM address WHERE id =${header.consumeId}&useExecuteUpdate=true")
                .end();*/

    }
}
