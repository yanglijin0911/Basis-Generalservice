package com.adb.adblab.generalservice.controller;

import com.adb.adblab.generalservice.service.QueryTopByCityOrBrandService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/generalservice")
public class QueryTopByCityOrBrandController {

    @Autowired
    private QueryTopByCityOrBrandService queryTopByCityOrBrandService;

    @RequestMapping(value="queryTopBrandByCity",method = RequestMethod.GET)
    public JSONObject queryTopBrandByCity(@RequestParam(value = "cityname") String cityname){

        JSONObject output = queryTopByCityOrBrandService.queryTopBrandByCity(cityname);
        return output;
    }

    @RequestMapping(value="queryTopCityByBrand",method = RequestMethod.GET)
    public JSONObject queryTopCityByBrand(@RequestParam(value = "brand") String brand){

        JSONObject output = queryTopByCityOrBrandService.queryTopCityByBrand(brand);
        return output;
    }
}
