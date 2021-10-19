package com.insurance.calculator.demo.enumeration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumerationTest {

    @Test
    public void testPolicyStatus() {
        assertEquals("Registered", PolicyStatus.REGISTERED.getValue());
        assertEquals("Approved", PolicyStatus.APPROVED.getValue());
    }

    @Test
    public void testRiskType() {
        assertEquals("Fire", RiskType.FIRE.getValue());
        assertEquals("Theft", RiskType.THEFT.getValue());
    }
}
