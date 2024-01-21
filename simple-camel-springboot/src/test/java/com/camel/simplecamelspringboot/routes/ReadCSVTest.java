package com.camel.simplecamelspringboot.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@UseAdviceWith
@CamelSpringBootTest
public class ReadCSVTest {
    @Autowired
    CamelContext context;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;

    @Autowired
    ProducerTemplate producerTemplate;
    @Test
    public void testFileByMockingFromEndPoint() throws Exception{
        //Set up the mock
        String expectedBody ="OutboundAddress(name=Mike, concatenatedAddress= 111 Toronto ON MG789)";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);

        // Tweak the route definition
        AdviceWith.adviceWith(context, "legacyFileMovedCSVFile", routeBuilder ->{
            routeBuilder.replaceFromWith("direct:mockStart");
            routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
        });
        // Start the context and validate is mock asserted
        context.start();
        producerTemplate.sendBody("direct:mockStart","name,house_number,city,province,postal_code\n" +
                "Mike,111,Toronto,ON,MG789");
        mockEndpoint.assertIsSatisfied();

    }
}
