package com.insurance.calculator.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.calculator.demo.calculations.PremiumCalculator;
import com.insurance.calculator.demo.configuration.MyConfig;
import com.insurance.calculator.demo.domain.Policy;
import com.insurance.calculator.demo.domain.PolicyObject;
import com.insurance.calculator.demo.domain.PolicySubObject;
import com.insurance.calculator.demo.enumeration.PolicyStatus;
import com.insurance.calculator.demo.enumeration.RiskType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyConfig.class)
@WebMvcTest(controllers = PolicyController.class)
public class PolicyControllerTest {

    @Autowired
    private PolicyController policyController;

    @Autowired
    private PremiumCalculator premiumCalculator;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPremium() throws Exception {
        Policy policy = createPolicy();

        String response = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/policies")
                .content(objectMapper.writeValueAsString(policy))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("1.240", response);
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