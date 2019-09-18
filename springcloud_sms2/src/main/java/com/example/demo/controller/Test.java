package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {
public static void main(String[] args) throws IOException {
		//BufferedReader  br =  new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\86175\\Desktop\\allin\\学习笔记.txt"),"GB2312"));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\kk\\ll\\oo.txt"),"GB2312"));
		 FileInputStream fis = new FileInputStream("C:\\Users\\86175\\Desktop\\allin\\学习笔记.txt");
	       FileOutputStream fos = new FileOutputStream("E:\\kk\\ll\\oo.txt");
		//String b="";
	       int len=0;
	       byte[] by=new byte[fis.available()];
	       while((len=fis.read(by))!=-1) {
	    	   fos.write(by, 0, len);
	    	   fos.flush();
	       }
		//StringBuffer sb = new StringBuffer();
		/*try {
			while((b = br.readLine())!=null){
				//得到文件内容放到sb中
				//sb.append(b);
				//这里可以写自己想对每一行的处理代码
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\kk\\ll\\oo.txt"),"GB2312"));
				bw.write(b);
			}
			// s = sb.toString();
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\kk\\ll\\oo.txt"),"GB2312"));
		//	bw.write(s);
			//bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
*/

//}
}
}
