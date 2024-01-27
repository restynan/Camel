package com.camel.simplecamelspringboot.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
//import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;

@Data
@Entity
@Table(name = "ADDRESS")

@CsvRecord(separator = ",")
//@NamedQuery(name="fetchAllRows", query="SELECT x FROM Address x")
public class Address implements Serializable {
    @Id
    private Long id;
    @DataField(pos = 1)
    private String name;
    @DataField(pos = 2)
    @Column(name = "house_number")
    private String houseNumber;
    @DataField(pos = 3)
    private String city;
    @DataField(pos = 4)
    private String province;
    @DataField(pos = 5)
    @Column(name = "postal_code")
    private String postalCode;

}
