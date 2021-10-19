package com.insurance.calculator.demo.controller;

import com.insurance.calculator.demo.calculations.PremiumCalculator;
import com.insurance.calculator.demo.domain.Policy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PolicyController {

    @Autowired
    private PremiumCalculator premiumCalculator;

    @PostMapping("/policies")
    public BigDecimal getPremium(@RequestBody Policy policy) {
        return premiumCalculator.calculate(policy);
    }
}
