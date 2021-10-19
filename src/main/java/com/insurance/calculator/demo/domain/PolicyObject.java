package com.insurance.calculator.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PolicyObject {

    private String name;
    private List<PolicySubObject> policySubObjects;

}
