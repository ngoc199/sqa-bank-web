package com.banking.banking.model.user;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Phone {
    private String phoneNumber;
}
