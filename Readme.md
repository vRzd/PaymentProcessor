curl --location --request POST 'http://localhost:8087/ws/mathCalculation.wsdl' --header 'SOAPAction: "" --data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:math="http://mathApp/math-calculation-web-service"> <soapenv:Header/> <soapenv:Body> <math:calculativeRequest> <math:firstValue>1</math:firstValue> <math:secondValue>1</math:secondValue> <math:operationType>PLUS</math:operationType> </math:calculativeRequest> </soapenv:Body> </soapenv:Envelope>'

curl --location --request POST 'http://localhost:8087/ws/mathCalculation.wsdl' --header 'Content-Type: text/xml' --data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pay="http://payment/payment-processor-web-service"> <soapenv:Header/> <soapenv:Body> <pay:paymentRequest> <pay:cardNumber>1</pay:cardNumber> <pay:expirationDate>2</pay:expirationDate> <pay:cardHolderName>c</pay:cardHolderName> <pay:operationType>PAYMENT</pay:operationType> <pay:processingTime>0</pay:processingTime> </pay:paymentRequest> </soapenv:Body></soapenv:Envelope>'


curl --location --request POST 'http://localhost:3000/ws/mathCalculation.wsdl' --header 'SOAPAction: "" --data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:math="http://mathApp/math-calculation-web-service"> <soapenv:Header/> <soapenv:Body> <math:calculativeRequest> <math:firstValue>1</math:firstValue> <math:secondValue>1</math:secondValue> <math:operationType>PLUS</math:operationType> </math:calculativeRequest> </soapenv:Body> </soapenv:Envelope>'




--data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pay="http://payment/payment-processor-web-service">
<soapenv:Header/>
<soapenv:Body>
<pay:paymentRequest>
<!--Optional:-->
<pay:cardNumber>1</pay:cardNumber>
<!--Optional:-->
<pay:expirationDate>2</pay:expirationDate>
<!--Optional:-->
<pay:cardHolderName>c</pay:cardHolderName>
<!--Optional:-->
<pay:operationType>PAYMENT</pay:operationType>
<!--Optional:-->
<pay:processingTime>0</pay:processingTime>
</pay:paymentRequest>
</soapenv:Body>
</soapenv:Envelope>'