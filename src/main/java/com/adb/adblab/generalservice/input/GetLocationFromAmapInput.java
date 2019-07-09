package com.adb.adblab.generalservice.input;


import javax.validation.constraints.NotBlank;

public class GetLocationFromAmapInput {

    @NotBlank
    private String city;

    @NotBlank
    private String keyword;

    public String getCity() {
        return city;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
