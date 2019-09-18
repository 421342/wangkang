package com.example.demo.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.component.sendmail;
import com.example.demo.dao.MessageInfoMapper;
import com.example.demo.model.MessageInfo;
import com.example.demo.param.callBackParam;
import com.example.demo.rabbitmq.MqSend;
import com.example.demo.result.SmsSingleSenderResult;
import com.example.demo.result.resultMsg;
import com.github.qcloudsms.httpclient.HTTPException;

@Service
public class SmsSendMegServiceImpl {
	@Autowired
	MessageInfoMapper messageInfoMapper;
	@Autowired
    MqSend mqSend;
	private static final Logger log = LoggerFactory.getLogger(SmsSendMegServiceImpl.class);

	@Value("${meg.appid}")
	private int appid;

	@Value("${meg.appkey}")
	private String appkey;

	@Value("${meg.url}")
	private String url;
	
	@Value("${revmail.addresser}")
	private String receiver;
	/**
	 * 获取验证码
	 * 
	 * @param msgNum
	 * @return
	 */
	public ArrayList<String> getMegMc(double msgNum) {
		ArrayList<String> list = new ArrayList<String>();
		String mobile_code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, msgNum - 1)));
		list.add(mobile_code);
		list.add("5");
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public resultMsg localSend(String nationCode, String phoneNumber, int templateId, ArrayList<String> params,
			String sign, String extend, String ext) throws JSONException, com.example.demo.config.httpclient.HTTPException, IOException {
		resultMsg result = new resultMsg();
		log.debug("---------发送之前入库-----------");

		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setNationCode(nationCode);
		messageInfo.setPhoneNum(phoneNumber);
		// {1}为您的登录验证码，请于{2}分钟内填写。如非本人操作，请忽略本短信。
		messageInfo.setMessage(params.get(0) + "为您的登录验证码，请于" + params.get(1) + "分钟内填写。如非本人操作，请忽略本短信");
		messageInfo.setCreateDate(new Date());
		// 1 预发送 2失败 0 短信平台发送成功 3用户成功收到 4 用户未收到 
		messageInfo.setStatus((short) 1);

		int insert = 0;
		try {
			insert = messageInfoMapper.insert(messageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        result.setCode(1);
        result.setMessage("本地入库失败");
		if (insert > 0) {
			log.debug("入库成功{}", insert);
			SmsSingleSender sms=new SmsSingleSender(appid,appkey);
			SmsSingleSenderResult senderResult;
				senderResult = sms.sendWithParam(nationCode, phoneNumber, templateId,
						params, sign, extend, messageInfo.getMessage(),appid,appkey,url);
				if (senderResult.result == 0) {
					log.debug("------------短信平台返回success-------------");
					Map<String, Comparable> map = new HashMap<String, Comparable>();
					map.put("status", 0);
					map.put("MsgId", messageInfo.getMsgId());
					map.put("sid", senderResult.sid);
					log.debug("-----------更新数据库---------------");
					if (messageInfoMapper.updateByExampleSelective(map) > 0) {
						//放入队列
						log.debug("发完短信，放入队列");
						sendmail sm=new sendmail();
						sm.setDate(new Date());
						sm.setMsg(senderResult.ext);
						sm.setUser(receiver);
						mqSend.sendMsgMail(sm);
						//result
						result.setCode(0);
						result.setMessage("发送成功");
					} else {
						log.debug("-----------更新数据库失败---------------");
						result.setCode(1);
						result.setMessage("更新数据库失败");
					}
				} else {
					result.setCode(senderResult.result);
					result.setMessage(senderResult.errMsg);
				}
			
		}
	
		return result;
	}
	/*
	 * public SmsSingleSenderResult sendMegPlat(String nationCode, String
	 * phoneNumber, int templateId, ArrayList<String> params, String sign, String
	 * extend, String ext,SmsSingleSender sms) { SmsSingleSenderResult result=new
	 * SmsSingleSenderResult(); try { log.debug("----------通知短信平台-------------");
	 * result = sms.sendWithParam (nationCode, phoneNumber, templateId, params,
	 * sign, extend, ext,url);
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); } catch (HTTPException e) {
	 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } return
	 * result; }
	 */
	/**
	 * 短信平台回调
	 * @throws ParseException 
	 */
	public resultMsg callBackSer(List<callBackParam> list) throws ParseException {
		resultMsg result=new resultMsg();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List <MessageInfo> listde=new ArrayList<MessageInfo>();
		for(callBackParam param :list) {
			if("SUCCESS".equals(param.getReport_status())) {
				MessageInfo info=new MessageInfo();
				info.setSid(param.getSid());
				info.setDes(param.getDescription());
				info.setUserRcvTms(format.parse(param.getUser_receive_time()));
				info.setStatus((short)3);
				listde.add(info);
			}else if("FAIL".equals(param.getReport_status())) {
				MessageInfo info=new MessageInfo();
				info.setSid(param.getSid());
				info.setDes(param.getDescription());
				info.setStatus((short)4);
				listde.add(info);
			}
			
		}
		int i = messageInfoMapper.updateStatusPatch(listde);
		if(i>0) {
			result.setCode(0);
			result.setMessage("更新成功");
		}
		return result;
	}
}
