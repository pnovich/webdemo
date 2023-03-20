package com.example.webdemo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

//@Configuration
public class Config {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean(name = "default")
    public Queue queue(){
        return new ActiveMQQueue("standalone.queue");
    }

    @Bean(name = "queue1")
    public Queue queue1(){
        return new ActiveMQQueue("queue1.queue");
    }

    @Bean(name = "queue2")
    public Queue queue2(){
        return new ActiveMQQueue("queue2.queue");
    }


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory =new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(activeMQConnectionFactory());
    }
}
