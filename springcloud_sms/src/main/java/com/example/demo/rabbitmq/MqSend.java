package com.example.demo.rabbitmq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.component.sendmail;
import com.example.demo.config.BeanToBean;
import com.example.demo.config.MQConfig;

@Component
public class MqSend {
@Autowired
AmqpTemplate amqpTemplate;

private static final Logger log = LoggerFactory.getLogger(MqSend.class);

public void sendMsgMail(sendmail mail) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String str = BeanToBean.beanToString(mail);	
amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,MQConfig.TOPIC_KEY2, str);
log.debug("消息入队时间：{}",sdf.format(new Date()));
}
}
