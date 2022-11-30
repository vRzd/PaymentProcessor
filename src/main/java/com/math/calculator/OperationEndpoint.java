package com.math.calculator;


import com.math.calculator.service.CalculationService;
import mathapp.math_calculation_web_service.CalculationRequest;
import mathapp.math_calculation_web_service.CalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.Optional;

@Endpoint
public class OperationEndpoint {

    private static final String NAMESPACE_URI = "http://mathApp/math-calculation-web-service";

    @Autowired
    private CalculationService calculationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "calculationRequest")
    @ResponsePayload

    public CalculationResponse doCalculation(@RequestPayload CalculationRequest getOperationRequest) throws InterruptedException {

        final Float firstValue = Float.valueOf(Optional.ofNullable(getOperationRequest.getFirstValue())
                .orElseThrow(() -> new UnsupportedOperationException()));
        final Float secondValue = Float.valueOf(Optional.ofNullable(getOperationRequest.getSecondValue())
                .orElseThrow(() -> new UnsupportedOperationException()));

        return calculationService.calculateMathOperation(firstValue, secondValue, getOperationRequest.getOperation());

    }
}