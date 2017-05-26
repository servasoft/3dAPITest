package com.serva.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.serva.constant.Constant;
import com.serva.model.Alarm;
import com.serva.model.Asset;
import com.serva.model.LoginResult;
import com.serva.model.TempAsset;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexdong on 17/19/05.
 */
public class AccessApiService {

    private static LoginResult loginResult;

    public AccessApiService(){

    }

    public LoginResult auth(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Constant.URL+"auth");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "admin"));
            nvps.add(new BasicNameValuePair("password", "admin"));
            UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            httpPost.setEntity(reqEntity);

            System.out.println("executing request " + httpPost.getRequestLine());
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            LoginResult loginResult = (LoginResult)JSON.parseObject(responseText, LoginResult.class);

            EntityUtils.consume(resEntity);
            this.loginResult = loginResult;
            return loginResult;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Asset addAsset(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Constant.URL+"asset");
            Asset asset = new Asset();
            asset.setId("equipment1");
            asset.setName("equipment1");
            asset.setParentId("R102-1");
            asset.setDataTypeId("equipment1");
            httpPost.setEntity(new StringEntity(JSON.toJSONString(asset), ContentType.create("application/json", "UTF-8")));
            httpPost.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpPost.getRequestLine());
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            JSONObject result = JSON.parseObject(responseText);
            Asset resAsset = result.getObject("value", Asset.class);

            return resAsset;

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Asset updateAsset(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPut httpPut = new HttpPut(Constant.URL+"asset/equipment1");
            Asset asset = new Asset();
            asset.setName("equipment123");
            httpPut.setEntity(new StringEntity(JSON.toJSONString(asset), ContentType.create("application/json", "UTF-8")));
            httpPut.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpPut.getRequestLine());
            CloseableHttpResponse response = client.execute(httpPut);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            JSONObject result = JSON.parseObject(responseText);
            Asset resAsset = result.getObject("value", Asset.class);

            return resAsset;

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Asset deleteAsset(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpDelete httpDelete = new HttpDelete(Constant.URL+"asset/equipment1");
            httpDelete.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpDelete.getRequestLine());
            CloseableHttpResponse response = client.execute(httpDelete);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            JSONObject result = JSON.parseObject(responseText);
            Asset resAsset = result.getObject("value", Asset.class);

            return resAsset;

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject addAlarm() {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Constant.URL+"alarms");
            Alarm alarm = new Alarm();
            alarm.setAlarmId("test1");
            alarm.setAlarmType("temperature");
            alarm.setDeviceId("A01");
            alarm.setDescription("温度过高");
            alarm.setLevel("critical");
            JSONObject json = new JSONObject();
            json.put("module","PEMS");
            List<Alarm> alarms = new ArrayList<Alarm>();
            alarms.add(alarm);
            json.put("data", alarms.toArray());
            httpPost.setEntity(new StringEntity(JSON.toJSONString(json), ContentType.create("application/json", "UTF-8")));
            httpPost.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpPost.getRequestLine());
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            return JSON.parseObject(responseText);


        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject updateAlarm(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPut httpPut = new HttpPut(Constant.URL+"alarm/test1");
            Alarm alarm = new Alarm();
            alarm.setAckTime(new Date());
            alarm.setAckNotice("风沙眼");
            httpPut.setEntity(new StringEntity(JSON.toJSONString(alarm), ContentType.create("application/json", "UTF-8")));
            httpPut.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpPut.getRequestLine());
            CloseableHttpResponse response = client.execute(httpPut);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            JSONObject result = JSON.parseObject(responseText);

            return result;

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject deleteAlarm(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpDelete httpDelete = new HttpDelete(Constant.URL+"alarm/test1");
            httpDelete.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpDelete.getRequestLine());
            CloseableHttpResponse response = client.execute(httpDelete);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            return JSON.parseObject(responseText);

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject pushAPIData() {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Constant.URL+"data");

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
            httpPost.setEntity(new StringEntity(JSON.toJSONString(json), ContentType.create("application/json", "UTF-8")));
            httpPost.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpPost.getRequestLine());
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            return JSON.parseObject(responseText);


        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TempAsset addTempAsset(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Constant.URL+"tempAsset");

            List<TempAsset> assets = new ArrayList<TempAsset>();
            TempAsset asset = new TempAsset();
            asset.setId("equipment2");
            asset.setName("equipment2");
            asset.setDescription("test equipment2");
            assets.add(asset);
            JSONObject data = new JSONObject();
            data.put("data", assets);
            httpPost.setEntity(new StringEntity(JSON.toJSONString(data), ContentType.create("application/json", "UTF-8")));
            httpPost.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpPost.getRequestLine());
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            JSONObject result = JSON.parseObject(responseText);
            JSONArray resValue = result.getJSONArray("value");
            TempAsset resAsset = resValue.getObject(0, TempAsset.class);

            return resAsset;

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TempAsset deleteTempAsset(){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpDelete httpDelete = new HttpDelete(Constant.URL+"tempAsset/equipment2");
            httpDelete.setHeader("x-access-token",this.loginResult.getAccess_token());

            System.out.println("executing request " + httpDelete.getRequestLine());
            CloseableHttpResponse response = client.execute(httpDelete);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            String responseText = EntityUtils.toString(resEntity, "UTF-8");
            System.out.println("result=" + responseText);

            JSONObject result = JSON.parseObject(responseText);
            TempAsset resAsset = result.getObject("value", TempAsset.class);

            return resAsset;

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
