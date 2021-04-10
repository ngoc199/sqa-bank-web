package com.banking.banking.model.user;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.banking.banking.model.user.Permission.*;

public enum Role {
    CUSTOMER(Sets.newHashSet(MY_ACCOUNT_READ)),
    EMPLOYEE_CUSTOMER_CARE(Sets.newHashSet(ACCOUNTS_READ)),
    EMPLOYEE_ACCOUNTANT(Sets.newHashSet(ACCOUNTS_READ, REPORT_EXPORT)),
    EMPLOYEE_MANAGER(Sets.newHashSet(ACCOUNTS_READ, REPORT_EXPORT, CONFIG_WRITE, CONFIG_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());

        // Add this role to the granted authorities
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
