package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class FeedVideo implements Serializable {

    private Object uri;
    private ClipBean clip;

    public void setUri(Object uri) {
        this.uri = uri;
    }

    public void setClip(ClipBean clip) {
        this.clip = clip;
    }

    public Object getUri() {
        return uri;
    }

    public ClipBean getClip() {
        return clip;
    }

    public static class ClipBean {

        private String uri;
        private String name;
        private String description;
        private String link;
        private int duration;
        private int width;
        private Object language;
        private int height;

        private String created_time;
        private String modified_time;
        private String release_time;
        private List<String> content_rating;

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

        public void setContent_rating(List<String> content_rating) {
            this.content_rating = content_rating;
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

        public List<String> getContent_rating() {
            return content_rating;
        }

    }
}
