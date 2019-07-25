package com.adb.adblab.generalservice.entity;

import javax.persistence.*;

@Table(name = "`city_gdp`")
public class CityGdp {
    @Column(name = "`cityname`")
    private String cityname;

    @Column(name = "`gdp_eighteen`")
    private String gdpEighteen;

    @Column(name = "`population_num`")
    private String populationNum;

    @Column(name = "`per_capita_gdp`")
    private String perCapitaGdp;

    @Column(name = "`increment`")
    private String increment;

    @Column(name = "`increment_percent`")
    private String incrementPercent;

    @Column(name = "`gdp_seventeen`")
    private String gdpSeventeen;

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
     * @return gdp_eighteen
     */
    public String getGdpEighteen() {
        return gdpEighteen;
    }

    /**
     * @param gdpEighteen
     */
    public void setGdpEighteen(String gdpEighteen) {
        this.gdpEighteen = gdpEighteen;
    }

    /**
     * @return population_num
     */
    public String getPopulationNum() {
        return populationNum;
    }

    /**
     * @param populationNum
     */
    public void setPopulationNum(String populationNum) {
        this.populationNum = populationNum;
    }

    /**
     * @return per_capita_gdp
     */
    public String getPerCapitaGdp() {
        return perCapitaGdp;
    }

    /**
     * @param perCapitaGdp
     */
    public void setPerCapitaGdp(String perCapitaGdp) {
        this.perCapitaGdp = perCapitaGdp;
    }

    /**
     * @return increment
     */
    public String getIncrement() {
        return increment;
    }

    /**
     * @param increment
     */
    public void setIncrement(String increment) {
        this.increment = increment;
    }

    /**
     * @return increment_percent
     */
    public String getIncrementPercent() {
        return incrementPercent;
    }

    /**
     * @param incrementPercent
     */
    public void setIncrementPercent(String incrementPercent) {
        this.incrementPercent = incrementPercent;
    }

    /**
     * @return gdp_seventeen
     */
    public String getGdpSeventeen() {
        return gdpSeventeen;
    }

    /**
     * @param gdpSeventeen
     */
    public void setGdpSeventeen(String gdpSeventeen) {
        this.gdpSeventeen = gdpSeventeen;
    }
}