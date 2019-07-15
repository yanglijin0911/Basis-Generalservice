package com.adb.adblab.generalservice.controller;


import com.adb.adblab.generalservice.service.ShopNumPerBrandService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/generalservice")
public class ShopNumPerBrandController {

    @Autowired
    private ShopNumPerBrandService shopNumPerBrandService;

    @RequestMapping(value="queryAllBrandShopNumByProvinvial",method = RequestMethod.GET)
    public JSONObject queryAllBrandShopNumByProvinvial(@RequestParam(value = "pname") String pname){

        JSONObject output = shopNumPerBrandService.queryAllBrandShopNumByProvinvial(pname);
        return output;
    }

}
