package com.banking.banking.model.user;

public enum Permission {
    MY_ACCOUNT_READ("my_account:read"),
    ACCOUNTS_READ("accounts:read"),
    REPORT_EXPORT("report:export"),
    CONFIG_READ("config:read"),
    CONFIG_WRITE("config:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
