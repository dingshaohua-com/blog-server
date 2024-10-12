package com.dshvv.blogserver.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Component;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

@Component
public class HttpHelper {

    /*
     * 根据url地址返回json
     * */
    public JSONObject sendGetReturnJson(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求
        CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        // 获取响应体
        HttpEntity entity = response.getEntity();
        // 获取响应内容
        String contentStr = EntityUtils.toString(entity, "utf-8");
        // 关闭连接,释放资源
        response.close();
        httpClient.close();
        return JSONObject.parseObject(contentStr);
    }

    /*
     * 根据url地址返回二进制
     * */
    public BufferedInputStream sendGetReturnStream(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求
        CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        // 获取响应体
        HttpEntity entity = response.getEntity();
        // 获取响应内容
        byte[] contentByteArray = EntityUtils.toByteArray(entity);
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(contentByteArray);
        BufferedInputStream imgStream = new BufferedInputStream(byteInputStream);
        // 关闭连接,释放资源
        response.close();
        httpClient.close();
        return imgStream;
    }
}
