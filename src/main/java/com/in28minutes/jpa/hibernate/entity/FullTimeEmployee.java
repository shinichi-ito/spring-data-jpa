package com.in28minutes.jpa.hibernate.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class FullTimeEmployee extends Employee {
    protected FullTimeEmployee(){}
    public FullTimeEmployee(String name, BigDecimal salary){
        super(name);
        this.salary = salary;
    }
    private BigDecimal salary;
}
