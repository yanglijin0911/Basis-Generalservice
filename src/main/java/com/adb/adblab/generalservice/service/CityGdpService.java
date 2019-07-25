package com.adb.adblab.generalservice.service;

import com.adb.adblab.generalservice.entity.CityGdp;
import com.adb.adblab.generalservice.entity.CountProvincialBrand;
import com.adb.adblab.generalservice.entity.ShortCityGdp;
import com.adb.adblab.generalservice.mapper.CityGdpMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CityGdpService {
    @Autowired
    private CityGdpMapper cityGdpMapper;

    public JSONObject getAllCityCapitaGDP(){
        JSONObject output = new JSONObject();
        List<ShortCityGdp> list = new ArrayList<>();

        List<CityGdp> cityGdps = cityGdpMapper.selectAll();
        for (CityGdp gg:cityGdps) {
            ShortCityGdp cGdp = new ShortCityGdp();
            cGdp.setCityname(gg.getCityname());
            cGdp.setPopulationNum(gg.getPopulationNum());
            cGdp.setPerCapitaGdp(gg.getPerCapitaGdp());
            list.add(cGdp);
        }
        Collections.sort(list, new Comparator<ShortCityGdp>() {

            public int compare(ShortCityGdp o1, ShortCityGdp o2) {
                if (Integer.valueOf(o1.getPerCapitaGdp()) > Integer.valueOf(o2.getPerCapitaGdp())) {
                    return -1;
                }
                if (Integer.valueOf(o1.getPerCapitaGdp()) == Integer.valueOf(o2.getPerCapitaGdp())) {
                    return 0;
                }
                return 1;
            }
        });
        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",list);
        return output;
    }

}
