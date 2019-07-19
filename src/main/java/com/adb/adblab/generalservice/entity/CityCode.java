package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`city_code`")
public class CityCode {
    @Column(name = "`pname`")
    private String pname;

    @Column(name = "`cityname`")
    private String cityname;

    @Column(name = "`city_code`")
    private String cityCode;

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
     * @return city_code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}