package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`count_provincial_cityname`")
public class CountProvincialCityname {
    @Column(name = "`pname`")
    private String pname;

    @Column(name = "`cityname`")
    private String cityname;

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