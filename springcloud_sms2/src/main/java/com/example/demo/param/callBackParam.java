package com.example.demo.param;

import java.io.Serializable;

public class callBackParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public String user_receive_time;
     public String nationcode;
     public String mobile;
     public String report_status;
     public String errmsg;
     public String description;
     public String sid;
	public String getUser_receive_time() {
		return user_receive_time;
	}
	public void setUser_receive_time(String user_receive_time) {
		this.user_receive_time = user_receive_time;
	}
	public String getNationcode() {
		return nationcode;
	}
	public void setNationcode(String nationcode) {
		this.nationcode = nationcode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
     
}
