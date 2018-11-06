package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;

public class Season implements Serializable{

    private String uri;
    private String name;
    private String type;
    private String description;
    private int position;
    private User user;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getPosition() {
        return position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
