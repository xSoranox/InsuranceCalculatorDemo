package com.insurance.calculator.demo.calculations;

import com.insurance.calculator.demo.domain.PolicySubObject;
import com.insurance.calculator.demo.enumeration.RiskType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FireInsuredSumCalculatorTest {

    private FireInsuredSumCalculator fireInsuredSumCalculator = new FireInsuredSumCalculator();

    @Test
    public void testCalculateWhenSumInsuredIsLessThanHundred() {
        PolicySubObject fireInsuredSubObject = createPolicySubObject(BigDecimal.TEN);
        BigDecimal actualResult = fireInsuredSumCalculator.calculate(fireInsuredSubObject);
        assertEquals(BigDecimal.valueOf(0.14), actualResult.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testCalculateWhenSumInsuredIsGreaterThanHundred() {
        PolicySubObject fireInsuredSubObject = createPolicySubObject(BigDecimal.valueOf(101));
        BigDecimal actualResult = fireInsuredSumCalculator.calculate(fireInsuredSubObject);
        assertEquals(BigDecimal.valueOf(2.424), actualResult.setScale(3, RoundingMode.HALF_UP));
    }

    @Test
    public void testCalculateWhenSumInsuredIsHundred() {
        PolicySubObject fireInsuredSubObject = createPolicySubObject(BigDecimal.valueOf(100));
        BigDecimal actualResult = fireInsuredSumCalculator.calculate(fireInsuredSubObject);
        assertEquals(BigDecimal.valueOf(1.4), actualResult.setScale(1, RoundingMode.HALF_UP));
    }

    @Test(expected = RuntimeException.class)
    public void testCalculateWhenSumInsuredIsNull() {
        PolicySubObject fireInsuredSubObject = createPolicySubObject(null);
        try {
            fireInsuredSumCalculator.calculate(fireInsuredSubObject);
        } catch (RuntimeException e) {
            assertEquals("Sub-object doesn't have sum insured", e.getMessage());
            throw e;
        }
    }

    private PolicySubObject createPolicySubObject(BigDecimal sumInsured) {
        PolicySubObject fireInsuredSubObject = new PolicySubObject();
        fireInsuredSubObject.setSubObjectName("TV");
        fireInsuredSubObject.setRiskType(RiskType.FIRE);
        fireInsuredSubObject.setSumInsured(sumInsured);
        return fireInsuredSubObject;
    }
}