package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;

public class OnDemandPageVideo implements Serializable{

    private String uri;
    private String name;
    private String description;
    private String link;
    private int duration;
    private int width;
    private String language;
    private int height;
    private EmbedBean embed;

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

    public void setLanguage(String language) {
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

    public String getLanguage() {
        return language;
    }

    public int getHeight() {
        return height;
    }

    public void setEmbed(EmbedBean embed) {
        this.embed = embed;
    }

    public EmbedBean getEmbed() {
        return embed;
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
}
