package com.banking.banking.model.user;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Fullname {
    private String firstName;
    private String middleName;
    private String lastName;

    /**
     * Get the full name in vietnamese order
     * @return fullname
     */
    public String getFullname() {
        return (lastName + " " + middleName + " " + firstName).trim();
    }
}
