package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class OnDemandPageGenre implements Serializable {

    private String canonical;
    private String link;
    private String name;
    private String uri;

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCanonical() {
        return canonical;
    }
    
    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }


}
