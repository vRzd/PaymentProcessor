package com.app.calculation;


import com.app.calculation.service.CalculationService;
import mathapp.math_calculation_web_service.CalculativeRequest;
import mathapp.math_calculation_web_service.CalculativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.Optional;

@Endpoint
public class OperationEndpoint {

    private static final String NAMESPACE_URI = "http://mathApp/math-calculation-web-service";

    @Autowired
    private CalculationService calculationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "calculativeRequest")
    @ResponsePayload
    public CalculativeResponse doCalculation(@RequestPayload CalculativeRequest calculationRequest) throws InterruptedException {

        final Float firstValue = Optional.ofNullable(calculationRequest.getFirstValue())
                .orElseThrow(UnsupportedOperationException::new);
        final Float secondValue = Optional.ofNullable(calculationRequest.getSecondValue())
                .orElseThrow(UnsupportedOperationException::new);

        return calculationService.calculateMathOperation(firstValue, secondValue, calculationRequest.getOperationType().getValue());

    }
}