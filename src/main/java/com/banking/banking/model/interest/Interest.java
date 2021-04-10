package com.banking.banking.model.interest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DecimalMin(value = "0.001", message = "{validation.rate.min}")
    @DecimalMax(value = "0.5", message = "{validation.rate.max}")
    @Digits(fraction = 3, integer = 1, message = "{validation.rate.digit}")
    private float rate; // Calculated by percent

    @Min(value = 7, message = "{validation.period.min}")
    @Max(value = 730, message = "{validation.period.max}")
    private int period; // Calculated by days

    public float getRate() {
        return rate * 100;
    }

}
