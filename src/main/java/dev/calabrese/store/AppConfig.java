package dev.calabrese.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${payment.gateway}")
    private String gateway;
    @Bean
    public PaymentService stripe(){
        return new StripePaymentService();
    }
    @Bean
    public PaymentService paypal(){
        return new PayPalPaymentService();
    }

    @Bean
    public OrderService orderService(){

        if (gateway.equals("stripe")){
            return new OrderService(stripe());

        }
            return new OrderService(paypal());

    }

}
