package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class LikeVideo implements Serializable {

    private String uri;
    private String name;
    private String description;
    private String link;
    private int duration;
    private int width;
    private Object language;
    private int height;
    private EmbedBean embed;
    private String created_time;
    private String modified_time;
    private String release_time;
    private List<String> content_rating;
    private Object license;
    private PrivacyBean privacy;
    private PicturesBean pictures;

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

    public void setEmbed(EmbedBean embed) {
        this.embed = embed;
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

    public EmbedBean getEmbed() {
        return embed;
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

    public void setLicense(Object license) {
        this.license = license;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }

    public Object getLicense() {
        return license;
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


    public static class EmbedBean {

        private String html;
        private BadgesBean badges;

        public void setHtml(String html) {
            this.html = html;
        }

        public void setBadges(BadgesBean badges) {
            this.badges = badges;
        }

        public String getHtml() {
            return html;
        }

        public BadgesBean getBadges() {
            return badges;
        }

        public static class BadgesBean {

            private boolean hdr;
            private LiveBean live;
            private StaffPickBean staff_pick;
            private boolean vod;
            private boolean weekend_challenge;

            public void setHdr(boolean hdr) {
                this.hdr = hdr;
            }

            public void setLive(LiveBean live) {
                this.live = live;
            }

            public void setStaff_pick(StaffPickBean staff_pick) {
                this.staff_pick = staff_pick;
            }

            public void setVod(boolean vod) {
                this.vod = vod;
            }

            public void setWeekend_challenge(boolean weekend_challenge) {
                this.weekend_challenge = weekend_challenge;
            }

            public boolean getHdr() {
                return hdr;
            }

            public LiveBean getLive() {
                return live;
            }

            public StaffPickBean getStaff_pick() {
                return staff_pick;
            }

            public boolean getVod() {
                return vod;
            }

            public boolean getWeekend_challenge() {
                return weekend_challenge;
            }

            public static class LiveBean {

                private boolean streaming;
                private boolean archived;

                public void setStreaming(boolean streaming) {
                    this.streaming = streaming;
                }

                public void setArchived(boolean archived) {
                    this.archived = archived;
                }

                public boolean getStreaming() {
                    return streaming;
                }

                public boolean getArchived() {
                    return archived;
                }
            }

            public static class StaffPickBean {

                private boolean normal;
                private boolean best_of_the_month;
                private boolean best_of_the_year;
                private boolean premiere;

                public void setNormal(boolean normal) {
                    this.normal = normal;
                }

                public void setBest_of_the_month(boolean best_of_the_month) {
                    this.best_of_the_month = best_of_the_month;
                }

                public void setBest_of_the_year(boolean best_of_the_year) {
                    this.best_of_the_year = best_of_the_year;
                }

                public void setPremiere(boolean premiere) {
                    this.premiere = premiere;
                }

                public boolean getNormal() {
                    return normal;
                }

                public boolean getBest_of_the_month() {
                    return best_of_the_month;
                }

                public boolean getBest_of_the_year() {
                    return best_of_the_year;
                }

                public boolean getPremiere() {
                    return premiere;
                }
            }
        }
    }

    public static class PrivacyBean {

        private String view;
        private String embed;
        private boolean download;
        private boolean add;
        private String comments;

        public void setView(String view) {
            this.view = view;
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

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getView() {
            return view;
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

        public String getComments() {
            return comments;
        }
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
}
