package com.example.demo.service;

import com.github.qcloudsms.httpclient.HTTPClient;
import com.github.qcloudsms.httpclient.HTTPException;
import com.github.qcloudsms.httpclient.HTTPMethod;
import com.github.qcloudsms.httpclient.HTTPRequest;
import com.github.qcloudsms.httpclient.HTTPResponse;
import com.github.qcloudsms.SmsSenderUtil;
import com.example.demo.component.SmsBase;
import com.example.demo.controller.SmsSingleSenderResult;
import com.example.demo.dao.MessageInfoMapper;
import com.example.demo.model.MessageInfo;
import com.example.demo.result.resultMsg;
import com.github.qcloudsms.httpclient.DefaultHTTPClient;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

public class SmsSingleSender extends SmsBase {

private static final Logger log = LoggerFactory.getLogger(SmsSingleSender.class);
   
    @Autowired
    SmsSingleSender smsSingleSender;
    @Autowired
    MessageInfoMapper messageInfoMapper;
    

	 
    public SmsSingleSender(int appid, String appkey) {
        super(appid, appkey, new DefaultHTTPClient());
    }

    public SmsSingleSender(int appid, String appkey, HTTPClient httpclient) {
        super(appid, appkey, httpclient);
    }
   public SmsSingleSender() {
	   
   }
    /**
     * 普通单发
     *
     * 普通单发短信接口，明确指定内容，如果有多个签名，请在内容中以【】的方式添加到信息内容中，否则系统将使用默认签名
     *
     * @param type 短信类型，0 为普通短信，1 营销短信
     * @param nationCode 国家码，如 86 为中国
     * @param phoneNumber 不带国家码的手机号
     * @param msg 信息内容，必须与申请的模板格式一致，否则将返回错误
     * @param extend 扩展码，可填空
     * @param ext 服务端原样返回的参数，可填空
     * @return {@link}SmsSingleSenderResult
     * @throws HTTPException  http status exception
     * @throws JSONException  json parse exception
     * @throws IOException    network problem
     */
    public com.example.demo.controller.SmsSingleSenderResult send(int type, String nationCode, String phoneNumber,
        String msg, String extend, String ext,String url)
            throws HTTPException, JSONException, IOException {
log.debug("短信发送开始service");
        long random = SmsSenderUtil.getRandom();
        long now = SmsSenderUtil.getCurrentTime();
        JSONObject body = new JSONObject()
            .put("tel", (new JSONObject()).put("nationcode", nationCode).put("mobile", phoneNumber))
            .put("type", type)
            .put("msg", msg)
            .put("sig", SmsSenderUtil.calculateSignature(appkey, random, now, phoneNumber))
            .put("time", now)
            .put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
            .put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");
log.debug("{}",SmsSenderUtil.calculateSignature(appkey, random, now, phoneNumber));
        HTTPRequest req = new HTTPRequest(HTTPMethod.POST, url)
            .addHeader("Conetent-Type", "application/json")
            .addQueryParameter("sdkappid", appid)
            .addQueryParameter("random", random)
            .setConnectionTimeout(60 * 1000)
            .setRequestTimeout(60 * 1000)
            .setBody(body.toString());

        // TODO Handle timeout exception
        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            // May throw JSONException
            return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
        } catch(URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
    }

    /**
     * 指定模板单发
     *
     * @param nationCode 国家码，如 86 为中国
     * @param phoneNumber 不带国家码的手机号
     * @param templateId 信息内容
     * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
     * @param sign 签名，如果填空，系统会使用默认签名
     * @param extend 扩展码，可填空
     * @param ext 服务端原样返回的参数，可填空
     * @return {@link}SmsSingleSenderResult
     * @throws HTTPException  http status exception
     * @throws JSONException  json parse exception
     * @throws IOException    network problem
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public SmsSingleSenderResult sendWithParam(String nationCode, String phoneNumber, int templateId,
        ArrayList<String> params, String sign, String extend, String ext,int appid,String appkey,String url)
            throws HTTPException, JSONException, IOException {

        long random = SmsSenderUtil.getRandom();
        long now = SmsSenderUtil.getCurrentTime();
        JSONObject body = new JSONObject()
            .put("tel", (new JSONObject()).put("nationcode", nationCode).put("mobile", phoneNumber))
            
            .put("sig", SmsSenderUtil.calculateSignature(appkey, random, now, phoneNumber))
            .put("tpl_id", templateId)
            .put("params", params)
            .put("sign", sign)
            .put("time", now)
            .put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
            .put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

        HTTPRequest req = new HTTPRequest(HTTPMethod.POST, url)
            .addHeader("Conetent-Type", "application/json")
            .addQueryParameter("sdkappid", appid)
            .addQueryParameter("random", random)
            .setConnectionTimeout(60 * 1000)
            .setRequestTimeout(60 * 1000)
            .setBody(body.toString());

        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            // May throw JSONException
            return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
        } catch(URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
    }

    public SmsSingleSenderResult sendWithParam(String nationCode, String phoneNumber, int templateId,
        String[] params, String sign, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        return sendWithParam(nationCode, phoneNumber, templateId,
            new ArrayList<String>(Arrays.asList(params)), sign, extend, ext,0,null,null);
    }
    public ArrayList<String> getMegMc(double msgNum){
    	ArrayList<String> list=new ArrayList<String>();
    	String mobile_code = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10, msgNum-1)));
    	list.add(mobile_code);
    	list.add("5");
    	return list;
    }
	/*
	 * @Transactional(propagation = Propagation.REQUIRED, rollbackFor =
	 * Exception.class) public resultMsg localSend(String nationCode, String
	 * phoneNumber, int templateId, ArrayList<String> params, String sign, String
	 * extend, String ext) { resultMsg result=new resultMsg();
	 * log.debug("---------发送之前入库-----------");
	 * 
	 * MessageInfo messageInfo=new MessageInfo();
	 * messageInfo.setNationCode(nationCode); messageInfo.setPhoneNum(phoneNumber);
	 * //{1}为您的登录验证码，请于{2}分钟内填写。如非本人操作，请忽略本短信。
	 * messageInfo.setMessage(params.get(0)+"为您的登录验证码，请于"+params.get(1)+
	 * "分钟内填写。如非本人操作，请忽略本短信"); messageInfo.setCreateDate(new Date()); // 1 预发送 2失败 0
	 * 成功 messageInfo.setStatus((short)1);
	 * 
	 * int insert=0; try { insert = messageInfoMapper.insert(messageInfo); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * if(insert>0) { log.debug("入库成功{}",insert); SmsSingleSenderResult senderResult
	 * = smsSingleSender. sendMegPlat(nationCode, phoneNumber, templateId, params,
	 * sign, extend, ext); if(senderResult.result==0) {
	 * log.debug("------------短信平台返回success-------------"); Map map=new HashMap();
	 * map.put("status", 0); map.put("MsgId", messageInfo.getMsgId());
	 * log.debug("-----------更新数据库---------------");
	 * if(messageInfoMapper.updateByExampleSelective(map)>0) { result.setCode(0);
	 * result.setMessage("发送成功"); }else {
	 * log.debug("-----------更新数据库失败---------------"); result.setCode(1);
	 * result.setMessage("更新数据库失败"); } }else { result.setCode(senderResult.result);
	 * result.setMessage(senderResult.errMsg); } } return result; }
	 */
    /**
     * 通知短信平台
     * @param nationCode
     * @param phoneNumber
     * @param templateId
     * @param params
     * @param sign
     * @param extend
     * @param ext
     * @return
     */
	/*
	 * @Transactional(propagation = Propagation.REQUIRED, rollbackFor =
	 * Exception.class) public SmsSingleSenderResult sendMegPlat(String nationCode,
	 * String phoneNumber, int templateId, ArrayList<String> params, String sign,
	 * String extend, String ext) { smsSingleSender=new
	 * SmsSingleSender(appid,appkey); SmsSingleSenderResult result=new
	 * SmsSingleSenderResult(); try { log.debug("----------通知短信平台-------------");
	 * result = smsSingleSender.sendWithParam (nationCode, phoneNumber, templateId,
	 * params, sign, extend, ext,url);
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); } catch (HTTPException e) {
	 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } return
	 * result; }
	 */
}
