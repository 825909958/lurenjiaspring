package com.example.lurenjiaspring.controller.rabbitMq;

import com.example.lurenjiaspring.aop.adviceuntil.ResultCode;
import com.example.lurenjiaspring.config.rabbitconfig.SimpleMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Produced {
    @Autowired
    private AmqpTemplate template;

    /**
     * 1.意思就是template.convertAndSend()方法参数带有交换机就是发布订阅模式
     * 2.路由规则由配置类决定
     * 3.发送随意路由键，能匹配上规则就能发送到规则绑定的队列中
     * <p>
     * // 没用
     * //        template.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY, "user".toString());
     *
     * @return
     */
    @RequestMapping("/mq/send")
    public Object userProduce() {
        // ?????简单模式就是直连模式
//        template.convertAndSend(SimpleMqConfig.DIRECT_QUEUE_NAME, "thtnihao");

        // Simple模式
        template.convertAndSend("", SimpleMqConfig.SIMPLE_QUEUE, "simpleCase");

        // 广播模式（交换机，type=fanout）
        template.convertAndSend(SimpleMqConfig.EXCHANGE_NAME1, "", "广播模式");

        // topic模式(two param is routerkey)
//        template.convertAndSend(EXCHANGE_NAME2,"","topic" + "");

//        template.convertAndSend(EXCHANGE_NAME2, "order.add", "topic" + ",order.add");
//        template.convertAndSend(EXCHANGE_NAME2, "order.select", "topic" + ",order.select");
//        template.convertAndSend(EXCHANGE_NAME2, "order.select.abccc", "topic" + ",order.select.abccc");

        return ResultCode.SUCCESS;
    }
}
