package com.camel.simplecamelspringboot.rest_routes;

import com.camel.simplecamelspringboot.beans.Address;
import com.camel.simplecamelspringboot.processor.InboundMessageProcessor;
import jakarta.jms.JMSException;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import java.net.ConnectException;

@Component
public class RestRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("jetty")
                .host("0.0.0.0")
                .port(8081)
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        onException(JMSException.class, ConnectException.class)
                .handled(true)
                .log(LoggingLevel.INFO, "JMS connection could not be established");


        rest("masterclass")
                .produces("application/json")
                .post("addressURI").type(Address.class)
                .routeId("restRouteId")

                .to("direct:wiretapRoute");


        from("direct:wiretapRoute")
                .wireTap("seda:sendToQueue")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .transform().simple("message processed:${body}")
                .to("direct:persistMessage")
                .end();


        from("direct:persistMessage")
                .routeId("persistMessageRouteId")
                .log(LoggingLevel.INFO, String.valueOf(simple("${body}")))
                //.to("sql:SELECT * FROM camel_masterclass.address?dataSource=#dataSource")
               //.to("jpa:" + Address.class.getName())
                .to("file:src/data/output?fileName=sampleOutput.csv&fileExist=append&appendChars=\\n");


        from("seda:sendToQueue")
                .routeId("sendToQueueRouteId")
                .to("activemq:queue:addressQueue");

//save data to database
//                .to("jpa:"+ Address.class.getName());
        // exchange pattern defines if you expect a response or not
        // send to morethan one endpoint
        // to("seda:toDB") asychronous processing
        // wire tap , it will first send to to route and then to the wiretap route


//        from("direct:persistMessage")
//                .routeId("persistMessageRouteId")
//                .log(LoggingLevel.INFO, String.valueOf(simple("${body}")))
//                .to("jpa:" + Address.class.getName())
//                .process(new InboundMessageProcessor())
//                .log(LoggingLevel.INFO, "Transformed Body:${body}")
//                .convertBodyTo(String.class)
//                .multicast()
//                .to("file:src/data/output?fileName=sampleOutput.csv&fileExist=append&appendChars=\\n")
//                .to("activemq:queue:addressQueue?exchangePattern=InOnly");

    }
}
