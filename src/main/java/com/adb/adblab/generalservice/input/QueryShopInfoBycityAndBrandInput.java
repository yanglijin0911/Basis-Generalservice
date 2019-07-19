package com.adb.adblab.generalservice.input;

import java.util.List;

public class QueryShopInfoBycityAndBrandInput {

    private String cityName;
    private List<String> brandNames;

    public String getCityName() {
        return cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(List<String> brandNames) {
        this.brandNames = brandNames;
    }
}
