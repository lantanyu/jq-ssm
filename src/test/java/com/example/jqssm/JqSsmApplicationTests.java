package com.example.jqssm;

import com.example.jqssm.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JqSsmApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testsendmq() {

        //确认是否成功
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override            //1.配置信息 2.是否成功接收 3.失败原因
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("已发送");
                if(b) {
                    System.out.println("发送成功到交换机"+s);
                }else {
                    System.out.println("发失败到交换机"+s);
                }
            }
        });
        //设置交换机没到队列返回
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override                   //1.消息对象 2.错误码 3 错误信息 4 交互机 5。路右键
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("没送到队列");
                System.out.println(message);
                System.out.println(i);
                System.out.println(s);
                System.out.println(s1);
                System.out.println(s2);
            }
        });

        for (int i =0;i<10;i++) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"boot.haha","boot mq hello~~~");
        }
    }
    //ttl||死信||延迟
    @Test
    public void testsendTTLmq() {
        //确认是否成功到交换机
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override            //1.配置信息 2.是否成功接收 3.失败原因
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("已发送");
                if(b) {
                    System.out.println("发送成功到交换机"+s);
                }else {
                    System.out.println("发失败到交换机"+s);
                }
            }
        });
        //设置交换机没到队列返回
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override                   //1.消息对象 2.错误码 3 错误信息 4 交互机 5。路右键
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("没送到队列");
                System.out.println(message);
                System.out.println(i);
                System.out.println(s);
                System.out.println(s1);
                System.out.println(s2);

            }
        });
        //设置消息的一些信息
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置消息时间
                message.getMessageProperties().setExpiration("5000");
                return message;
            }
        };
        //消息拒绝，过期，长度，都会传到死信交换机
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"TTL.haha","boot TTLmq 5000~~~",messagePostProcessor);

    }


}
