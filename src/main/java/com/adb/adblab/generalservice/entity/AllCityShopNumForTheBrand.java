package com.adb.adblab.generalservice.entity;

public class AllCityShopNumForTheBrand {

    private String brandName;
    private String cityName;
    private Integer number;

    public String getBrandName() {
        return brandName;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
