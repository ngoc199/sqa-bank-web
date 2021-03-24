package com.banking.banking.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "addresses")
@RequiredArgsConstructor
public class Address {
    @Id
    private String id;

    @NotNull
    private String city;

    @NotNull
    private String district;

    @NotNull
    private String subDistrict;

    @NotNull
    private String detail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void onCreate() {
        id = UUID.randomUUID().toString();
    }
}
