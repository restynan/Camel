package com.camel.simplecamelspringboot.processor;

import com.camel.simplecamelspringboot.beans.Address;
import com.camel.simplecamelspringboot.beans.OutboundAddress;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundMessageProcessor implements Processor {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void process(Exchange exchange) throws Exception {
        Address address = exchange.getIn().getBody(Address.class);
        exchange.getIn().setBody( new OutboundAddress(address.getName(),returnOutboundAddress(address)));
        exchange.getIn().setHeader("consumedId", address.getId());


    }
    private String returnOutboundAddress(Address address){
        StringBuilder concatenatedAddress  = new StringBuilder(200);
        concatenatedAddress.append(" "+ address.getHouseNumber());
        concatenatedAddress.append(" "+ address.getCity());
        concatenatedAddress.append(" "+ address.getProvince());
        concatenatedAddress.append(" "+ address.getPostalCode());

        return concatenatedAddress.toString();
    }
}
