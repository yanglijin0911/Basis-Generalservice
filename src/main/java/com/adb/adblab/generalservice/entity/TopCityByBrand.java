package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`top_city_by_brand`")
public class TopCityByBrand {
    @Column(name = "`brand_name`")
    private String brandName;

    @Column(name = "`cityname`")
    private String cityname;

    @Column(name = "`shop_num`")
    private Integer shopNum;

    /**
     * @return brand_name
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return cityname
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * @param cityname
     */
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    /**
     * @return shop_num
     */
    public Integer getShopNum() {
        return shopNum;
    }

    /**
     * @param shopNum
     */
    public void setShopNum(Integer shopNum) {
        this.shopNum = shopNum;
    }
}