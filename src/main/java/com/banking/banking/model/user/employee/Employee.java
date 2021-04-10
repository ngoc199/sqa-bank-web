package com.banking.banking.model.user.employee;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.banking.banking.model.user.User;

@Entity
@Table(name = "employees")
public class Employee extends User {

}
