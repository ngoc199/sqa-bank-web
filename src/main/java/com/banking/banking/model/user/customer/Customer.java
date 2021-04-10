package com.banking.banking.model.user.customer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.banking.banking.model.bankaccount.BankAccount;
import com.banking.banking.model.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccounts;

}
