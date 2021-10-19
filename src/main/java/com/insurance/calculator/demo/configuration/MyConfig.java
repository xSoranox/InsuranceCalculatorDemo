package com.insurance.calculator.demo.configuration;

import com.insurance.calculator.demo.calculations.FireInsuredSumCalculator;
import com.insurance.calculator.demo.calculations.InsuredSumCalculator;
import com.insurance.calculator.demo.calculations.TheftInsuredSumCalculator;
import com.insurance.calculator.demo.enumeration.RiskType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@ComponentScan("com.insurance.calculator.demo")
public class MyConfig {

    /**
     * @return map collection of risk type and corresponding calculator
     */
    @Bean
    public Map<RiskType, InsuredSumCalculator> insuredSumCalculators() {
        EnumMap<RiskType, InsuredSumCalculator> insuredSumCalculators = new EnumMap<>(RiskType.class);
        insuredSumCalculators.put(RiskType.FIRE, new FireInsuredSumCalculator());
        insuredSumCalculators.put(RiskType.THEFT, new TheftInsuredSumCalculator());
        return insuredSumCalculators;
    }
}
