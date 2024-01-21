package com.camel.simplecamelspringboot.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutboundAddress {
    private String  name;
    private String concatenatedAddress;
}
