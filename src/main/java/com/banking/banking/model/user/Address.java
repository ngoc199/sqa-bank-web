package com.banking.banking.model.user;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

    private String country;
    private String city;
    private String district;
    private String subDistrict;
    private String details;
    private String zipCode;
}
