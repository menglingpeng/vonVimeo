package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;

public class Follower implements Serializable {

    private int id;

    private String created_at;

    private User follower;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
