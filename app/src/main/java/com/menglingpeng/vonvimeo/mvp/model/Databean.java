package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class Databean {

    private String uri;
    private String name;
    private String description;
    private String link;
    private User user
    private int duration;
    private int width;
    private Object language;
    private int height;
    private String created_time;
    private String modified_time;
    private String release_time;
    private Object license;
    private Video.DataBean.PicturesBean pictures;
    private Video.DataBean.StatsBean stats;
    private String status;
    private String resource_key;
    private Video.DataBean.TranscodeBean transcode;
    private List<String> content_rating;
    private List<Video.DataBean.TagsBean> tags;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLanguage(Object language) {
        this.language = language;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    public void setPictures(Video.DataBean.PicturesBean pictures) {
        this.pictures = pictures;
    }

    public void setStats(Video.DataBean.StatsBean stats) {
        this.stats = stats;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public void setTranscode(Video.DataBean.TranscodeBean transcode) {
        this.transcode = transcode;
    }

    public void setContent_rating(List<String> content_rating) {
        this.content_rating = content_rating;
    }

    public void setTags(List<Video.DataBean.TagsBean> tags) {
        this.tags = tags;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public int getDuration() {
        return duration;
    }

    public int getWidth() {
        return width;
    }

    public Object getLanguage() {
        return language;
    }

    public int getHeight() {
        return height;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getModified_time() {
        return modified_time;
    }

    public String getRelease_time() {
        return release_time;
    }

    public Object getLicense() {
        return license;
    }

    public Video.DataBean.PicturesBean getPictures() {
        return pictures;
    }

    public Video.DataBean.StatsBean getStats() {
        return stats;
    }

    public String getStatus() {
        return status;
    }

    public String getResource_key() {
        return resource_key;
    }

    public Video.DataBean.TranscodeBean getTranscode() {
        return transcode;
    }

    public List<String> getContent_rating() {
        return content_rating;
    }

    public List<Video.DataBean.TagsBean> getTags() {
        return tags;
    }

    public User getUser() {
        return user;
}
