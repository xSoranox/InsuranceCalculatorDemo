package com.insurance.calculator.demo.domain;

import com.insurance.calculator.demo.enumeration.RiskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@NoArgsConstructor
public class PolicySubObject {

    private String subObjectName;
    private BigDecimal sumInsured;
    private RiskType riskType;

}
