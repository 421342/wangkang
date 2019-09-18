package com.example.demo.param;

import java.io.Serializable;

/**
 * 条码支付、声波支付 请求参数
 * @author zhangqijian
 * @date 2018/10/28
 */
public class AuthCodePayParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 支付场景 条码支付 01条码支付FF预留
	 */
	private String scene;

	/**
	 * 支付授权码
	 */
	private String authCode;

	/**
	 * 订单标题
	 */
	private String subject;

	/**
	 * 商户订单号
	 */
	private String orderId;

	/**
	 * 交易金额
	 */
	private Long totalAmount;

	/**
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易(分钟为单位)
	 */
	private Integer timeoutExpress;
	/**
	 * 虚拟站点编号8位
	 */
	private String stationCode;
	/**
	 * 虚拟设备编号8位
	 */
	private String deviceCode;

	public AuthCodePayParam(String scene, String authCode, String subject, String orderId, Long totalAmount,
                            Integer timeoutExpress) {
		super();
		this.scene = scene;
		this.authCode = authCode;
		this.subject = subject;
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.timeoutExpress = timeoutExpress;
	}

	public AuthCodePayParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTimeoutExpress() {
		return timeoutExpress;
	}

	public void setTimeoutExpress(Integer timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}
	

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Override
	public String toString() {
		return "AuthCodePayParam [scene=" + scene + ", authCode=" + authCode + ", subject=" + subject + ", orderId="
				+ orderId + ", totalAmount=" + totalAmount + ", timeoutExpress=" + timeoutExpress + ", stationCode="
				+ stationCode + ", deviceCode=" + deviceCode + "]";
	}
	
}
