package com.camel.simplecamelspringboot.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
@UseAdviceWith
@CamelSpringBootTest
public class LegacyFileRouteTest {
    @Autowired
    CamelContext context;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;

// Mocking only the destination
    @Test
    public void testLegacyFileRoute() throws Exception {
        //Set up the mock
        String expectedBody ="This is an input file that will be processed and moved to output file";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);

        // Tweak the route definition
        AdviceWith.adviceWith(context, "legacyFileMoveRouteId", routeBuilder ->{
            routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
        });
       // Start the context and validate is mock asserted
        context.start();
        mockEndpoint.assertIsSatisfied();
    }
    @AfterEach()
    public void tearDown() throws IOException {
        File file = new File("./sampleInput.csv");

        String path = file.getAbsolutePath();

            boolean result = Files.deleteIfExists(Paths.get(path));
            if (result) {
                System.out.println(file.getName()+" is deleted!");
            }

    }

}
