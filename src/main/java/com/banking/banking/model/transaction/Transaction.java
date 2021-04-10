package com.banking.banking.model.transaction;

import java.time.LocalDateTime;
import java.util.Currency;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.banking.banking.model.bankaccount.BankAccount;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Currency moneyTransfer;

    @OneToOne(targetEntity = TransactionType.class, cascade = CascadeType.ALL)
    private TransactionType type;

    @ManyToOne(targetEntity = BankAccount.class, cascade = CascadeType.ALL)
    private BankAccount transferAccount;

    @ManyToOne(targetEntity = BankAccount.class, cascade = CascadeType.ALL)
    private BankAccount receiveAccount;

    @CreationTimestamp
    private LocalDateTime transactionTime;
}
