package com.simple.backend.math;


import mathapp.math_simple_backend_web_service.GetOperationRequest;
import mathapp.math_simple_backend_web_service.OperationResponse;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.Random;


@Endpoint
public class OperationEndpoint {

    private static final String NAMESPACE_URI = "http://mathApp/math-simple-backend-web-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOperationRequest")
    @ResponsePayload
    public OperationResponse doCalculation(@RequestPayload GetOperationRequest getOperationRequest) throws Exception {
        final Long value1 = getOperationRequest.getValue1();
        final Long value2 = getOperationRequest.getValue2();

        final OperationResponse operationResponse = new OperationResponse();
        long operationResponseValue;

        switch (getOperationRequest.getOperation().getValue()) {
            case PLUS -> {
                sleepDuringRandomValueInIntervalFrom(500, 2500);
                operationResponseValue = value1 + value2;
            }
            case SUBTRACT -> {
                sleepDuringRandomValueInIntervalFrom(500, 2500);
                operationResponseValue = value1 - value2;
            }
            case MULTIPLY -> {
                sleepDuringRandomValueInIntervalFrom(500, 2500);
                operationResponseValue = value1 * value2;
            }
            case DIVIDE -> {
                sleepDuringRandomValueInIntervalFrom(500, 2500);
                operationResponseValue = value1 / value2;
            }
            case STALL -> {
                sleepDuringRandomValueInIntervalFrom(31000, 50000);
                operationResponseValue = value1 / value2;
            }
            default -> {
                sleepFor(400);
                throw new Exception("Invalid Math Operation");
            }
        }
        operationResponse.setResponse(operationResponseValue);
        return operationResponse;
    }

    void sleepDuringRandomValueInIntervalFrom(int startFromMillisecond, int stopOnMillisecond) {
        if (startFromMillisecond > stopOnMillisecond) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }

        Double fraction = startFromMillisecond - (stopOnMillisecond + 1) * new Random().nextDouble();
        long definedSleepTime = fraction.longValue() + stopOnMillisecond;
        try {
            Thread.sleep(definedSleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void sleepFor(int sleepMillisecond) {
        try {
            Thread.sleep(sleepMillisecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}