package com.payment.processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.payment_processor_web_service.OperationType;
import payment.payment_processor_web_service.PaymentResult;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PaymentProcessingService {

    @Value("${min.math.operation.time}")
    private int minMathOperationTime;
    @Value("${max.math.operation.time}")
    private int maxMathOperationTime;


    public PaymentResult paymentProcessing(OperationType operationType, String cardNumber, String cardHolderName, String expirationDate, Float processingTime) throws InterruptedException {

        validateCardParam(cardNumber, cardHolderName, expirationDate);

        switch (operationType) {
            case PAYMENT -> {
                processing(processingTime);
                return PaymentResult.SUCCESSFUL;
            }
            case RETURN_PAYMENT -> {
                sleepFromTo(minMathOperationTime, maxMathOperationTime);
                return PaymentResult.FAILED;
            }
            default -> {
                return PaymentResult.FAILED;
            }
        }
    }

    private void processing(Float processingTime) throws InterruptedException {
        if (Objects.nonNull(processingTime)) {
            Thread.sleep(Math.round(processingTime.floatValue()));
        } else sleepFromTo(minMathOperationTime, maxMathOperationTime);

    }

    private Boolean validateCardParam(String cardNumber, String cardHolderName, String expirationDate) {
        return true;
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
