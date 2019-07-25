package com.adb.adblab.generalservice.controller;

import com.adb.adblab.generalservice.service.CityGdpService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/generalservice")
public class CityGdpController {
    @Autowired
    private CityGdpService cityGdpService;

    @RequestMapping(value="getAllCityCapitaGDP",method = RequestMethod.GET)
    public JSONObject getAllCityCapitaGDP(){

        JSONObject output = cityGdpService.getAllCityCapitaGDP();
        return output;
    }
}
