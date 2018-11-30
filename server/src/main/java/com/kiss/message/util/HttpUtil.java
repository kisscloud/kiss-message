package com.kiss.message.util;

import org.apache.http.*;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HttpUtil {

//    private static Logger logger = Logger.getLogger(HttpUtil.class);
    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */

    public static String doPost(String url, String params){

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader(HttpHeaders.CONNECTION, "close");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                System.out.println("返回数据："+jsonString);
                return jsonString;
            }
            else{
                httpPost.abort();
            }
        }catch (Exception e){
            httpPost.abort();
        }
        finally {
            httpPost.abort();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpPost.releaseConnection();
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String formDataPost(String url, List<NameValuePair> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
//        CloseableHttpClient httpclient = HttpConnectionManager.httpClient;
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader(HttpHeaders.CONNECTION,"close");
        String charSet = "UTF-8";
        CloseableHttpResponse response = null;

        try {
            StringEntity entity = new UrlEncodedFormEntity(params,charSet);
            httpPost.setEntity(entity);
            //setConnectTimeout：设置连接超时时间，单位毫秒。setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
            RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(600000).setConnectionRequestTimeout(600000).setSocketTimeout(600000).setExpectContinueEnabled(true)
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            httpPost.setConfig(defaultRequestConfig);

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity,charSet);
                return jsonString;
            } else {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity,charSet);
                httpPost.abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpPost.abort();
        }
        finally {
            httpPost.abort();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpPost.releaseConnection();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void close(CloseableHttpResponse rsp){
        if(rsp != null){
            try {
                EntityUtils.consume(rsp.getEntity());
                rsp.close();
            } catch (Exception e) {
//				log.error(null,e);
            }
        }
    }
}
