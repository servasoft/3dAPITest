package com.serva.restTemplate;

import com.alibaba.fastjson.JSONObject;
import com.serva.model.Asset;
import com.serva.model.LoginResult;
import com.serva.model.TempAsset;
import org.junit.*;
import org.junit.runners.MethodSorters;

/** 
* AccessApiService Tester. 
* 
* @author alex dong
* @since <pre>五月 26, 2017</pre> 
* @version 1.0 
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccessApiServiceTest {

    private AccessApiService service = new AccessApiService();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: auth()
    *
    */
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


} 
