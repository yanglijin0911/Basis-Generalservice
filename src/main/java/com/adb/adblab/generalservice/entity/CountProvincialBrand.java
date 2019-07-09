package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`count_provincial_brand`")
public class CountProvincialBrand {
    @Column(name = "`pname`")
    private String pname;

    @Column(name = "`vehicle_name`")
    private String vehicleName;

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
     * @return vehicle_name
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * @param vehicleName
     */
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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