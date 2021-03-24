package com.banking.banking.models.values;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;

import lombok.Data;

@Data
@Embeddable
public class Money {
    @DecimalMin(value = "0", message = "Giá trị tài khoản không được nhỏ hơn 0")
    private float value;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
