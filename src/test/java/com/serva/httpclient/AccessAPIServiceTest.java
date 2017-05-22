package com.serva.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.serva.model.Asset;
import com.serva.model.LoginResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by alexdong on 17/19/05.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccessAPIServiceTest {

    private AccessApiService service = new AccessApiService();
    private LoginResult loginResult;

    @Before
    public void before(){
        LoginResult result = service.auth();
        service.setLoginResult(result);
    }
    @Test
    public void test1Auth() throws Exception {
        LoginResult result = service.auth();
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getAccess_token());
    }

    @Test
    public void test2AddAsset() throws Exception {
        Asset asset = service.addAsset();
        Assert.assertNotNull(asset);
        Assert.assertEquals("equipment1", asset.getId());
    }

    @Test
    public void test3UpdateAsset() throws Exception {
        Asset asset = service.updateAsset();
        Assert.assertNotNull(asset);
        Assert.assertEquals("equipment123", asset.getName());
    }

    @Test
    public void testZDeleteAsset() throws Exception {
        Asset asset = service.deleteAsset();
        Assert.assertNotNull(asset);
        Assert.assertEquals("equipment1", asset.getId());
    }

    @Test
    public void test4AddAlarm() throws Exception {
        JSONObject jo = service.addAlarm();
        Assert.assertNull(jo.get("error"));
    }

    @Test
    public void test5UpdateAlarm() throws Exception {
        JSONObject jo = service.updateAlarm();
        Assert.assertNull(jo.get("error"));
    }

    @Test
    public void test6DeleteAlarm() throws Exception {
        JSONObject jo = service.deleteAlarm();
        Assert.assertNull(jo.get("error"));
    }

    @Test
    public void test7PullAPIData() throws Exception {
        JSONObject jo = service.pullAPIData();
        Assert.assertNull(jo.get("error"));
    }
}