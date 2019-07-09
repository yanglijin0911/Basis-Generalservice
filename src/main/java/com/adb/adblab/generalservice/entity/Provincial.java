package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`provincial`")
public class Provincial {
    @Id
    @Column(name = "`pid`")
    private Integer pid;

    @Column(name = "`Provincial`")
    private String provincial;

    /**
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @return Provincial
     */
    public String getProvincial() {
        return provincial;
    }

    /**
     * @param provincial
     */
    public void setProvincial(String provincial) {
        this.provincial = provincial;
    }
}