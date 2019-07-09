package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`vehicle_name`")
public class VehicleName {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`vehicle_name`")
    private String vehicleName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
}