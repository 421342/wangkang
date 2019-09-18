package com.example.demo.config.alipayConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Configs {
	@Value("${alipay.url.gateWay}")
	private String gateWay;
	
	@Value("${alipay.appId}")
	private String appId;
	
	@Value("${alipay.agreementNo}")
	private String agreementNo;
	
	@Value("${alipay.version}")
	private String version;
	
	@Value("${alipay.tradeType}")
	private String tradeType;
	
	@Value("${alipay.signType}")
	private String signType;
	
	@Value("${alipay.signScence}")
	private String signScence;

	/**
	 * 支付宝公钥
	 */
	@Value("${alipay.alipayPublicKey}")
	private String alipayPublicKey;

	/**
	 * 商户公钥
	 */
	@Value("${alipay.custPublicKey}")
	private String custPublicKey;

	/**
	 * 商户私钥
	 */
	@Value("${alipay.custPrivateKey}")
	private String custPrivateKey;

	@Value("${alipay.charSet}")
	private String charSet;
	
	@Value("${alipay.format}")
	private String format;
	/*
	 * @Value("${alipay.url.notifyUrl}") private String notifyUrl;
	 */

	@Value("${alipay.isCheckSign}")
	private boolean isCheckSign;
	
	@Value("${alipay.isDecrypt}")
	private boolean isDecrypt;

	//@Value("${alipay.url.payNotifyUrl}")
//	private String payNotifyUrl;

	@Value("${alipay.productCode}")
	private String productCode;

	@Value("${alipay.personalProductCode}")
	private String personalProductCode;

	@Value("${alipay.queryTxnTimePeriod}")
	private int[] queryTxnTimePeriod;

	@Value("${alipay.billFolder}")
	private String billFolder;

	@Value("${alipay.channel}")
	private String channel;



	/**
	 * 交易流水序列号前缀.支付宝
	 */
	@Value("${alipay.prefix.tradeNo}")
	private String prefixTradeNo;
	/**
	 * 交易流水序列号前缀.支付宝消费类
	 */
	@Value("${alipay.prefix.consume}")
	private String prefixConsume;
	/**
	 * 交易流水序列号前缀.支付宝退货类
	 */
	@Value("${alipay.prefix.refund}")
	private String prefixRefund;
	/**
	 * 交易流水序列号前缀.支付宝撤销类
	 */
	@Value("${alipay.prefix.undo}")
	private String prefixUndo;
	
	//绑定状态查询间隔时间
    @Value("${alipay.queryBindTimePeriod}")
    private int[] queryBindTimePeriod;

	public String getGateWay() {
		return gateWay;
	}

	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignScence() {
		return signScence;
	}

	public void setSignScence(String signScence) {
		this.signScence = signScence;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}

	public String getCustPublicKey() {
		return custPublicKey;
	}

	public void setCustPublicKey(String custPublicKey) {
		this.custPublicKey = custPublicKey;
	}

	public String getCustPrivateKey() {
		return custPrivateKey;
	}

	public void setCustPrivateKey(String custPrivateKey) {
		this.custPrivateKey = custPrivateKey;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	/*
	 * public String getNotifyUrl() { return notifyUrl; }
	 * 
	 * public void setNotifyUrl(String notifyUrl) { this.notifyUrl = notifyUrl; }
	 */

	

	

	

	public boolean isCheckSign() {
		return isCheckSign;
	}

	public void setCheckSign(boolean isCheckSign) {
		this.isCheckSign = isCheckSign;
	}

	public boolean isDecrypt() {
		return isDecrypt;
	}

	public void setDecrypt(boolean isDecrypt) {
		this.isDecrypt = isDecrypt;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPersonalProductCode() {
		return personalProductCode;
	}

	public void setPersonalProductCode(String personalProductCode) {
		this.personalProductCode = personalProductCode;
	}

	public int[] getQueryTxnTimePeriod() {
		return queryTxnTimePeriod;
	}

	public void setQueryTxnTimePeriod(int[] queryTxnTimePeriod) {
		this.queryTxnTimePeriod = queryTxnTimePeriod;
	}

	public String getBillFolder() {
		return billFolder;
	}

	public void setBillFolder(String billFolder) {
		this.billFolder = billFolder;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPrefixTradeNo() {
		return prefixTradeNo;
	}

	public void setPrefixTradeNo(String prefixTradeNo) {
		this.prefixTradeNo = prefixTradeNo;
	}

	public String getPrefixConsume() {
		return prefixConsume;
	}

	public void setPrefixConsume(String prefixConsume) {
		this.prefixConsume = prefixConsume;
	}

	public String getPrefixRefund() {
		return prefixRefund;
	}

	public void setPrefixRefund(String prefixRefund) {
		this.prefixRefund = prefixRefund;
	}

	public String getPrefixUndo() {
		return prefixUndo;
	}

	public void setPrefixUndo(String prefixUndo) {
		this.prefixUndo = prefixUndo;
	}

	public int[] getQueryBindTimePeriod() {
		return queryBindTimePeriod;
	}

	public void setQueryBindTimePeriod(int[] queryBindTimePeriod) {
		this.queryBindTimePeriod = queryBindTimePeriod;
	}

}
