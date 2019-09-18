package com.example.demo.rabbitmq;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.component.sendmail;
import com.example.demo.config.BeanToBean;
import com.example.demo.config.MQConfig;
import com.example.demo.service.sendMailServiceImpl;
import com.rabbitmq.client.Channel;

@Component
public class MqReceiver {
@Autowired
sendMailServiceImpl mailService;

private static final Logger log = LoggerFactory.getLogger(MqReceiver.class);

@RabbitListener(queues=MQConfig.QUEUE)
@RabbitHandler
public void MqConsumer(String msg,Channel channel,Message meg) throws IOException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	sendmail bean = BeanToBean.stringToBean(msg, sendmail.class);
	log.debug("消费时间:{}",sdf.format(new Date()));
	mailService.sendMail(bean);
	
}
}
