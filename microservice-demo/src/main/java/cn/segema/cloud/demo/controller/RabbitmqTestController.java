package cn.segema.cloud.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.segema.cloud.demo.vo.DemoStudentVO;

@Controller
@RequestMapping(value = "/rabbitmq/test")
public class RabbitmqTestController {
	
	@Autowired
	RabbitMessagingTemplate rabbitSendTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public void send() {
    		DemoStudentVO student = new DemoStudentVO();
    		student.setName("zhangsan");
    		student.setAddress("wuhan");
    		student.setAge(20);
        //rabbitSendTemplate.convertAndSend("default.topic", "test2.send", student);
		rabbitSendTemplate.convertAndSend("myFanoutExhange", "",student);
        
        
    }
}
