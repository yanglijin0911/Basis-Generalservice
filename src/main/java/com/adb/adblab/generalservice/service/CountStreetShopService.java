package com.adb.adblab.generalservice.service;

import com.adb.adblab.generalservice.entity.*;
import com.adb.adblab.generalservice.input.QueryShopInfoBycityAndBrandInput;
import com.adb.adblab.generalservice.mapper.CountStreetShopExtMapper;
import com.adb.adblab.generalservice.mapper.ProvincialExtMapper;
import com.adb.adblab.generalservice.mapper.StreetShopExtMapper;
import com.adb.adblab.generalservice.mapper.VehicleMapExtMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountStreetShopService {

    private static final String gdurl = "https://restapi.amap.com/v3/road/roadname?";
    private static final String key = "cf2ce6129415e8ff02e6e729eb6339b2";

    @Autowired
    private VehicleMapExtMapper vmMapper;
    @Autowired
    private ProvincialExtMapper provincialExtMapper;
    @Autowired
    private StreetShopExtMapper streetShopExtMapper;
    @Autowired
    private CountStreetShopExtMapper countStreetShopExtMapper;

    /**
     * rest 接口对外提供获取同一街道超过number家店的信息
     * @param number
     * @return
     */
    public JSONArray countStreetShop(String city,Integer number){
        JSONArray output = new JSONArray();
        List<CountStreetShop> countStreetShops =null;
        if(StringUtils.isBlank(city)){
            //同一条路或街道大于3家的店铺
           countStreetShops = countStreetShopExtMapper.selectByNumber(number);

        }else {
            Example example = new Example(CountStreetShop.class);
            if(city.contains("上海") || city.contains("北京") || city.contains("天津") || city.contains("重庆")){
                example.createCriteria().andEqualTo("pname",city).andEqualTo("number",number);
            }else {
                example.createCriteria().andEqualTo("cityname",city).andEqualTo("number",number);
            }
            countStreetShops = countStreetShopExtMapper.selectByExample(example);
        }

        for (CountStreetShop css:countStreetShops) {
            JSONObject output0 = new JSONObject();
            output0.put("city",css.getPname());
            output0.put("cityname",css.getCityname());
            output0.put("street",css.getStreetAddress());
            output0.put("number",css.getNumber());

            List<StreetShop> StreetShops = streetShopExtMapper.selectByPnameAndCityname(css.getPname(), css.getCityname(),
                    css.getAdname(),css.getStreetAddress());
//            for (StreetShop sp : StreetShops) {
//                if(StringUtils.isBlank(sp.getRoadLocation())){
//                    String roadLocation = getRoadLocation(sp.getCityname(), sp.getStreetAddress());
//                    sp.setRoadLocation(roadLocation);
//
//                    streetShopExtMapper.updateByPrimaryKey(sp);
//                }
//            }
            output0.put("shops",JSONArray.parseArray(JSON.toJSONString(StreetShops)));
            output.add(output0);
        }
        return output;
    }

    public JSONObject queryShopNumBycity(String cityname){
        JSONObject output = new JSONObject();
        Example example = new Example(StreetShop.class);
        example.createCriteria().andEqualTo("cityname",cityname);
        List<StreetShop> streetShops = streetShopExtMapper.selectByExample(example);
        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",streetShops.size());
        return output;
    }

    public JSONObject queryShopInfoBycityAndBrand(QueryShopInfoBycityAndBrandInput input){
        JSONObject output = new JSONObject();
        List<String> brandNames = input.getBrandNames();
        String cityName = input.getCityName();
        List<StreetShop> streetShops = null;

        if(StringUtils.isBlank(cityName) && CollectionUtils.isEmpty(brandNames)){
            streetShops = streetShopExtMapper.selectAll();
            System.out.println(streetShops.size());
            return output;
        }

        if(StringUtils.isNotBlank(cityName) && !CollectionUtils.isEmpty(brandNames)){
            Example example = new Example(StreetShop.class);
            example.createCriteria().andLike("cityname", "%" + cityName + "%")
                    .andIn("brandName",brandNames);
            streetShops = streetShopExtMapper.selectByExample(example);
        }

        if(StringUtils.isBlank(cityName) && !CollectionUtils.isEmpty(brandNames)){
            Example example = new Example(StreetShop.class);
            example.createCriteria().andIn("brandName",brandNames);
            streetShops = streetShopExtMapper.selectByExample(example);
        }

        if(StringUtils.isNotBlank(cityName) && CollectionUtils.isEmpty(brandNames)){
            Example example = new Example(StreetShop.class);
            example.createCriteria().andEqualTo("cityname",cityName);
            streetShops = streetShopExtMapper.selectByExample(example);
        }

        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",streetShops);
        return output;
    }

    public JSONObject queryAllCity(){
        JSONObject output = new JSONObject();
        List<String> list = streetShopExtMapper.selectAllCity();
        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",JSONArray.parseArray(JSON.toJSONString(list)));
        return output;
    }

    /**
     * 过滤处理address字段，变成xx街道或xx路
     */
    public void cleanAddress(){
        List<StreetShop> list = new ArrayList<>();
        List<Provincial> provincials = provincialExtMapper.selectAll();
        for (Provincial p : provincials) {
            Example example = new Example(VehicleMap.class);
            example.createCriteria().andEqualTo("pname",p.getProvincial());
            List<VehicleMap> vehicleMaps = vmMapper.selectByExample(example);
            if(CollectionUtils.isEmpty(vehicleMaps)){
                continue;
            }

            for (VehicleMap am :vehicleMaps) {
                String address = am.getAddress();
                String addressOfStreet = "";
                if (address.contains("[")) {
                    continue;
                }

                if(address.contains("镇")){
                    address = address.substring(address.indexOf("镇")+1,address.length());
                }
                if(address.contains("街")){
                    addressOfStreet = address.substring(0,address.indexOf("街")+1);
                } else if(address.contains("路")){
                    addressOfStreet = address.substring(0,address.indexOf("路")+1);
                } else {
                    addressOfStreet = address;
                }

                StreetShop streetShop = new StreetShop();
                streetShop.setId(am.getId());
                streetShop.setPname(am.getPname());
                streetShop.setCityname(am.getCityname());
                streetShop.setAdname(am.getAdname());
                if(StringUtils.isBlank(addressOfStreet)){
                    streetShop.setStreetAddress(am.getAddress());
                }else {
                    streetShop.setStreetAddress(addressOfStreet);
                }

                streetShop.setAddress(am.getAddress());
                streetShop.setBrandName(am.getBrandName());
                streetShop.setLocation(am.getLocation());
                streetShop.setTel(am.getTel());
                list.add(streetShop);
            }
        }

        for (StreetShop st:list) {
            streetShopExtMapper.insert(st);
        }
    }

    public void insertCountStreetShop(){
        List<CountStreetShop> CountStreetShops = streetShopExtMapper.selectStreetShopByGroup();
        for (CountStreetShop css :CountStreetShops) {
            countStreetShopExtMapper.insertSelective(css);
        }
    }

    public String getRoadLocation(String city,String road){
        String url = gdurl+"keywords="+road+"&city="+city+"&offset=1&output=json"+"&key="+key;
//        System.out.println(url);

        JSONArray roadLocation = null;
        try {
            HttpGet request = new HttpGet(url);//这里发送get请求
            HttpClient httpClient = new DefaultHttpClient();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String result= EntityUtils.toString(response.getEntity(),"utf-8");

                JSONObject mapJson = JSONObject.parseObject(result);
                roadLocation = mapJson.getJSONArray("roads").getJSONObject(0).getJSONArray("polylines");
//                System.out.println(roadLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roadLocation.toJSONString();
    }

    public void updateRoadLocation(){
        List<StreetShop> streetShops = streetShopExtMapper.selectAll();
        for (StreetShop s:streetShops) {
            String roadLocation = getRoadLocation(s.getCityname(), s.getStreetAddress());
            s.setRoadLocation(roadLocation);
            streetShopExtMapper.updateByPrimaryKey(s);
            System.out.println(JSON.toJSON(s));
        }

    }
}
