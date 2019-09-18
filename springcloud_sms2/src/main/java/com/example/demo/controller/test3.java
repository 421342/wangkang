package com.example.demo.controller;

import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import com.github.qcloudsms.SmsSenderUtil;

public class test3 {
public static void main(String[] args) {
	boolean a=false;
	boolean b=true;
	 System.out.println(a&=b);
	 System.out.println(new Random(System.currentTimeMillis()/1000).nextInt(900000) + 100000);
	 Math.random();
	 new Random();
	 String appid="123";
	 String appkey="456";
	 StringBuffer buffer = new StringBuffer("appkey=")
	            .append(appid+appkey)
	            .append("&random=")
	            .append(appkey)
	            .append("&time=")
	            .append(new Date())
	            .append("&mobile=")
	            .append(789);
	 System.out.println(buffer);
	 System.out.println(DigestUtils.md5Hex(DigestUtils.md5Hex("123")));
}
}
