package com.example.lurenjiaspring.controller.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.lurenjiaspring.config.rabbitconfig.SimpleMqConfig.*;

@Component
public class Cusmor {
    //@RabbitListener(queues=QUEUE_THT)
    //public void process0(String userMessage) throws InterruptedException {
    //    System.out.println("QUEUE_THT： "+userMessage);
    //}

    /**
     *
     * simple模式
     * @param simpleMessage
     * @throws InterruptedException
     */
    @RabbitListener(queues=SIMPLE_QUEUE)
    public void process1(String simpleMessage) throws InterruptedException {
        System.out.println("processSimple： "+simpleMessage);
    }

    /**
     *
     * simple模式
     * @param simpleMessage
     * @throws InterruptedException
     */
    @RabbitListener(queues=SIMPLE_QUEUE)
    public void processSimple1(String simpleMessage) throws InterruptedException {
        System.out.println("processSimple1： "+simpleMessage);
    }

    /**
     * 发布订阅模式类型  队列和交换机绑定
     * @param message
     * @throws InterruptedException
     */
    @RabbitListener(queues=QUEUE_NAME1)
    public void process2(String message) throws InterruptedException {
        System.out.println("processFanout： "+message);
    }

    /**
     * 发布订阅模式类型  队列和交换机绑定
     * @param message
     * @throws InterruptedException
     */
    @RabbitListener(queues=QUEUE_NAME1)
    public void processFanout2(String message) throws InterruptedException {
        System.out.println("processFanout2： "+message);
    }

    //@RabbitListener(queues=QUEUE_NAME2)
    //public void process3(String message) throws InterruptedException {
    //    System.out.println("QUEUE_NAME2： "+message);
    //}

//    @RabbitListener(queues="springboot-fanout-queue2")
//    public void process4(String message) throws InterruptedException {
//        System.out.println("TopicRecive2接受的消息： "+message);
//    }

    /**
     * topic模式
     */
    @RabbitListener(queues = QUEUE_NAME3)
    public void receiving1(String msg){
        System.out.println("QUEUE_NAME3：\t" + msg);
    }

    @RabbitListener(queues = QUEUE_NAME4)
    public void receiving2(String msg) {
        System.out.println("QUEUE_NAME4：\t" + msg);
    }

}
