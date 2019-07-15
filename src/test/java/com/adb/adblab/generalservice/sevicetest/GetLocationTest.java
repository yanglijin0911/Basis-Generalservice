package com.adb.adblab.generalservice.sevicetest;

import com.adb.adblab.generalservice.GeneralserviceApplicationTests;
import com.adb.adblab.generalservice.mapper.StreetShopExtMapper;
import com.adb.adblab.generalservice.service.*;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneralserviceApplicationTests.class)
public class GetLocationTest {
    @Autowired
    ShopLocationService shopLocationService;
    @Autowired
    QueryVehShopService queryVehShopService;
    @Autowired
    QueryTopByCityOrBrandService queryTopByCityOrBrandService;
    @Autowired
    BaiduPOI baiduPOI;
    @Autowired
    CountStreetShopService cps;
    @Autowired
    CountStreetShopService countStreetShopService;
    @Autowired
    ShopNumPerBrandService shopNumPerBrandService;

    @Autowired
    StreetShopExtMapper streetShopExtMapper;


    @Test
    public void test(){

        try {
            String urlNameString = "https://restapi.amap.com/v3/place/text?keywords=爱玛电动车专卖店&city=shanghai&output=json&offset=200&page=1&key=cf2ce6129415e8ff02e6e729eb6339b2&extensions=all";
            String result="";
            try {
                // 根据地址获取请求
                HttpGet request = new HttpGet(urlNameString);//这里发送get请求
                // 获取当前客户端对象
                HttpClient httpClient = new DefaultHttpClient();
                // 通过请求对象获取响应对象
                HttpResponse response = httpClient.execute(request);

                // 判断网络连接状态码是否正常(0--200都数正常)
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result= EntityUtils.toString(response.getEntity(),"utf-8");
                    System.out.print(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void LocationTest(){

        shopLocationService.insetAllProData();

    }

    @Test
    public void countVehByPro(){

//        queryVehicleInfo.countVehByPro();
        queryVehShopService.countByVehBrand();
    }

    @Test
    public void baiduPOI(){

        baiduPOI.getLocationFromAmap("爱玛电动车","上海");
    }

    @Test
    public void count(){
        cps.cleanAddress();
    }

    @Test
    public void countStreet(){
        cps.insertCountStreetShop();
    }

    @Test
    public void getAllcity(){
//        topByCityOrBrandService.topBrandTheCity();
        queryTopByCityOrBrandService.topCityTheBrand();
    }

    @Test
    public void getLocation(){
//        String roadLocation = countStreetShopService.getRoadLocation("幸福大街", "北京");
//        System.out.println(roadLocation);
        countStreetShopService.updateRoadLocation();
    }

    @Test
    public void testShopNumPerBrand(){
//        shopNumPerBrandService.cityShopNumPerBrand(5);
    }

    @Test
    public void insertCity(){
        List<String> list = streetShopExtMapper.selectAllCity();
        System.out.println(list.size());
        System.out.println(JSON.toJSON(list));
    }
}

