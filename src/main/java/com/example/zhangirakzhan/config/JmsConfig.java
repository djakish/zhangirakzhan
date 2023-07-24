package com.example.zhangirakzhan.config;


import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfig {
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("3-10");
        return factory;
    }

    @Bean
    public BrokerService brokerService() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setUseJmx(false);
        broker.setPersistent(false);
        broker.setUseShutdownHook(false);
        broker.addConnector("tcp://localhost:61616");
        return broker;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

    /*
    @Bean
    public ConnectionFactory  kafkaConnectionFactory() {
        Properties settings = new Properties();
        settings.put(JMSClientConfig.CLIENT_ID_CONFIG, "test-client-1");
        settings.put(JMSClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        settings.put(JMSClientConfig.ZOOKEEPER_CONNECT_CONF, "localhost:2181");
        ConnectionFactory cf = new KafkaConnectionFactory(settings);
        return cf;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory((jakarta.jms.ConnectionFactory) kafkaConnectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(cachingConnectionFactory());
    }

   */
}
