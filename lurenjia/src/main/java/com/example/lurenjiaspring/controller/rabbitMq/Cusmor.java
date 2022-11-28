package com.example.lurenjiaspring.Controller.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.lurenjiaspring.config.rabbitconfig.SimpleMqConfig.QUEUE_NAME3;
import static com.example.lurenjiaspring.config.rabbitconfig.SimpleMqConfig.QUEUE_NAME4;

@Component
public class Cusmor {
    @RabbitListener(queues="spring.tht")
    public void process0(String userMessage) throws InterruptedException {
//        System.out.println("TopicRecive1接受的消息： "+userMessage);
    }

    /**
     *
     * simple模式
     * @param simpleMessage
     * @throws InterruptedException
     */
    @RabbitListener(queues="springboot-simple-queue")
    public void process1(String simpleMessage) throws InterruptedException {
        System.out.println("simpleRecive1接受的消息： "+simpleMessage);
    }

    /**
     * 发布订阅模式类型  队列和交换机绑定
     * @param message
     * @throws InterruptedException
     */
    @RabbitListener(queues="springboot-fanout-queue1")
    public void process2(String message) throws InterruptedException {
        System.out.println("TopicRecive1接受的消息： "+message);
    }

    @RabbitListener(queues="springboot-fanout-queue2")
    public void process3(String message) throws InterruptedException {
        System.out.println("TopicRecive1接受的消息： "+message);
    }

//    @RabbitListener(queues="springboot-fanout-queue2")
//    public void process4(String message) throws InterruptedException {
//        System.out.println("TopicRecive2接受的消息： "+message);
//    }

    /**
     * topic模式
     */
    @RabbitListener(queues = QUEUE_NAME3)
    public void receiving1(String msg){
        System.out.println("消费者1从队列中取出消息：\t" + msg);
    }

    @RabbitListener(queues = QUEUE_NAME4)
    public void receiving2(String msg) {
        System.out.println("消费者2从队列中取出消息：\t" + msg);
    }

}
