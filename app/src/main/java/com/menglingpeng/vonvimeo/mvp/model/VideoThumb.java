package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class VideoThumb implements Serializable{

    private String uri;
    private boolean active;
    private String type;
    private String resource_key;
    private List<SizesBean> sizes;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResource_key() {
        return resource_key;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public List<SizesBean> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizesBean> sizes) {
        this.sizes = sizes;
    }

    public static class SizesBean {

        private int width;
        private int height;
        private String link;
        private String link_with_play_button;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink_with_play_button() {
            return link_with_play_button;
        }

        public void setLink_with_play_button(String link_with_play_button) {
            this.link_with_play_button = link_with_play_button;
        }
    }
}
