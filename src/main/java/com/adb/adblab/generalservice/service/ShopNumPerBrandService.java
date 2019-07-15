package com.adb.adblab.generalservice.service;

import com.adb.adblab.generalservice.entity.AllCityShopNumForTheBrand;
import com.adb.adblab.generalservice.entity.CountProvincialBrand;
import com.adb.adblab.generalservice.entity.VehicleName;
import com.adb.adblab.generalservice.mapper.CountProvincialBrandExtMapper;
import com.adb.adblab.generalservice.mapper.StreetShopExtMapper;
import com.adb.adblab.generalservice.mapper.VehicleNameExtMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ShopNumPerBrandService {
    @Autowired
    private CountProvincialBrandExtMapper countProvincialBrandExtMapper;
    @Autowired
    private VehicleNameExtMapper vehicleNameExtMapper;
    @Autowired
    private StreetShopExtMapper streetShopExtMapper;

    public JSONObject queryAllBrandShopNumByProvinvial(String pname){
        JSONObject output = new JSONObject();
        JSONObject data = new JSONObject();
        List<VehicleName> vehicleNames = vehicleNameExtMapper.selectAll();

        if(StringUtils.isBlank(pname)){
            for (VehicleName vn:vehicleNames) {
                Example example = new Example(CountProvincialBrand.class);
                example.createCriteria().andEqualTo("vehicleName",vn.getVehicleName());
                List<CountProvincialBrand> list = countProvincialBrandExtMapper.selectByExample(example);
                Collections.sort(list, new Comparator<CountProvincialBrand>() {

                    public int compare(CountProvincialBrand o1, CountProvincialBrand o2) {
                        if (o1.getNumber() > o2.getNumber()) {
                            return -1;
                        }
                        if (o1.getNumber() > o2.getNumber()) {
                            return 0;
                        }
                        return 1;
                    }
                });
                data.put(vn.getVehicleName(),JSONArray.parseArray(JSON.toJSONString(list)));
            }
        }else{
            for (VehicleName vn:vehicleNames) {
                List<AllCityShopNumForTheBrand> list =null;
                if(pname.contains("上海") || pname.contains("北京") || pname.contains("天津") || pname.contains("重庆")){
                    list = streetShopExtMapper.selectByPnameAndBrandName2(pname, vn.getVehicleName());
                }else {
                    list = streetShopExtMapper.selectByPnameAndBrandName(pname, vn.getVehicleName());
                }

//                if(CollectionUtils.isEmpty(list)){
//                    String msg = String.format("根据参数未查询到结果,pname=%s",pname);
//                    throw new IllegalArgumentException(msg);
//                }

                Collections.sort(list, new Comparator<AllCityShopNumForTheBrand>() {

                    public int compare(AllCityShopNumForTheBrand oo1, AllCityShopNumForTheBrand oo2) {
                        if (oo1.getNumber() > oo2.getNumber()) {
                            return -1;
                        }
                        if (oo1.getNumber() > oo2.getNumber()) {
                            return 0;
                        }
                        return 1;
                    }
                });
                data.put(vn.getVehicleName(),JSONArray.parseArray(JSON.toJSONString(list)));
            }
        }
        output.put("statusCode",200);
        output.put("msg","成功");
        output.put("data",data);
        System.out.println(JSON.toJSON(data));
        return output;
    }

}
