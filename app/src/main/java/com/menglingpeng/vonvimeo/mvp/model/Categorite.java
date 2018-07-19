package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;

public class Categorite implements Serializable {

    private int id;

    private String name;

    private String description;

    private int shots_count;

    private String created_at;

    private String updated_at;
    /**
     * uri : /categories/animation
     * link : https://vimeo.com/categories/animation
     * top_level : true
     */

    private String uri;
    private String link;
    private boolean top_level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShots_count() {
        return shots_count;
    }

    public void setShots_count(int shots_count) {
        this.shots_count = shots_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }


    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTop_level(boolean top_level) {
        this.top_level = top_level;
    }

    public String getUri() {
        return uri;
    }

    public String getLink() {
        return link;
    }

    public boolean getTop_level() {
        return top_level;
    }
}
