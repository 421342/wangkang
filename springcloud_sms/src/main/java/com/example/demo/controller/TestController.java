package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.component.sendmail;
import com.example.demo.config.httpclient.HTTPException;
import com.example.demo.dao.TestMapper;
import com.example.demo.model.Test;
import com.example.demo.param.AuthCodePayParam;
import com.example.demo.param.callBackParam;
import com.example.demo.result.resultMsg;
import com.example.demo.service.SmsSendMegServiceImpl;
import com.example.demo.service.sendMailServiceImpl;
import com.example.demo.service.alipay.alipayServiceImpl;


@RestController
@RequestMapping("/testController")
public class TestController {
	@Autowired
    TestMapper testMapper;
	@Autowired
	SmsSendMegServiceImpl smsSend;
	@Autowired
	alipayServiceImpl alipayService;
	@Autowired
	sendMailServiceImpl sendMailService;
	 @Value("${meg.appid}")
	    private int appid;
	    @Value("${meg.appkey}")
	    private String appkey;
	    @Value("${meg.url}") 
		 protected String url;
		  
		  @Value("${meg.msgNum}") 
		  protected double msgNum;  
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@PostMapping("/getId")
	public BigDecimal getId(@RequestBody Test test) {
		
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String format2 = format.format(new Date());
		test.setTabName("test_"+format2);
		testMapper.insert(test);
		return test.getId();
	}
	public static void main(String[] args) throws IOException {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String format2 = format.format(new Date());
		System.out.println("test_"+format2);
		String mobile_code = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10, 6-1)));
		double co=((Math.random() * 9 + 1) * Math.pow(10, 6-1));
		System.out.println(mobile_code);
		int d=(int)(Math.random() * 9 + 1);
		System.out.println(d);
	File f=new File("E:\\kk\\ll\\oo.txt");
	double random = Math.random();
	System.out.println("random:"+random);
		/*
		 * if (!f.exists()) { f.createNewFile(); }
		 */
}   
	@PostMapping("/send")
	public resultMsg send(@RequestBody sendParam param) throws JSONException, HTTPException, IOException {
		log.debug("-------短信发送start------");
		log.debug("-------初始化SmsSingleSender----------");
		//sms=new SmsSingleSenderService(appid,appkey);
		log.debug("--------获取随机验证码和有效时间----------------");
		ArrayList<String> list = smsSend.getMegMc(msgNum);
		resultMsg result = smsSend.localSend(param.getNationCode(), param.getPhoneNumber(), 406372, list, "王之涣公众号", "", "");
		
		return result;
	}
	/**
	 * 短信平台回调
	 */
	@RequestMapping("/callBack")
	public resultMsg callBack(@RequestBody List<callBackParam> list) {
		log.debug("-----短信平台回调-----------");
		resultMsg result=new resultMsg();
		try {
			result=	smsSend.callBackSer(list);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@PostMapping("pay")
	public resultMsg pay(@RequestBody AuthCodePayParam param) {
		resultMsg result=new resultMsg();
		alipayService.pay(param);
		return result;
	}
	@PostMapping("/sendMail")
	public resultMsg sendMail(@RequestBody sendmail mail) {
		resultMsg result=new resultMsg();
		result = sendMailService.sendMail(mail);
		return result;
	}
	
	}
