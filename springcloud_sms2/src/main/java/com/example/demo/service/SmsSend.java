package com.example.demo.service;

import java.util.Random;

public class SmsSend {
public static void main(String[] args) {
	Random random = new Random(System.currentTimeMillis() / 10000000);
	System.out.println(System.currentTimeMillis() / 10000000);
	System.out.println(random.nextInt());
	long mill=System.currentTimeMillis()/10000000;
	System.out.println(mill);
	long nextInt = new Random(mill).nextInt(1000000)+100000;
	System.out.println(nextInt);
	System.out.println(Math.pow(10, 5));
}
}
