package com.banking.banking.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.banking.banking.models.values.Way;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "saving_accounts")
public class SavingAccount extends BankAccount {

    @Getter
    @Setter
    private LocalDateTime dueDate;

    @Getter
    @Setter
    private Way wayReceive;

    @Getter
    @Setter
    // Period calculate by days
    private int period;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @Getter
    @Setter
    private Customer customer;

    @Getter
    @Setter
    private LocalDateTime nextReceiveDate;
}
