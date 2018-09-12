package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable{

    private String uri;
    private String name;
    private String description;
    private String link;
    private int duration;
    private String created_time;
    private String modified_time;
    private User user;
    private PrivacyBean privacy;
    private PicturesBean pictures;
    private String sort;
    private String layout;
    private String theme;
    private Object brand_color;
    private Object custom_logo;
    private boolean review_mode;
    private boolean hide_nav;
    private MetadataBean metadata;

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

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
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

    public String getCreated_time() {
        return created_time;
    }

    public String getModified_time() {
        return modified_time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }

    public PrivacyBean getPrivacy() {
        return privacy;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setBrand_color(Object brand_color) {
        this.brand_color = brand_color;
    }

    public void setCustom_logo(Object custom_logo) {
        this.custom_logo = custom_logo;
    }

    public void setReview_mode(boolean review_mode) {
        this.review_mode = review_mode;
    }

    public void setHide_nav(boolean hide_nav) {
        this.hide_nav = hide_nav;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public String getSort() {
        return sort;
    }

    public String getLayout() {
        return layout;
    }

    public String getTheme() {
        return theme;
    }

    public Object getBrand_color() {
        return brand_color;
    }

    public Object getCustom_logo() {
        return custom_logo;
    }

    public boolean getReview_mode() {
        return review_mode;
    }

    public boolean getHide_nav() {
        return hide_nav;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }


    public static class EmbedBean {

            private Object html;

            public Object getHtml() {
                return html;
            }

            public void setHtml(Object html) {
                this.html = html;
            }
        }

    public static class PrivacyBean {

        private String view;

        public void setView(String view) {
            this.view = view;
        }

        public String getView() {
            return view;
        }
    }

    public static class PicturesBean {

        private Object uri;
        private boolean active;
        private String type;
        private String resource_key;
        private List<SizesBean> sizes;

        public void setUri(Object uri) {
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

        public Object getUri() {
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
            private String link_with_play_button;

            public void setWidth(int width) {
                this.width = width;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public void setLink_with_play_button(String link_with_play_button) {
                this.link_with_play_button = link_with_play_button;
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

            public String getLink_with_play_button() {
                return link_with_play_button;
            }
        }
    }

    public static class MetadataBean{

        private ConnectionsBean connections;
        private InteractionsBean interactions;

        public void setConnections(ConnectionsBean connections) {
            this.connections = connections;
        }

        public void setInteractions(InteractionsBean interactions) {
            this.interactions = interactions;
        }

        public ConnectionsBean getConnections() {
            return connections;
        }

        public InteractionsBean getInteractions() {
            return interactions;
        }

        public static class ConnectionsBean {

            private VideosBean videos;

            public void setVideos(VideosBean videos) {
                this.videos = videos;
            }

            public VideosBean getVideos() {
                return videos;
            }

            public static class VideosBean {

                private String uri;
                private int total;
                private List<String> options;

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public String getUri() {
                    return uri;
                }

                public int getTotal() {
                    return total;
                }

                public List<String> getOptions() {
                    return options;
                }
            }
        }
    }
}
