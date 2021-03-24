package com.banking.banking.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "checking_accounts")
public class CheckingAccount extends BankAccount {
    @Getter
    @Setter
    private float fee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @Getter
    @Setter
    private Customer customer;
}
