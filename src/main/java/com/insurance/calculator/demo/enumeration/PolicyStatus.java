package com.insurance.calculator.demo.enumeration;

import lombok.Getter;

public enum PolicyStatus {

    REGISTERED("Registered"),
    APPROVED("Approved");

    @Getter
    private String value;

    PolicyStatus(String value) {
        this.value = value;
    }
}
