package com.banking.banking.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.banking.banking.models.composite.LoginInfoId;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "login_infos")
@Data
@RequiredArgsConstructor
@IdClass(LoginInfoId.class)
public class LoginInfo {

    @Id
    private String device;

    @Id
    private String city;

    @NotNull
    private String ipAddress;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

    @NotNull
    private LocalDateTime lastLogin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void onCreate() {
        lastLogin = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        lastLogin = LocalDateTime.now();
    }
}
