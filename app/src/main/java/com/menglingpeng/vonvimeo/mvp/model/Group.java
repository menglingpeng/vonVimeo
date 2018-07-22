package com.menglingpeng.vonvimeo.mvp.model;

public class Group {

    private String uri;
    private String name;
    private Object description;
    private String link;
    private String created_time;
    private String modified_time;
    private PrivacyBean privacy;
    private PicturesBean pictures;
    private User user;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public Object getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getModified_time() {
        return modified_time;
    }

    public PrivacyBean getPrivacy() {
        return privacy;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class PrivacyBean {

        private String view;
        private String join;
        private String videos;
        private String comment;
        private String invite;

        public void setView(String view) {
            this.view = view;
        }

        public void setJoin(String join) {
            this.join = join;
        }

        public void setVideos(String videos) {
            this.videos = videos;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setInvite(String invite) {
            this.invite = invite;
        }

        public String getView() {
            return view;
        }

        public String getJoin() {
            return join;
        }

        public String getVideos() {
            return videos;
        }

        public String getComment() {
            return comment;
        }

        public String getInvite() {
            return invite;
        }
    }

    public static class PicturesBean {

        private Object uri;
        private boolean active;
        private String type;

        public void setUri(Object uri) {
            this.uri = uri;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setType(String type) {
            this.type = type;
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
    }
}
