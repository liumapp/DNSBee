package com.liumapp.DNSBee.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liumapp on 8/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class HttpClientUtil {

    public String sendPost(String url , Map params) {

        String result = null;
        HttpClient httpclient = new DefaultHttpClient();//声明HttpClient实例

        httpclient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,   2000);
        httpclient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,  2000);

        //创建HttpPost对象，将要请求的URL通过构造方法传入HttpPost对象。
        HttpPost httpPost = new HttpPost(url);
        //建立一个NameValuePair数组，用于存储欲传送的参数
        List nvps = new ArrayList();
        Set keySet = params.keySet();

        for(Object key : keySet) {
            //添加参数
            nvps.add(new BasicNameValuePair((String) key, (String) params.get(key)));
        }

        try {

            //使用HttpPost方法提交HTTP POST请求，则需要使用HttpPost类的setEntity方法设置请
            //求参数。参数则必须用NameValuePair[]数组存储。
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));//设置编码

            // 使用DefaultHttpClient类的execute方法发送HTTP GET或HTTP POST请求，
            HttpResponse response = httpclient.execute(httpPost);

            //并返回HttpResponse对象。
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();

            //确认发送
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {//链接成功
                //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理。
                result = EntityUtils.toString(entity);
            } else {
                //发送失败时的处理
                if (statusLine.getStatusCode() == 302) {
                    //重定向
                    String locationUrl=response.getLastHeader("Location").getValue();
                    System.out.println(locationUrl);
                }
            }

            httpPost.releaseConnection();

            return result;

        } catch (ClientProtocolException e) {

            return e.getMessage();

        } catch (IOException e) {

            return e.getMessage();

        }  finally {

            httpPost.releaseConnection();//关闭连接

        }

    }

}
