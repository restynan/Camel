package com.camel.simplecamelspringboot.routes;

import com.camel.simplecamelspringboot.beans.Address;
import com.camel.simplecamelspringboot.processor.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReadCSV extends RouteBuilder {
    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void configure() throws Exception {
        from("file:src/data/input?fileName=sampleInput.csv")
                .routeId("legacyFileMovedCSVFile")
                .split(body().tokenize("\n",1,true))
                .unmarshal().bindy(BindyType.Csv, Address.class)
                .process(new InboundMessageProcessor())
                .log(LoggingLevel.INFO, "Transformed Body: ${body}")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "${body}")
               .to("file:src/data/output?fileName=sampleOutput.csv&fileExist=append&appendChars=\\n")
                .end();


        /*
        * exchange -> {
                    String fileData = exchange.getIn().getBody(String.class);
                    logger.info("This is the read fileData:"+ fileData);
        * */

    }
}
