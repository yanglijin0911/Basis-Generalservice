package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`count_vehicle_num`")
public class CountVehicleNum {
    @Column(name = "`vehicle_name`")
    private String vehicleName;

    @Column(name = "`number`")
    private Integer number;

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