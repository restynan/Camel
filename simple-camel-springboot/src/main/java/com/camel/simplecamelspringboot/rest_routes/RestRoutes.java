package com.camel.simplecamelspringboot.rest_routes;

import com.camel.simplecamelspringboot.beans.Address;
import com.camel.simplecamelspringboot.processor.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("jetty")
                .host("0.0.0.0")
                .port(8085)
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        rest("masterclass")
                .produces("application/json")
                .post("addressURI").type(Address.class)
                .routeId("restRouteId")
                .to("direct:process");

        from("direct:process")
                .routeId("processRouteId")
                .log(LoggingLevel.INFO, "${body}")
                .process(new InboundMessageProcessor())
                .log(LoggingLevel.INFO, "Transformed Body:${body}")
                .convertBodyTo(String.class)
                .multicast()
                    .to("file:src/data/output?fileName=sampleOutput.csv&fileExist=append&appendChars=\\n")
                    .to("activemq:queue:addressQueue?exchangePattern=InOnly");

//save data to database
//                .to("jpa:"+ Address.class.getName());
        // exchange pattern defines if you expect a response or not
        // send to morethan one endpoint
        // to("seda:toDB") asychronous processing
        // wire tap , it will first send to to route and then to the wiretap route
    }
}
