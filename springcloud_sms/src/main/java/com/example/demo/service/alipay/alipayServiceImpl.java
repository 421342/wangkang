package com.example.demo.service.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.example.demo.config.alipayConfig.Configs;
import com.example.demo.param.AuthCodePayParam;
import com.example.demo.result.resultMsg;

@Service
public class alipayServiceImpl {
@Autowired
private Configs configs;
public 	resultMsg pay(AuthCodePayParam param) {
	resultMsg result=new resultMsg();
	AlipayClient alipayClient = new DefaultAlipayClient(configs.getGateWay(),configs.getAppId(),
			configs.getCustPrivateKey(),configs.getFormat(),configs.getCharSet(),configs.getCustPublicKey(),configs.getSignType());
	AlipayTradePayRequest request=new AlipayTradePayRequest();
	AlipayTradePayModel model = new AlipayTradePayModel();
	model.setOutTradeNo(param.getOrderId());
	model.setScene("bar_code");
	model.setAuthCode(param.getAuthCode());
	model.setSubject(param.getSubject());
	model.setTotalAmount("0.1");
	request.setBizModel(model);
	try {
		AlipayTradePayResponse response = alipayClient.execute(request);
	} catch (AlipayApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
}
