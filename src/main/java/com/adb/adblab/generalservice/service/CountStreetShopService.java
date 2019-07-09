package com.adb.adblab.generalservice.service;

import com.adb.adblab.generalservice.entity.*;
import com.adb.adblab.generalservice.mapper.CountStreetShopExtMapper;
import com.adb.adblab.generalservice.mapper.ProvincialExtMapper;
import com.adb.adblab.generalservice.mapper.StreetShopExtMapper;
import com.adb.adblab.generalservice.mapper.VehicleMapExtMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountStreetShopService {

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
    public JSONArray countStreetShop(Integer number){
        JSONArray output = new JSONArray();
        //同一条路或街道大于3家的店铺
        List<CountStreetShop> countStreetShops = countStreetShopExtMapper.selectByNumber(number);
        for (CountStreetShop css:countStreetShops) {
            JSONObject output0 = new JSONObject();
            output0.put("city",css.getPname());
            output0.put("cityname",css.getCityname());
            output0.put("street",css.getStreetAddress());
            output0.put("number",css.getNumber());

            List<StreetShop> StreetShops = streetShopExtMapper.selectByPnameAndCityname(css.getPname(), css.getCityname(), css.getAdname(),css.getStreetAddress());
            output0.put("shops",JSONArray.parseArray(JSON.toJSONString(StreetShops)));
            output.add(output0);
        }
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
}
