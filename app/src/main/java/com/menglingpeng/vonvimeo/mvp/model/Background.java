package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Background implements Serializable {

    private String uri;
    private boolean active;
    private String type;
    private String resource_key;
    private List<SizesBean> sizes;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public void setSizes(List<SizesBean> sizes) {
        this.sizes = sizes;
    }

    public String getUri() {
        return uri;
    }

    public boolean getActive() {
        return active;
    }

    public String getType() {
        return type;
    }

    public String getResource_key() {
        return resource_key;
    }

    public List<SizesBean> getSizes() {
        return sizes;
    }

    public static class SizesBean {

        private int width;
        private int height;
        private String link;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getLink() {
            return link;
        }
    }
}
