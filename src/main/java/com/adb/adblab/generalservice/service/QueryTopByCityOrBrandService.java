package com.adb.adblab.generalservice.service;

import com.adb.adblab.generalservice.entity.TopBrandByCity;
import com.adb.adblab.generalservice.entity.TopCityByBrand;
import com.adb.adblab.generalservice.entity.VehicleName;
import com.adb.adblab.generalservice.mapper.TopBrandByCityExtMapper;
import com.adb.adblab.generalservice.mapper.TopCityByBrandExtMapper;
import com.adb.adblab.generalservice.mapper.VehicleMapExtMapper;
import com.adb.adblab.generalservice.mapper.VehicleNameExtMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class QueryTopByCityOrBrandService {

    @Autowired
    private VehicleMapExtMapper vehicleMapExtMapper;
    @Autowired
    private TopBrandByCityExtMapper topBrandByCityExtMapper;
    @Autowired
    private VehicleNameExtMapper vehicleNameExtMapper;
    @Autowired
    private TopCityByBrandExtMapper topCityByBrandExtMapper;


    public JSONObject queryTopBrandByCity(String cityname){
        JSONObject output = new JSONObject();
        Example example = new Example(TopBrandByCity.class);
        example.createCriteria().andEqualTo("cityname",cityname);
        List<TopBrandByCity> list = topBrandByCityExtMapper.selectByExample(example);
        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",JSONArray.parseArray(JSON.toJSONString(list)));
        return output;
    }


    /**
     *
     * @param brand
     * @return
     */
    public JSONObject queryTopCityByBrand(String brand){
        JSONObject output = new JSONObject();
        Example example = new Example(TopCityByBrand.class);
        example.createCriteria().andEqualTo("brandName",brand);
        List<TopCityByBrand> list = topCityByBrandExtMapper.selectByExample(example);
        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",JSONArray.parseArray(JSON.toJSONString(list)));
        return output;
    }



    public void topBrandTheCity(){
        List<String> allCity = vehicleMapExtMapper.getAllCity();
        for (String cityname: allCity) {
            List<TopBrandByCity> topBrandByCities = vehicleMapExtMapper.selectTopBrandByCityname(cityname);
            insertTopBrandByCity(topBrandByCities);
            System.out.println(JSON.toJSON(topBrandByCities));
        }

        System.out.println(allCity.size());
    }

    public void topCityTheBrand(){
        List<VehicleName> vehicleNames = vehicleNameExtMapper.selectAll();
        for (VehicleName vn:vehicleNames) {
            List<TopCityByBrand> topCityByBrands = vehicleMapExtMapper.selectTopCityByBrand(vn.getVehicleName());
            insertTopCityByBrand(topCityByBrands);
            System.out.println(JSON.toJSON(topCityByBrands));
        }
    }


    /**
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertTopBrandByCity(List<TopBrandByCity> list){
        for (TopBrandByCity t:list) {
            topBrandByCityExtMapper.insertSelective(t);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void insertTopCityByBrand(List<TopCityByBrand> list){
        for (TopCityByBrand t:list) {
            topCityByBrandExtMapper.insertSelective(t);
        }

    }
}
