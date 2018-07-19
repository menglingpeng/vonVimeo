package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String uri;
    private String name;
    private String link;
    private Object location;
    private Object bio;
    private String created_time;
    private PicturesBean pictures;
    private MetadataBean metadata;
    private PreferencesBean preferences;
    private String resource_key;
    private String account;
    private List<?> websites;
    private List<String> content_filter;
    /**
     * videos : {"privacy":{"view":"anybody","comments":"anybody","embed":"public","download":true,"add":true}}
     */

    private VideosBean videos;

    public void setVideos(VideosBean videos) {
        this.videos = videos;
    }

    public VideosBean getVideos() {
        return videos;
    }

    public static class PicturesBean {

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
        }
    }


    public static class VideosBean {

        private PrivacyBean privacy;

        public void setPrivacy(PrivacyBean privacy) {
            this.privacy = privacy;
        }

        public PrivacyBean getPrivacy() {
            return privacy;
        }

        public static class PrivacyBean {

            private String view;
            private String comments;
            private String embed;
            private boolean download;
            private boolean add;

            public void setView(String view) {
                this.view = view;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public void setEmbed(String embed) {
                this.embed = embed;
            }

            public void setDownload(boolean download) {
                this.download = download;
            }

            public void setAdd(boolean add) {
                this.add = add;
            }

            public String getView() {
                return view;
            }

            public String getComments() {
                return comments;
            }

            public String getEmbed() {
                return embed;
            }

            public boolean getDownload() {
                return download;
            }

            public boolean getAdd() {
                return add;
            }
        }
    }
}
