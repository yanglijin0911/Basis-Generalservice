package com.adb.adblab.generalservice.entity;

public class StreetShopGroupResult {

    private String pname;
    private String cityname;
    private String StreetShop;
    private Integer number;

    public String getPname() {
        return pname;
    }

    public String getCityname() {
        return cityname;
    }

    public String getStreetShop() {
        return StreetShop;
    }

    public Integer getNumber() {
        return number;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setStreetShop(String streetShop) {
        StreetShop = streetShop;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
