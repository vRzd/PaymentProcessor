package com.math.calculator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Value("${soap.namespace.uri}")
    private String soapNamespaceUri;

    @Value("${xsd.schemas.location}")
    private String xsdNamespaceUri;


    @Value("${wsdl11.port.name}")
    private String wsdl11PortName;

    @Value("${wsdl11.location.uri}")
    private String wsdl11LocationUri;

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "mathCalculation")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema operationSchema) {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(wsdl11PortName);
        wsdl11Definition.setLocationUri(wsdl11LocationUri);
        wsdl11Definition.setTargetNamespace(soapNamespaceUri);
        wsdl11Definition.setSchema(operationSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema operationSchema() {
        return new SimpleXsdSchema(new ClassPathResource(xsdNamespaceUri));
    }

}
