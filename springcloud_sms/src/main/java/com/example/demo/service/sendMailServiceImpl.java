package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.component.sendmail;
import com.example.demo.result.resultMsg;

@Service
public class sendMailServiceImpl {
	@Autowired
	JavaMailSender javaMailSender;
	@Value("${revmail.addresser}")
	private String receiver;

private static final Logger log = LoggerFactory.getLogger(sendMailServiceImpl.class);
	
public resultMsg sendMail(sendmail mail) {
	log.debug("-----------邮件服务开始------------");
	resultMsg result=new resultMsg();
	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	try {
		mimeMessage.setFrom("17521271709@163.com");
		
		mimeMessage.setReplyTo(new Address[]{new InternetAddress(receiver)});
		mimeMessage.addRecipients(MimeMessage.RecipientType.TO, new Address[]{new InternetAddress(receiver)});
		mimeMessage.setSubject("腾讯短信平台");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		
		StringBuilder sb=new StringBuilder();
        sb.append("<p style='color:#F00'>验证码已经发送,发送短息时间</p>")
        .append(format.format(mail.getDate()))
        .append("<p style='color:#F00'>验证码已经发送,短息内容:  </p>")
        .append(mail.getMsg());
		mimeMessage.setText(sb.toString(),"utf-8","html");
		javaMailSender.send(mimeMessage);
		log.debug("-----------邮件服务完成------------");
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return result;
}
public static void main(String[] args) {
}
}
