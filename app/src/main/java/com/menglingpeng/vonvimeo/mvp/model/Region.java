package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;

public class Region implements Serializable {


    private String uri;
    private String country_code;
    private String country_name;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getUri() {
        return uri;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getCountry_name() {
        return country_name;
    }
}
