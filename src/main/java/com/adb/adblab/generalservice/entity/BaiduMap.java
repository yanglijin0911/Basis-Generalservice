package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`baidu_map`")
public class BaiduMap {
    @Column(name = "`name`")
    private String name;

    @Column(name = "`location`")
    private String location;

    @Column(name = "`address`")
    private String address;

    @Column(name = "`province`")
    private String province;

    @Column(name = "`city`")
    private String city;

    @Column(name = "`area`")
    private String area;

    @Column(name = "`telephone`")
    private String telephone;

    @Column(name = "`detail`")
    private Integer detail;

    @Column(name = "`uid`")
    private String uid;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return detail
     */
    public Integer getDetail() {
        return detail;
    }

    /**
     * @param detail
     */
    public void setDetail(Integer detail) {
        this.detail = detail;
    }

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
}