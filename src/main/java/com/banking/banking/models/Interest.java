package com.banking.banking.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.banking.banking.models.values.InterestType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "interests")
@Data
@RequiredArgsConstructor
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DecimalMin(value = "1.00", message = "Lãi suất không được nhỏ hơn 1%")
    @DecimalMax(value = "5.00")
    // Rate is calculated in percentage
    private float rate;

    // Period is calculated by days
    private int minPeriod;
    private int maxPeriod;

    private InterestType type;
}
