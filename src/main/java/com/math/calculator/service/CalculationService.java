package com.math.calculator.service;

import mathapp.math_calculation_web_service.CalculationResponse;
import mathapp.math_calculation_web_service.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CalculationService {

    @Value("${min.math.operation.time}")
    private int minMathOperationTime;
    @Value("${max.math.operation.time}")
    private int maxMathOperationTime;
    @Value("${min.stall.operation.time}")
    private int minStallTime;
    @Value("${max.stall.operation.time}")
    private int maxStallTime;
    @Value("${accurate.error.operation.time}")
    private int timeForError;


    public CalculationResponse calculateMathOperation(float firstValue, float secondValue, Operation operation) throws InterruptedException {
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
                sleepFromTo(minMathOperationTime, maxMathOperationTime);
                mathResult = firstValue / secondValue;
            }
            case STALL -> {
                sleepFromTo(minStallTime, maxStallTime);
                mathResult = firstValue / secondValue;
            }
            default -> {
                Thread.sleep(timeForError);
                throw new UnsupportedOperationException("Invalid Math Operation");
            }
        }
        CalculationResponse operationResponse = new CalculationResponse();
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
