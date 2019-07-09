package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`count_street_shop`")
public class CountStreetShop {
    @Column(name = "`pname`")
    private String pname;

    @Column(name = "`cityname`")
    private String cityname;

    @Column(name = "`adname`")
    private String adname;

    @Column(name = "`street_address`")
    private String streetAddress;

    @Column(name = "`number`")
    private Integer number;

    /**
     * @return pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname
     */
    public void setPname(String pname) {
        this.pname = pname;
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
     * @return adname
     */
    public String getAdname() {
        return adname;
    }

    /**
     * @param adname
     */
    public void setAdname(String adname) {
        this.adname = adname;
    }

    /**
     * @return street_address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}