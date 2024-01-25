package com.camel.simplecamelspringboot.routes;

import com.camel.simplecamelspringboot.processor.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReadCSV extends RouteBuilder {
    Logger logger = LoggerFactory.getLogger(getClass());
    //use beanio to cnvert every row int a java object
    BeanIODataFormat inboundDataFormat = new BeanIODataFormat("inboundMessageBeanIOMapping.xml",
            "inputMessageStream");

    @Override
    public void configure() throws Exception {
       /* from("file:src/data/input?fileName=sampleInput.csv")
                .routeId("legacyFileMovedCSVFile")
                .split(body().tokenize("\n",1,true))
                .unmarshal(inboundDataFormat)
                .process(new InboundMessageProcessor())
                .log(LoggingLevel.INFO, "Transformed Body: ${body}")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "${body}")
               .to("file:src/data/output?fileName=sampleOutput.csv&fileExist=append&appendChars=\\n")
                .end();
*/


        /*
        * exchange -> {
                    String fileData = exchange.getIn().getBody(String.class);
                    logger.info("This is the read fileData:"+ fileData);
        * */

    }
}
