package com.camel.simplecamelspringboot.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.NamedQuery;

@Data
@Entity
@Table(name = "ADDRESS")
@NamedQuery(name="fetchAllRows", query="SELECT x FROM Address x")
public class Address {
    @Id
    private Long id;
    private String name;
    @Column(name = "house_number")
    private String houseNumber;
    private String city;
    private String province;
    @Column(name = "postal_code")
    private String postalCode;

}
