package com.example.jqssm.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "boot_topic_exchange3";
    public static final String DLXEXCHANGE_NAME = "dlx_topic_exchange3";
    public static final String QUEUE_NAME = "boot_queue3";
    public static final String TTLQUEUE_NAME = "TTL_queue3";
    public static final String DLXQUEUE_NAME = "DLX_queue3";

    //交换机
    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }
    //死信交换机
    @Bean("dlxExchange")
    public Exchange dlxExchange(){
        return ExchangeBuilder.topicExchange(DLXEXCHANGE_NAME).durable(true).build();
    }


    //2.Queue 队列
    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
    //TTL队列,绑定死信，延迟队列
    @Bean("TTLQueue")
    public Queue TTLQueue(){
        return QueueBuilder
                .durable(TTLQUEUE_NAME)
                .withArgument("x-message-ttl",10000)
                //声明该队列的死信消息发送到的 交换机 （队列添加了这个参数之后会自动与该交换机绑定，并设置路由键，不需要开发者手动设置)
                .withArgument("x-dead-letter-exchange",DLXEXCHANGE_NAME)
                //声明该队列死信消息在交换机的 路由键
                .withArgument("x-dead-letter-routing-key", "dlx.nihao")
                .build();
    }
    @Bean("DLXQueue")
    public Queue DLXQueue(){
        return QueueBuilder.durable(DLXQUEUE_NAME).build();
    }


    //3. 队列和交互机绑定关系 Binding
    /*
        1. 知道哪个队列
        2. 知道哪个交换机
        3. routing key
     */
    @Bean
    public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
    @Bean
    public Binding TTLbindQueueExchange(@Qualifier("TTLQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("TTL.#").noargs();
    }
    @Bean
    public Binding DLXbindQueueExchange(@Qualifier("DLXQueue") Queue queue, @Qualifier("dlxExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dlx.#").noargs();
    }

}
