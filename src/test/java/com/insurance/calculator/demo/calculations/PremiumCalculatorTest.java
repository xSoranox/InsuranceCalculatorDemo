package com.insurance.calculator.demo.calculations;

import com.insurance.calculator.demo.domain.Policy;
import com.insurance.calculator.demo.domain.PolicyObject;
import com.insurance.calculator.demo.domain.PolicySubObject;
import com.insurance.calculator.demo.enumeration.PolicyStatus;
import com.insurance.calculator.demo.enumeration.RiskType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PremiumCalculatorTest {

    @Mock
    private FireInsuredSumCalculator fireInsuredSumCalculator;
    @Mock
    private TheftInsuredSumCalculator theftInsuredSumCalculator;
    @Mock
    private Map<RiskType, InsuredSumCalculator> insuredSumCalculators;

    @InjectMocks
    private PremiumCalculator premiumCalculator;

    @Test
    public void calculate() {
        when(insuredSumCalculators.get(RiskType.FIRE)).thenReturn(fireInsuredSumCalculator);
        when(insuredSumCalculators.get(RiskType.THEFT)).thenReturn(theftInsuredSumCalculator);
        when(fireInsuredSumCalculator.calculate(any())).thenReturn(BigDecimal.TEN);
        when(theftInsuredSumCalculator.calculate(any())).thenReturn(BigDecimal.ONE);

        BigDecimal actualResult = premiumCalculator.calculate(createPolicy());

        assertEquals(BigDecimal.valueOf(11), actualResult);
        verify(fireInsuredSumCalculator).calculate(any());
        verify(theftInsuredSumCalculator).calculate(any());
        verifyNoMoreInteractions(fireInsuredSumCalculator, theftInsuredSumCalculator);
    }

    private Policy createPolicy() {
        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.APPROVED, createPolicyObjects());
        return policy;
    }

    private List<PolicyObject> createPolicyObjects() {
        List<PolicyObject> policyObjects = new ArrayList<>();
        PolicyObject policyObject = new PolicyObject("House", createPolicySubObjects());
        policyObjects.add(policyObject);
        return policyObjects;
    }

    private List<PolicySubObject> createPolicySubObjects() {
        List<PolicySubObject> policySubObjects = new ArrayList<>();
        policySubObjects.add(createPolicySubObject(BigDecimal.TEN, RiskType.FIRE));
        policySubObjects.add(createPolicySubObject(BigDecimal.TEN, RiskType.THEFT));
        return policySubObjects;
    }

    private PolicySubObject createPolicySubObject(BigDecimal sumInsured, RiskType riskType) {
        PolicySubObject insuredSubObject = new PolicySubObject();
        insuredSubObject.setSubObjectName("TV");
        insuredSubObject.setRiskType(riskType);
        insuredSubObject.setSumInsured(sumInsured);
        return insuredSubObject;
    }
}