package com.insurance.calculator.demo.calculations;

import com.insurance.calculator.demo.domain.PolicySubObject;

import java.math.BigDecimal;

public interface InsuredSumCalculator {

    BigDecimal calculate(PolicySubObject insuredSubObject);
}
