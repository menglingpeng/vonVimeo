package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class GenreOnDemandPage implements Serializable {

    private String uri;
    private String name;
    private String description;
    private String type;
    private String link;
    private Object domain_link;
    private String theme;
    private BackgroundBean background;
    private ColorsBean colors;
    private String created_time;
    private List<String> content_rating;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Object getDomain_link() {
        return domain_link;
    }

    public void setDomain_link(Object domain_link) {
        this.domain_link = domain_link;
    }

    public void setBackground(BackgroundBean background) {
        this.background = background;
    }

    public void setColors(ColorsBean colors) {
        this.colors = colors;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setContent_rating(List<String> content_rating) {
        this.content_rating = content_rating;
    }

    public BackgroundBean getBackground() {
        return background;
    }

    public ColorsBean getColors() {
        return colors;
    }

    public String getCreated_time() {
        return created_time;
    }

    public List<String> getContent_rating() {
        return content_rating;
    }


    public static class BackgroundBean {

        private String active;
        private String link;
        private String resource_key;
        private String type;
        private String uri;
        private List<SizesBean> sizes;

        public void setActive(String active) {
            this.active = active;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setSizes(List<SizesBean> sizes) {
            this.sizes = sizes;
        }

        public String getActive() {
            return active;
        }

        public String getLink() {
            return link;
        }

        public String getResource_key() {
            return resource_key;
        }

        public String getType() {
            return type;
        }

        public String getUri() {
            return uri;
        }

        public List<SizesBean> getSizes() {
            return sizes;
        }

        public static class SizesBean {
            private int height;
            private String link;
            private String link_with_play_button;
            private int width;

            public void setHeight(int height) {
                this.height = height;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public void setLink_with_play_button(String link_with_play_button) {
                this.link_with_play_button = link_with_play_button;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public String getLink() {
                return link;
            }

            public String getLink_with_play_button() {
                return link_with_play_button;
            }

            public int getWidth() {
                return width;
            }
        }
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


}
