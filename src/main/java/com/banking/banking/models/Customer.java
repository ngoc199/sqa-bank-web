package com.banking.banking.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
public class Customer extends User {
    
    private static final long serialVersionUID = 1L;

    @NotNull
    @Getter
    @Setter
    private Date dateOfBirth;

    @NotNull
    @Pattern(regexp = "^\\d{12}$", message = "Chứng minh thư phải có 12 số")
    @Getter
    @Setter
    private String idNumber;

    @OneToOne(mappedBy = "customer")
    @Getter
    @Setter
    private CheckingAccount checkingAccount;

    @OneToMany(mappedBy = "customer")
    @Getter
    @Setter
    private Set<SavingAccount> savingAccounts;

    @OneToMany(mappedBy = "customer")
    @Getter
    @Setter
    private Set<LoanAccount> loanAccounts;
}
