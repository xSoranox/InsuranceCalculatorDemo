package com.insurance.calculator.demo.domain;

import com.insurance.calculator.demo.enumeration.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    private String policyNumber;
    private PolicyStatus policyStatus;
    private List<PolicyObject> policyObjects;

}
