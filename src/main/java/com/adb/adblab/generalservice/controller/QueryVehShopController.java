package com.adb.adblab.generalservice.controller;

import com.adb.adblab.generalservice.input.QueryVehicleInfoInput;
import com.adb.adblab.generalservice.service.QueryVehShopService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/rest/generalservice")
public class QueryVehShopController {

    @Autowired
    private QueryVehShopService queryVehShopService;

    @RequestMapping(value="queryVehInfo",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject queryVehInfo(@RequestBody QueryVehicleInfoInput input){

        JSONObject vehicleMaps = queryVehShopService.queryShopInfo(input.getCityname(),input.getBrand(), input.getKeyword());
        return vehicleMaps;
    }

    @RequestMapping(value="queryProvincial",method = RequestMethod.GET)
    public JSONObject queryProvincial(){

        JSONObject output = queryVehShopService.selectProvincials();
        return output;
    }

    @RequestMapping(value="queryVehName",method = RequestMethod.GET)
    public JSONObject queryVehName(){

        JSONObject vehNames = queryVehShopService.selectVehNames();
        return vehNames;
    }

    @RequestMapping(value="countNumByProvincial",method = RequestMethod.GET)
    public JSONObject countNumByProvincial(@RequestParam(value = "pname") String pname){

        JSONObject output = queryVehShopService.countNumByProvincial(pname);
        return output;
    }


    @RequestMapping(value="countNumByVehName",method = RequestMethod.GET)
    public JSONObject countNumByVehName(@RequestParam(value = "pname") String pname){

        JSONObject output = queryVehShopService.countNumByVehName(pname);
        return output;
    }

}
