package com.adb.adblab.generalservice.input;

import javax.validation.constraints.NotBlank;

public class QueryVehicleInfoInput {
    @NotBlank
    private String cityname;

    @NotBlank
    private String brand;

    @NotBlank
    private String keyword;

    public String getCityname() {
        return cityname;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

