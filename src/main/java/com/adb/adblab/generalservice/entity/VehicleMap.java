package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`vehicle_map`")
public class VehicleMap {
    @Id
    @Column(name = "`id`")
    private String id;

    @Column(name = "`pcode`")
    private String pcode;

    @Column(name = "`biz_ext`")
    private String bizExt;

    @Column(name = "`type`")
    private String type;

    @Column(name = "`gridcode`")
    private String gridcode;

    @Column(name = "`typecode`")
    private String typecode;

    @Column(name = "`citycode`")
    private String citycode;

    @Column(name = "`adname`")
    private String adname;

    @Column(name = "`tel`")
    private String tel;

    @Column(name = "`event`")
    private String event;

    @Column(name = "`entr_location`")
    private String entrLocation;

    @Column(name = "`timestamp`")
    private String timestamp;

    @Column(name = "`address`")
    private String address;

    @Column(name = "`adcode`")
    private String adcode;

    @Column(name = "`pname`")
    private String pname;

    @Column(name = "`cityname`")
    private String cityname;

    @Column(name = "`match`")
    private String match;

    @Column(name = "`business_area`")
    private String businessArea;

    @Column(name = "`indoor_data`")
    private String indoorData;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`location`")
    private String location;

    @Column(name = "`navi_poiid`")
    private String naviPoiid;

    @Column(name = "`brand_name`")
    private String brandName;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return pcode
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * @param pcode
     */
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    /**
     * @return biz_ext
     */
    public String getBizExt() {
        return bizExt;
    }

    /**
     * @param bizExt
     */
    public void setBizExt(String bizExt) {
        this.bizExt = bizExt;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return gridcode
     */
    public String getGridcode() {
        return gridcode;
    }

    /**
     * @param gridcode
     */
    public void setGridcode(String gridcode) {
        this.gridcode = gridcode;
    }

    /**
     * @return typecode
     */
    public String getTypecode() {
        return typecode;
    }

    /**
     * @param typecode
     */
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    /**
     * @return citycode
     */
    public String getCitycode() {
        return citycode;
    }

    /**
     * @param citycode
     */
    public void setCitycode(String citycode) {
        this.citycode = citycode;
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
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return event
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return entr_location
     */
    public String getEntrLocation() {
        return entrLocation;
    }

    /**
     * @param entrLocation
     */
    public void setEntrLocation(String entrLocation) {
        this.entrLocation = entrLocation;
    }

    /**
     * @return timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
     * @return adcode
     */
    public String getAdcode() {
        return adcode;
    }

    /**
     * @param adcode
     */
    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

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
     * @return match
     */
    public String getMatch() {
        return match;
    }

    /**
     * @param match
     */
    public void setMatch(String match) {
        this.match = match;
    }

    /**
     * @return business_area
     */
    public String getBusinessArea() {
        return businessArea;
    }

    /**
     * @param businessArea
     */
    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    /**
     * @return indoor_data
     */
    public String getIndoorData() {
        return indoorData;
    }

    /**
     * @param indoorData
     */
    public void setIndoorData(String indoorData) {
        this.indoorData = indoorData;
    }

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
     * @return navi_poiid
     */
    public String getNaviPoiid() {
        return naviPoiid;
    }

    /**
     * @param naviPoiid
     */
    public void setNaviPoiid(String naviPoiid) {
        this.naviPoiid = naviPoiid;
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
}