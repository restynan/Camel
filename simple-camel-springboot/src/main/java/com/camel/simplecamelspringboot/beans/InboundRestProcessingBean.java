package com.camel.simplecamelspringboot.beans;

import org.apache.camel.Exchange;

public class InboundRestProcessingBean {
    public void validate(Exchange exchange){
        OutboundAddress address = exchange.getIn().getBody(OutboundAddress.class);
        exchange.getIn().setHeader("name",address.getName());

    }
}
