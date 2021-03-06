package com.example.demo.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.demo.config.httpclient.HTTPClient;
import com.example.demo.config.httpclient.HTTPException;
import com.example.demo.config.httpclient.HTTPResponse;


@Configuration
public class SmsBase {
	 protected int appid;
	    protected String appkey;
    
    protected HTTPClient httpclient;
    /**
     * SmsBase constructor
     *
     * @param appid   sdk appid
     * @param appkey  sdk appkey
     * @param httpclient  http client
     */
    public SmsBase(int appid, String appkey, HTTPClient httpclient) {
        this.appid = appid;
        this.appkey = appkey;
        this.httpclient = httpclient;
    }
  public SmsBase() {}
    /**
     * Handle http status error
     *
     * @param response   raw http response
     * @return response  raw http response
     * @throws HTTPException  http status exception
     */
    public HTTPResponse handleError(HTTPResponse response) throws HTTPException {
        if (response.statusCode < 200 || response.statusCode >= 300) {
            throw new HTTPException(response.statusCode, response.reason);
        }
        return response;
    }
}
