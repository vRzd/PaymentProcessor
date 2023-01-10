package com.app.calculation;


import com.app.calculation.service.CalculationService;
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
public class OperationEndpoint {

    private static final String NAMESPACE_URI = "http://payment/payment-processor-web-service";

    @Autowired
    private CalculationService calculationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "paymentRequest")
    @ResponsePayload
    public PaymentResponse doCalculation(@RequestPayload PaymentRequest paymentRequest) throws InterruptedException {

        final OperationType operationType = paymentRequest.getOperationType().getValue();

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