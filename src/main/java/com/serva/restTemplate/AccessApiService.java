package com.serva.restTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.serva.constant.Constant;
import com.serva.model.Alarm;
import com.serva.model.Asset;
import com.serva.model.LoginResult;
import com.serva.model.TempAsset;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexdong on 17/26/05.
 */
public class AccessApiService {

    private static RestTemplate restTemplate;
    private static LoginResult loginResult;

    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(5000);
        requestFactory.setConnectTimeout(5000);

        // 添加转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new FastJsonHttpMessageConverter());

        restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

    }

    public LoginResult auth(){
        MultiValueMap<String,String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("username","admin");
        bodyMap.add("password","admin");
        LoginResult result = restTemplate.postForObject(Constant.URL + "auth", bodyMap, LoginResult.class);
        loginResult = result;
        return result;
    }

    public Asset addAsset(){

        Asset asset = new Asset();
        asset.setId("equipment1");
        asset.setName("equipment1");
        asset.setParentId("rack2501");
        asset.setDataTypeId("HAIDE-server2301");

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity requestEntity  = new HttpEntity(asset, headers);
        String responseText = restTemplate.postForObject(Constant.URL+"asset", requestEntity, String.class);

        System.out.println("----------------------------------------");
        System.out.println(responseText);

        JSONObject result = JSON.parseObject(responseText);
        Asset resAsset = result.getObject("value", Asset.class);
        return resAsset;
    }

    public Asset updateAsset(){
        Asset asset = new Asset();
        asset.setName("equipment123");

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity reqEntity  = new HttpEntity(asset, headers);

        ResponseEntity<String> repEntity = restTemplate.exchange(Constant.URL+"asset/equipment1", HttpMethod.PUT, reqEntity, String.class);
        System.out.println("----------------------------------------");
        System.out.println(repEntity.getBody());

        JSONObject result = JSON.parseObject(repEntity.getBody());
        Asset resAsset = result.getObject("value", Asset.class);

        return resAsset;
    }

    public Asset deleteAsset(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity reqEntity  = new HttpEntity(null, headers);

        ResponseEntity<String> repEntity = restTemplate.exchange(Constant.URL+"asset/equipment1", HttpMethod.DELETE, reqEntity, String.class);
        System.out.println("----------------------------------------");
        System.out.println(repEntity.getBody());

        JSONObject result = JSON.parseObject(repEntity.getBody());
        Asset resAsset = result.getObject("value", Asset.class);

        return resAsset;
    }

    public JSONObject addAlarm() {
        Alarm alarm = new Alarm();
        alarm.setAlarmId("test1");
        alarm.setAlarmType("temperature");
        alarm.setDeviceId("equipment1");
        alarm.setDescription("温度过高");
        alarm.setLevel("critical");
        JSONObject json = new JSONObject();
        json.put("module","PEMS");
        List<Alarm> alarms = new ArrayList<Alarm>();
        alarms.add(alarm);
        json.put("data", alarms.toArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity reqEntity  = new HttpEntity(json, headers);

        String responseText = restTemplate.postForObject(Constant.URL+"alarms", reqEntity, String.class);

        System.out.println("----------------------------------------");
        System.out.println(responseText);

        return JSON.parseObject(responseText);
    }

    public JSONObject updateAlarm(){
        Alarm alarm = new Alarm();
        alarm.setAckTime(new Date());
        alarm.setAckNotice("风沙眼");

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity reqEntity  = new HttpEntity(alarm, headers);

        ResponseEntity<String> repEntity = restTemplate.exchange(Constant.URL+"alarm/test1", HttpMethod.PUT, reqEntity, String.class);
        System.out.println("----------------------------------------");
        System.out.println(repEntity.getBody());

        return JSON.parseObject(repEntity.getBody());
    }

    public JSONObject deleteAlarm(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity reqEntity  = new HttpEntity(null, headers);

        ResponseEntity<String> repEntity = restTemplate.exchange(Constant.URL+"alarm/test1", HttpMethod.DELETE, reqEntity, String.class);
        System.out.println("----------------------------------------");
        System.out.println(repEntity.getBody());

        return JSON.parseObject(repEntity.getBody());
    }

    public JSONObject pushAPIData() {
        JSONObject json = new JSONObject();
        json.put("module","PEMS");
        JSONObject data = new JSONObject();
        JSONObject th1 = new JSONObject();
        th1.put("temperature",30);
        th1.put("humidity",20);
        data.put("th01", th1);
        JSONObject th2 = new JSONObject();
        th2.put("temperature",24);
        th2.put("humidity",25);
        data.put("th02", th2);
        json.put("data", data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity reqEntity  = new HttpEntity(json, headers);

        String responseText = restTemplate.postForObject(Constant.URL+"data", reqEntity, String.class);

        System.out.println("----------------------------------------");
        System.out.println(responseText);

        return JSON.parseObject(responseText);
    }

    public TempAsset addTempAsset(){
        List<TempAsset> assets = new ArrayList<TempAsset>();
        TempAsset asset = new TempAsset();
        asset.setId("equipment2");
        asset.setName("equipment2");
        asset.setDescription("test equipment2");
        assets.add(asset);
        JSONObject data = new JSONObject();
        data.put("data", assets);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity reqEntity  = new HttpEntity(data, headers);

        String responseText = restTemplate.postForObject(Constant.URL+"tempAsset", reqEntity, String.class);

        System.out.println("----------------------------------------");
        System.out.println(responseText);

        JSONObject result = JSON.parseObject(responseText);
        JSONArray resValue = result.getJSONArray("value");
        TempAsset resAsset = resValue.getObject(0, TempAsset.class);

        return resAsset;
    }

    public TempAsset deleteTempAsset(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-access-token", loginResult.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity reqEntity  = new HttpEntity(null, headers);

        ResponseEntity<String> repEntity = restTemplate.exchange(Constant.URL+"tempAsset/equipment2", HttpMethod.DELETE, reqEntity, String.class);
        System.out.println("----------------------------------------");
        System.out.println(repEntity.getBody());

        JSONObject result = JSON.parseObject(repEntity.getBody());
        TempAsset resAsset = result.getObject("value", TempAsset.class);

        return resAsset;
    }
}
