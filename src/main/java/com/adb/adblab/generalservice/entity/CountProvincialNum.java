package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`count_provincial_num`")
public class CountProvincialNum {
    @Column(name = "`pname`")
    private String pname;

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