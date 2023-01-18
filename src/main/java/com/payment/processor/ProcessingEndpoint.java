package com.payment.processor;


import com.payment.processor.service.PaymentProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import payment.payment_processor_web_service.OperationType;
import payment.payment_processor_web_service.PaymentRequest;
import payment.payment_processor_web_service.PaymentResponse;
import payment.payment_processor_web_service.PaymentResult;

@Endpoint
public class ProcessingEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingEndpoint.class);

    private static final String NAMESPACE_URI = "http://payment/payment-processor-web-service";

    @Autowired
    private PaymentProcessingService calculationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "paymentRequest")
    @ResponsePayload
    public PaymentResponse doCalculation(@RequestPayload PaymentRequest paymentRequest) throws InterruptedException {

        final OperationType operationType = paymentRequest.getOperationType().getValue();

        LOGGER.info("Preparation for processing operation {}", operationType);

        final String cardNumber = paymentRequest.getCardNumber();
        final String expirationDate = paymentRequest.getExpirationDate();
        final String cardHolderName = paymentRequest.getCardHolderName().getValue();

        final Float processingTime = paymentRequest.getProcessingTime().getValue();

        final PaymentResult paymentResult = calculationService.paymentProcessing(operationType, cardNumber, cardHolderName, expirationDate, processingTime);
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentResult(paymentResult);
        return paymentResponse;

    }
}