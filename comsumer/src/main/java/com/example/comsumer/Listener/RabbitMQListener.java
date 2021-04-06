package com.example.comsumer.Listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQListener  {
    @RabbitListener(queues ="boot_queue3")
    //@RabbitHandler
    public void Listener(Message message,Channel Channel) throws Exception {
        //Thread.sleep(1000);
        long de = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println(new String(message.getBody()));
            System.out.println("业务处理限制2。。。");
            //int i=3/0;
            //确定签收
            Channel.basicAck(de,true);
        }catch (Exception e) {
            //e.printStackTrace();
            //3.重发
            Channel.basicNack(de,true,true);

        }
    }
}
