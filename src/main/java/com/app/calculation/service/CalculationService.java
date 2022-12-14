package com.app.calculation.service;

import mathapp.math_calculation_web_service.CalculativeResponse;
import mathapp.math_calculation_web_service.OperationType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CalculationService {

    @Value("${min.math.operation.time}")
    private int minMathOperationTime;
    @Value("${max.math.operation.time}")
    private int maxMathOperationTime;
    @Value("${min.other.operation.time}")
    private int minOtherOperationTime;
    @Value("${max.other.operation.time}")
    private int maxOtherOperationTime;


    public CalculativeResponse calculateMathOperation(float firstValue, float secondValue, OperationType operation) throws InterruptedException {
        float mathResult;
        switch (operation) {
            case PLUS -> {
                sleepFromTo(minMathOperationTime, maxMathOperationTime);
                mathResult = firstValue + secondValue;
            }
            case SUBTRACT -> {
                sleepFromTo(minMathOperationTime, maxMathOperationTime);
                mathResult = firstValue - secondValue;
            }
            case MULTIPLY -> {
                sleepFromTo(minMathOperationTime, maxMathOperationTime);
                mathResult = firstValue * secondValue;
            }
            case DIVIDE -> {
                sleepFromTo(minMathOperationTime, maxOtherOperationTime);
                mathResult = firstValue / secondValue;
            }
            default -> {
                sleepFromTo(minOtherOperationTime, maxMathOperationTime);
                throw new UnsupportedOperationException("Invalid Math Operation");
            }
        }
        CalculativeResponse operationResponse = new CalculativeResponse();
        operationResponse.setResponse(mathResult);
        return operationResponse;
    }


    private void sleepFromTo(int minMillisecond, int maxMillisecond) throws InterruptedException {
        final int millisecondToSleep = ThreadLocalRandom.current().nextInt(minMillisecond, maxMillisecond + 1);
        try {
            Thread.sleep(millisecondToSleep);
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }

    }
}
