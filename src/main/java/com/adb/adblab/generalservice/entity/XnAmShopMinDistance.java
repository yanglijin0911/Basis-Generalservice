package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`xn_am_shop_min_distance`")
public class XnAmShopMinDistance {
    @Column(name = "`pname`")
    private String pname;

    @Column(name = "`cityname`")
    private String cityname;

    @Column(name = "`xn_address`")
    private String xnAddress;

    @Column(name = "`am_address`")
    private String amAddress;

    @Column(name = "`xn_location`")
    private String xnLocation;

    @Column(name = "`am_location`")
    private String amLocation;

    @Column(name = "`min_distance`")
    private Double minDistance;

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
     * @return xn_address
     */
    public String getXnAddress() {
        return xnAddress;
    }

    /**
     * @param xnAddress
     */
    public void setXnAddress(String xnAddress) {
        this.xnAddress = xnAddress;
    }

    /**
     * @return am_address
     */
    public String getAmAddress() {
        return amAddress;
    }

    /**
     * @param amAddress
     */
    public void setAmAddress(String amAddress) {
        this.amAddress = amAddress;
    }

    /**
     * @return xn_location
     */
    public String getXnLocation() {
        return xnLocation;
    }

    /**
     * @param xnLocation
     */
    public void setXnLocation(String xnLocation) {
        this.xnLocation = xnLocation;
    }

    /**
     * @return am_location
     */
    public String getAmLocation() {
        return amLocation;
    }

    /**
     * @param amLocation
     */
    public void setAmLocation(String amLocation) {
        this.amLocation = amLocation;
    }

    /**
     * @return min_distance
     */
    public Double getMinDistance() {
        return minDistance;
    }

    /**
     * @param minDistance
     */
    public void setMinDistance(Double minDistance) {
        this.minDistance = minDistance;
    }
}