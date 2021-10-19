package com.insurance.calculator.demo.calculations;

import com.insurance.calculator.demo.domain.PolicySubObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class FireInsuredSumCalculator implements InsuredSumCalculator {

    /**
     * PREMIUM_FIRE = SUM_INSURED_FIRE * COEFFICIENT_FIRE
     *
     * @param fireInsuredSubObject sub-object which risk type is FIRE.
     * @return calculated PREMIUM_FIRE amount of one policy sub-object.
     */
    public BigDecimal calculate(PolicySubObject fireInsuredSubObject) {
        BigDecimal sumInsured = Optional.ofNullable(fireInsuredSubObject.getSumInsured())
                .orElseThrow(() -> new RuntimeException("Sub-object doesn't have sum insured"));
        if (sumInsured.doubleValue() > 100) {
            return sumInsured.multiply(BigDecimal.valueOf(0.024));
        }
        return sumInsured.multiply(BigDecimal.valueOf(0.014));
    }
}
