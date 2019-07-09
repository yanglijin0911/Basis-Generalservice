package com.adb.adblab.generalservice.controller;


import com.adb.adblab.generalservice.input.GetLocationFromAmapInput;
import com.adb.adblab.generalservice.service.ShopLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/generalservice")
public class ShopLocationController {

    @Autowired
    private ShopLocationService shopLocationService;

    @RequestMapping(value="location",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public int getLocationFromAmap(@RequestBody GetLocationFromAmapInput input){
//        log.info("importRap()入参="+ JSON.toJSONString(input));

        int output = shopLocationService.getLocationFromAmap(input.getKeyword(),input.getCity());
        return output;
    }


}
