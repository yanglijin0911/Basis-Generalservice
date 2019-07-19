package com.adb.adblab.generalservice.service;

import com.adb.adblab.generalservice.entity.*;
import com.adb.adblab.generalservice.mapper.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class QueryVehShopService {

    @Autowired
    private VehicleMapExtMapper vehicleMapExtMapper;
    @Autowired
    private ProvincialExtMapper provincialExtMapper;
    @Autowired
    private VehicleNameExtMapper vehicleNameExtMapper;
    @Autowired
    private CountProvincialNumExtMapper countProvincialNumExtMapper;
    @Autowired
    private CountVehicleNumExtMapper countVehicleNumExtMapper;
    @Autowired
    private CountProvincialBrandExtMapper cpbMapper;
    @Autowired
    private CountProvincialCitynameExtMapper cpcMapper;

    /**
     * @param city
     * @param keyword
     * @return
     */
    public JSONObject queryShopInfo(String city, String brand, String keyword){
        JSONObject output = new JSONObject();

        List<VehicleMap> vehicleMaps = new ArrayList<>();
        if(StringUtils.isBlank(city)  && StringUtils.isBlank(brand) && StringUtils.isBlank(keyword)){
            vehicleMaps = vehicleMapExtMapper.selectAll();
        }else if(StringUtils.isNotBlank(city)  && StringUtils.isBlank(brand)){
            vehicleMaps = vehicleMapExtMapper.selectByCity(city,keyword);
        }else if(StringUtils.isBlank(city)  && StringUtils.isNotBlank(brand)){
            vehicleMaps = vehicleMapExtMapper.selectByBrand(brand,keyword);
        }else if(StringUtils.isNotBlank(city) && StringUtils.isNotBlank(brand)){
            vehicleMaps = vehicleMapExtMapper.selectByCityAndBrand(city,brand,keyword);
        }

        System.out.println(vehicleMaps.size());

        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",vehicleMaps);

        return output;
    }

    /**
     * 查询所有省份
     * @return
     */
    public JSONObject selectProvincials(){
        JSONObject output = new JSONObject();
        JSONArray pros = new JSONArray();
        List<Provincial> provincials = provincialExtMapper.selectAll();
        for (Provincial pro:provincials) {
            pros.add(pro.getProvincial());
        }

        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",pros);
        return output;
    }

    /**
     * 查询所有车辆的名字
     * @return
     */
    public JSONObject selectVehNames(){
        JSONObject output = new JSONObject();
        JSONArray vehNames = new JSONArray();
        List<VehicleName> VehicleNames = vehicleNameExtMapper.selectAll();
        for (VehicleName veh : VehicleNames) {
            vehNames.add(veh.getVehicleName());
        }

        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",vehNames);

        return output;
    }

    /**
     * 以城市维度做统计
     * @return
     */
    public JSONObject countShopNumByProvincial(String pname){
        JSONObject output = new JSONObject();
        List list = new ArrayList<>();

        if(StringUtils.isBlank(pname)){
            list = countProvincialNumExtMapper.selectAll();
        }else {
            list = cpcMapper.selectCityShopNumWithCityCodeByProvincial(pname);
        }

        if(CollectionUtils.isEmpty(list)){
            output.put("statusCode",400);
            output.put("msg","查询的数据为null");
            output.put("data",new ArrayList<>());
        }else {
            output.put("statusCode",200);
            output.put("msg","成功");
            output.put("data",JSONArray.parseArray(JSON.toJSONString(list)));
        }

        return output;
    }

    /**
     * 以车的品牌维度做统计
     * @return
     */
    public JSONObject countNumByVehName(String pname){
        JSONObject output = new JSONObject();
        List list = new ArrayList<>();
        if(StringUtils.isBlank(pname)){
            list = countVehicleNumExtMapper.selectAll();
        }else {
            Example example = new Example(CountProvincialBrand.class);
            example.createCriteria().andEqualTo("pname",pname);
            list = cpbMapper.selectByExample(example);
        }
        if(CollectionUtils.isEmpty(list)){
            output.put("statusCode",400);
            output.put("msg","查询的数据为null");
            output.put("data",new ArrayList<>());
        }else {
            output.put("statusCode",200);
            output.put("msg","成功");
            output.put("data",JSONArray.parseArray(JSON.toJSONString(list)));
        }
        return output;
    }

    /**
     * 统计所有省份的店铺数量
     */
    public void countVehByPro(){
        List<CountProvincialNum> list = new ArrayList<>();
        List<Map<String, Object>> stringIntegerMap = vehicleMapExtMapper.selectGroupByPname();

        for (Map<String, Object> map : stringIntegerMap) {
            CountProvincialNum countProvincialNum = new CountProvincialNum();
            countProvincialNum.setPname(map.get("pname").toString());
            countProvincialNum.setNumber(Integer.valueOf(map.get("number").toString()));
            if(map.get("pname").toString().contains("[")){
                continue;
            }
            list.add(countProvincialNum);
        }
        for(int i=0;i<list.size();i++){
            countProvincialNumExtMapper.insertSelective(list.get(i));
        }
    }

    public void countByVehBrand(){
        List<CountVehicleNum> list = new ArrayList<>();
        List<Map<String, Object>> stringIntegerMap = vehicleMapExtMapper.selectGroupByBrandName();

        for (Map<String, Object> map : stringIntegerMap) {
            CountVehicleNum countVehicleNum = new CountVehicleNum();
            countVehicleNum.setVehicleName(map.get("brand_name").toString());
            countVehicleNum.setNumber(Integer.valueOf(map.get("number").toString()));
//
            if(map.get("pname").toString().contains("[")){
                continue;
            }
            list.add(countVehicleNum);
        }
        System.out.println(list);
//        for(int i=0;i<list.size();i++){
//            countVehicleNumExtMapper.insertSelective(list.get(i));
//        }
    }

}
