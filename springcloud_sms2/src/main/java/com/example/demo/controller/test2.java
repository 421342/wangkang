package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class test2 {
public static void main(String[] args) throws IOException, FileNotFoundException {
	BufferedReader  br =  new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\86175\\Desktop\\allin\\学习笔记.txt"),"GB2312"));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\kk\\ll\\oo.txt"),"GB2312"));
	String b="";
	while((b=br.readLine())!=null) {
		
		bw.write(b,0,b.length());
		bw.newLine();
	}
	bw.flush();
}
}
