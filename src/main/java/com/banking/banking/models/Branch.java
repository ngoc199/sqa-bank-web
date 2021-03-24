package com.banking.banking.models;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "branches")
@RequiredArgsConstructor
@Data
public class Branch {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "branch")
    private Set<User> users;

    @OneToMany(mappedBy = "branch")
    private Set<BankAccount> bankAccounts;

    @PrePersist
    private void onCreate() {
        id = UUID.randomUUID().toString();
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}
