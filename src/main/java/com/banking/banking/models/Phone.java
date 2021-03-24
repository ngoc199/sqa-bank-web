package com.banking.banking.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "phones")
@Data
@RequiredArgsConstructor
public class Phone {
    @Id
    private String id;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    void onCreate() {
        id = UUID.randomUUID().toString();
    }
}
