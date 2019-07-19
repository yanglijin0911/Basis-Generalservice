package com.adb.adblab.generalservice.controller;

import com.adb.adblab.generalservice.input.QueryShopInfoBycityAndBrandInput;
import com.adb.adblab.generalservice.service.CountStreetShopService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest/generalservice")
public class CountStreetShopController {

    @Autowired
    private CountStreetShopService countStreetShopService;

    @RequestMapping(value="countStreetShop",method = RequestMethod.GET)
    public JSONArray countStreetShop(@RequestParam(value = "city") String city,@RequestParam(value = "number") Integer number){

        JSONArray output = countStreetShopService.countStreetShop(city,number);
        return output;
    }

    @RequestMapping(value="queryShopNumBycity",method = RequestMethod.GET)
    public JSONObject queryShopNumBycity(@RequestParam(value = "cityName") String cityName){

        JSONObject output = countStreetShopService.queryShopNumBycity(cityName);
        return output;
    }


    @RequestMapping(value="queryShopInfoBycityAndBrand",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject queryShopInfoBycityAndBrand(@RequestBody QueryShopInfoBycityAndBrandInput input){

        JSONObject output = countStreetShopService.queryShopInfoBycityAndBrand(input);
        return output;
    }

    @RequestMapping(value="queryAllCity",method = RequestMethod.GET)
    public JSONObject queryAllCity(){

        JSONObject output = countStreetShopService.queryAllCity();
        return output;
    }

}
