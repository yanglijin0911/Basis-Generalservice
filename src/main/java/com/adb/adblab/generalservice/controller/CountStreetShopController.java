package com.adb.adblab.generalservice.controller;

import com.adb.adblab.generalservice.service.CountStreetShopService;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/generalservice")
public class CountStreetShopController {

    @Autowired
    private CountStreetShopService countStreetShopService;

    @RequestMapping(value="countStreetShop",method = RequestMethod.GET)
    public JSONArray countStreetShop(@RequestParam(value = "number") Integer number){

        JSONArray output = countStreetShopService.countStreetShop(number);
        return output;
    }
}
