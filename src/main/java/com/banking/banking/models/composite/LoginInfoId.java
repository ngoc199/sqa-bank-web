package com.banking.banking.models.composite;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginInfoId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String device;
    private String city;
}
