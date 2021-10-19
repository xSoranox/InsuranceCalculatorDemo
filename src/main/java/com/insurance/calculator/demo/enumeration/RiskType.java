package com.insurance.calculator.demo.enumeration;

import lombok.Getter;

public enum RiskType {

    FIRE("Fire"),
    THEFT("Theft");

    @Getter
    private String value;

    RiskType(String value) {
        this.value = value;
    }
}
