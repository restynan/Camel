package com.camel.simplecamelspringboot;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@UseAdviceWith
@CamelSpringBootTest
class SimpleCamelSpringbootApplicationTests {/*

	@Autowired
	CamelContext context;

	@EndpointInject("mock:result")
	protected MockEndpoint mockEndpoint;

	@Test
	public void testSimpleTimer() throws Exception {
		 String expectedBody ="Hello World";
		 mockEndpoint.expectedBodiesReceived(expectedBody);
		 mockEndpoint.expectedMinimumMessageCount(1);

		 // add mockEndpoint at the  end of the route
		 AdviceWith.adviceWith(context, "simpleTimerId", routeBuilder ->{
			 routeBuilder.weaveAddLast().to(mockEndpoint);
		 });

		 context.start();
		 mockEndpoint.assertIsSatisfied();
	}







	@Test
	void contextLoads() {
	}
*/
}