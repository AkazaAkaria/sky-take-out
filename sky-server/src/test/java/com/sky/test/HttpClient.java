package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author AkazaAkari
 * @version 1.0
 * @Date 2023/12/26 17:30
 * @Comment sky-take-out>xuzq
 * @className HttpClient
 * @description TODO
 */
// @SpringBootTest
public class HttpClient {
    /**
     * @return
     * @description //TODO 测试httpClient发送get请求
     * @Param
     */
    @Test
    public void testGET() throws Exception {
        // 创建httpcLient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");
        // 发送请求，接受响应结果
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 获取服务端返回的状态码I
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态码为：：" + statusCode);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println("服务端返回的数据为" + body);
        // 关闭资源
        response.close();
        httpClient.close();
    }

    /**
     * @return
     * @description //TODO 测试httpClient发送post请求
     * @Param
     */
    @Test
    public void testPOST() throws Exception {

        // 创建httpcLient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");
        // 设置请求参数 使用jsonObject 构造请求参数
        // 创建jsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");
        StringEntity entity = new StringEntity(jsonObject.toString());
        // 设置请求参数格式
        entity.setContentType("application/json");
        // 设置请求参数编码
        entity.setContentEncoding("UTF-8");
        httpPost.setEntity(entity);
        // 发送请求，接受响应结果
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        // 获取服务端返回的状态码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态码为：：" + statusCode);
        // 获取服务端返回的数据
        HttpEntity entity1 = httpResponse.getEntity();
        String body = EntityUtils.toString(entity1);
        System.out.println("服务端返回的数据为" + body);
        Map map = JSONObject.parseObject(body, Map.class);
        Map data = (Map)map.get("data");
        Object token = data.get("token");
        // 输出json
        System.out.println("token = " + token);
        // 关闭资源
        EntityUtils.consume(entity1);
        httpResponse.close();
        httpClient.close();
    }
}
