package com.payment.processor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.payment_processor_web_service.OperationType;
import payment.payment_processor_web_service.PaymentResult;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PaymentProcessingService {

    @Value("${min.operation.time}")
    private int minOperationTime;
    @Value("${max.operation.time}")
    private int maxOperationTime;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProcessingService.class);


    public PaymentResult paymentProcessing(OperationType operationType, String cardNumber, String cardHolderName, String expirationDate, Float processingTime) {

        validateCardParam(cardNumber, cardHolderName, expirationDate);
        PaymentResult paymentResult;
        LOGGER.info("Payment processing {}", operationType);

        switch (operationType) {
            case PAYMENT -> {
                processing(processingTime);
                paymentResult = PaymentResult.SUCCESSFUL;
            }
            case RETURN_PAYMENT -> {
                processing(processingTime);
                paymentResult = PaymentResult.FAILED;
            }
            default -> {
                paymentResult = PaymentResult.FAILED;
            }
        }
        return paymentResult;
    }

    private void processing(Float processingTime) {
        float processingTimeInSecond;
        if (Objects.nonNull(processingTime)) {
            processingTimeInSecond = processingTime.floatValue() * 100;
        } else {
            processingTimeInSecond = calculateProcessingTimeInRange(minOperationTime, maxOperationTime);
        }
        LOGGER.info("Payment processing, in time {} second", processingTimeInSecond);
        try {
            Thread.sleep(Math.round(processingTimeInSecond));
        } catch (InterruptedException e) {
            LOGGER.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        }

    }

    private Boolean validateCardParam(String cardNumber, String cardHolderName, String expirationDate) {

        return true;
    }


    private float calculateProcessingTimeInRange(int minMillisecond, int maxMillisecond) {
        final int millisecondToSleep = ThreadLocalRandom.current().nextInt(minMillisecond, maxMillisecond + 1);
        return (float) millisecondToSleep * 100;

    }

}
