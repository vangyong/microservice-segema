package cn.segema.cloud.demo.web.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMQConsumerConfig {
	
//	@Bean
//    public ConnectionFactory connectionFactory() {
//		
//        com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
//        connectionFactory.setHost("127.0.0.1");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setUsername("springcloud");
//        connectionFactory.setPassword("springcloud");
//
//        connectionFactory.setAutomaticRecoveryEnabled(true);
//        connectionFactory.setNetworkRecoveryInterval(10000);
//
//        Map<String, Object> connectionFactoryPropertiesMap = new HashMap();
//        connectionFactoryPropertiesMap.put("principal", "RobertoHuang");
//        connectionFactoryPropertiesMap.put("description", "RGP订单系统V2.0");
//        connectionFactoryPropertiesMap.put("emailAddress", "RobertoHuang@foxmail.com");
//        connectionFactory.setClientProperties(connectionFactoryPropertiesMap);
//
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
//        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
//
//        // 设置消费者线程数
//        simpleRabbitListenerContainerFactory.setConcurrentConsumers(5);
//        // 设置最大消费者线程数
//        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(10);
//
//        // 设置消费者标签
//        simpleRabbitListenerContainerFactory.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String s) {
//                return "RGP订单系统ADD处理逻辑消费者";
//            }
//        });
//
//        return simpleRabbitListenerContainerFactory;
//    }
//	
	

}
