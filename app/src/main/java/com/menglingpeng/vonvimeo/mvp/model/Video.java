package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Video implements Serializable{

    private int total;
    private int page;
    private int per_page;
    private PagingBean paging;
    private List<DataBean> data;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public PagingBean getPaging() {
        return paging;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class PagingBean {

        private Object next;
        private Object previous;
        private String first;
        private String last;

        public void setNext(Object next) {
            this.next = next;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public Object getNext() {
            return next;
        }

        public Object getPrevious() {
            return previous;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }

    public static class DataBean {

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
        private PicturesBean pictures;
        private StatsBean stats;
        private String status;
        private String resource_key;
        private TranscodeBean transcode;
        private List<String> content_rating;
        private List<TagsBean> tags;

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

        public void setPictures(PicturesBean pictures) {
            this.pictures = pictures;
        }

        public void setStats(StatsBean stats) {
            this.stats = stats;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public void setTranscode(TranscodeBean transcode) {
            this.transcode = transcode;
        }

        public void setContent_rating(List<String> content_rating) {
            this.content_rating = content_rating;
        }

        public void setTags(List<TagsBean> tags) {
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

        public PicturesBean getPictures() {
            return pictures;
        }

        public StatsBean getStats() {
            return stats;
        }

        public String getStatus() {
            return status;
        }

        public String getResource_key() {
            return resource_key;
        }

        public TranscodeBean getTranscode() {
            return transcode;
        }

        public List<String> getContent_rating() {
            return content_rating;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public static class PicturesBean {

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

        public static class StatsBean {


            private int plays;

            public void setPlays(int plays) {
                this.plays = plays;
            }

            public int getPlays() {
                return plays;
            }
        }

        public static class TranscodeBean {

            private String status;

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatus() {
                return status;
            }
        }

        public static class TagsBean {

            private String uri;
            private String name;
            private String tag;
            private String canonical;
            private String resource_key;

            public void setUri(String uri) {
                this.uri = uri;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public void setCanonical(String canonical) {
                this.canonical = canonical;
            }

            public void setResource_key(String resource_key) {
                this.resource_key = resource_key;
            }

            public String getUri() {
                return uri;
            }

            public String getName() {
                return name;
            }

            public String getTag() {
                return tag;
            }

            public String getCanonical() {
                return canonical;
            }

            public String getResource_key() {
                return resource_key;
            }
        }
    }
}
