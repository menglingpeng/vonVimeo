package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable{

    private int total;
    private int page;
    private int per_page;
    private PagingBean paging;
    private List<DataBean> data;

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

    public static class PagingBean {

        private Object next;
        private Object previous;
        private String first;
        private String last;

        public Object getNext() {
            return next;
        }

        public void setNext(Object next) {
            this.next = next;
        }

        public Object getPrevious() {
            return previous;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
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

    public static class Preferences{

        private VideosBean videos;

        public void setVideos(VideosBean videos) {
            this.videos = videos;
        }

        public VideosBean getVideos() {
            return videos;
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
}
