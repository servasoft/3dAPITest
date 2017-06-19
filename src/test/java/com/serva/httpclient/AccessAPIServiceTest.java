package com.serva.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.serva.model.Asset;
import com.serva.model.LoginResult;
import com.serva.model.TempAsset;
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

    @Before
    public void before(){

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
    public void test7PushAPIData() throws Exception {
        JSONObject jo = service.pushAPIData();
        Assert.assertNull(jo.get("error"));
    }

    @Test
    public void test8AddTempAsset() throws Exception{
        TempAsset asset = service.addTempAsset();
        Assert.assertNotNull(asset);
        Assert.assertEquals("equipment2", asset.getId());
    }
    @Test
    public void test9DeleteTempAsset() throws Exception {
        TempAsset asset = service.deleteTempAsset();
        Assert.assertNotNull(asset);
        Assert.assertEquals("equipment2", asset.getId());
    }
    @Test
    public void testSynchronousAsset() throws Exception {
        Asset asset = service.synchronousAsset();
        Assert.assertNotNull(asset);
    }
}