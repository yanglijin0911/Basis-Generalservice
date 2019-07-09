package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`top_brand_by_city`")
public class TopBrandByCity {
    @Column(name = "`cityname`")
    private String cityname;

    @Column(name = "`brand_name`")
    private String brandName;

    @Column(name = "`shop_num`")
    private Integer shopNum;

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