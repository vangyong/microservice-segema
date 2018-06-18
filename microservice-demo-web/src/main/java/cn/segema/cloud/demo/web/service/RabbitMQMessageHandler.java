package cn.segema.cloud.demo.web.service;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "myDirectQueue", durable = "true", autoDelete = "false", exclusive = "false"), 
//							exchange = @Exchange("myDirectExhange"))})
public class RabbitMQMessageHandler {
//
//	 @RabbitHandler
//	    public void add(byte[] body) {
//	        System.out.println("----------byte[]方法进行处理----------");
//	        try {
//	            System.out.println(new String(body, "UTF-8"));
//	        } catch (UnsupportedEncodingException e) {
//	            e.printStackTrace();
//	        }
//	    }
}
