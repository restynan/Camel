package com.camel.simplecamelspringboot.beans;

import lombok.Data;

@Data
public class Address {
    private String  name;
    private String houseNumber;
    private String city;
    private String province;
    private String postalCode;

}
