package com.example.lurenjiaspring.Controller.rabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.lurenjiaspring.config.rabbitconfig.SimpleMqConfig.EXCHANGE_NAME2;

@RestController
public class Produced {
    @Autowired
    private AmqpTemplate template;

    @RequestMapping("/mq/send")
    public String userProduce() {
//        template.convertAndSend("", "thtnihao");
//        template.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY, "user".toString());

        // Simple模式
//        template.convertAndSend("", SimpleMqConfig.QUEUE_NAME,"simpleCase");

        // Publish/Subscribe（交换机，type=fanout）
//        template.convertAndSend(SimpleMqConfig.EXCHANGE_NAME1,"","Publish/Subscribe模式");

        // topic模式
        template.convertAndSend(EXCHANGE_NAME2,"order.add","topic" + ",order.add");
        template.convertAndSend(EXCHANGE_NAME2,"order.select","topic" + ",order.select");
        template.convertAndSend(EXCHANGE_NAME2,"order.select.abccc","topic" + ",order.select.abccc");

        return "Success";
    }
}
