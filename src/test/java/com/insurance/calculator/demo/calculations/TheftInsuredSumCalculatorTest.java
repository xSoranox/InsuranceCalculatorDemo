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
public class TheftInsuredSumCalculatorTest {

    private TheftInsuredSumCalculator theftInsuredSumCalculator = new TheftInsuredSumCalculator();

    @Test
    public void testCalculateWhenSumInsuredIsLessThanFifteen() {
        PolicySubObject theftInsuredSubObject = createPolicySubObject(BigDecimal.valueOf(14));
        BigDecimal actualResult = theftInsuredSumCalculator.calculate(theftInsuredSubObject);
        assertEquals(BigDecimal.valueOf(1.54), actualResult.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testCalculateWhenSumInsuredIsGreaterThanFifteen() {
        PolicySubObject theftInsuredSubObject = createPolicySubObject(BigDecimal.valueOf(16));
        BigDecimal actualResult = theftInsuredSumCalculator.calculate(theftInsuredSubObject);
        assertEquals(BigDecimal.valueOf(0.8), actualResult.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    public void testCalculateWhenSumInsuredIsFifteen() {
        PolicySubObject theftInsuredSubObject = createPolicySubObject(BigDecimal.valueOf(15));
        BigDecimal actualResult = theftInsuredSumCalculator.calculate(theftInsuredSubObject);
        assertEquals(BigDecimal.valueOf(0.75), actualResult.setScale(2, RoundingMode.HALF_UP));
    }

    @Test(expected = RuntimeException.class)
    public void testCalculateWhenSumInsuredIsNull() {
        PolicySubObject theftInsuredSubObject = createPolicySubObject(null);
        try {
            theftInsuredSumCalculator.calculate(theftInsuredSubObject);
        } catch (RuntimeException e) {
            assertEquals("Sub-object doesn't have sum insured", e.getMessage());
            throw e;
        }
    }

    private PolicySubObject createPolicySubObject(BigDecimal sumInsured) {
        PolicySubObject theftInsuredSubObject = new PolicySubObject();
        theftInsuredSubObject.setSubObjectName("TV");
        theftInsuredSubObject.setRiskType(RiskType.THEFT);
        theftInsuredSubObject.setSumInsured(sumInsured);
        return theftInsuredSubObject;
    }
}