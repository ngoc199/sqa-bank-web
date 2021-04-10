package com.banking.banking.model.user;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class User {
    @Id
    private String id;

    @Embedded
    private Fullname fullname;

    @Embedded
    private Phone phone;

    @Embedded
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UserLogin.class, orphanRemoval = true)
    private UserLogin userLogin;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
