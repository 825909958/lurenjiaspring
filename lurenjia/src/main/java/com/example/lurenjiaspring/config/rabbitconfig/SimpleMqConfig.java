package com.example.lurenjiaspring.config.rabbitconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleMqConfig {
    // one simple
    public static final String QUEUE_NAME = "springboot-simple-queue";

    // two 发布订阅fanout
    public static final String QUEUE_NAME1 = "springboot-fanout-queue1";
    public static final String QUEUE_NAME2 = "springboot-fanout-queue2";
    public static final String EXCHANGE_NAME1 = "springboot-fanout-exchange";

    /**
     * one
     * @return
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    /**
     * two
     */
    //fanout队列1
    @Bean
    public Queue queue1(){
        return new Queue(QUEUE_NAME1,true,false,false);
    }

    //fanout队列2
    @Bean
    public Queue queue2(){
        return new Queue(QUEUE_NAME2,true,false,false);
    }

    //fanout交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE_NAME1,true,false,null);
    }

    //fanout交换机和队列1绑定
    @Bean
    public Binding binding1(FanoutExchange fanoutExchange, Queue queue1){
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }

    //fanout交换机和队列2绑定
    @Bean
    public Binding binding2(FanoutExchange fanoutExchange,Queue queue2){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

    /**
     * three (Topic模式)两个队列绑定不同类型的routingKey
     */
    public static final String QUEUE_NAME3 = "springboot-topic-queue1";
    public static final String QUEUE_NAME4 = "springboot-topic-queue2";

    public static final String EXCHANGE_NAME2 = "springboot-topic-exchange";
    //topic队列1
    @Bean
    public Queue queue3(){
        return new Queue(QUEUE_NAME3,true,false,false);
    }

    //topic队列2
    @Bean
    public Queue queue4(){
        return new Queue(QUEUE_NAME4,true,false,false);
    }

    //topic交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME2,true,false,null);
    }

    //topic交换机和队列3的绑定
    @Bean
    public Binding binding3(TopicExchange topicExchange,Queue queue3){
        return BindingBuilder.bind(queue3).to(topicExchange).with("order.*");
    }

    //topic交换机和队列4的绑定
    @Bean
    public Binding binding4(TopicExchange topicExchange,Queue queue4){
        return BindingBuilder.bind(queue4).to(topicExchange).with("order.#");
    }


}

