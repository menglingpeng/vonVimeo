package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class OnDemand {

    private String uri;
    private String name;
    private String description;
    private String type;
    private String link;
    private Object domain_link;
    private String theme;
    private ColorsBean colors;
    private PublishBean publish;
    private PreorderBean preorder;
    private Object rating;
    private PicturesBean pictures;
    private List<String> content_rating;
    private List<GenresBean> genres;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDomain_link(Object domain_link) {
        this.domain_link = domain_link;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setColors(ColorsBean colors) {
        this.colors = colors;
    }

    public void setPublish(PublishBean publish) {
        this.publish = publish;
    }

    public void setPreorder(PreorderBean preorder) {
        this.preorder = preorder;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public void setContent_rating(List<String> content_rating) {
        this.content_rating = content_rating;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
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

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public Object getDomain_link() {
        return domain_link;
    }

    public String getTheme() {
        return theme;
    }

    public ColorsBean getColors() {
        return colors;
    }

    public PublishBean getPublish() {
        return publish;
    }

    public PreorderBean getPreorder() {
        return preorder;
    }

    public Object getRating() {
        return rating;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public List<String> getContent_rating() {
        return content_rating;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public static class ColorsBean {

        private String primary;
        private String secondary;

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public void setSecondary(String secondary) {
            this.secondary = secondary;
        }

        public String getPrimary() {
            return primary;
        }

        public String getSecondary() {
            return secondary;
        }
    }

    public static class PublishBean {

        private boolean active;
        private String time;

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean getActive() {
            return active;
        }

        public String getTime() {
            return time;
        }
    }

    public static class PreorderBean {

        private boolean active;
        private String time;
        private Object cancel_time;
        private String publish_time;

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setCancel_time(Object cancel_time) {
            this.cancel_time = cancel_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public boolean getActive() {
            return active;
        }

        public String getTime() {
            return time;
        }

        public Object getCancel_time() {
            return cancel_time;
        }

        public String getPublish_time() {
            return publish_time;
        }
    }
    
}
