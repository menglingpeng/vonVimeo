package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class FeedVideo implements Serializable {

    private Object uri;
    private ClipBean clip;

    public void setUri(Object uri) {
        this.uri = uri;
    }

    public void setClip(ClipBean clip) {
        this.clip = clip;
    }

    public Object getUri() {
        return uri;
    }

    public ClipBean getClip() {
        return clip;
    }


    public static class ClipBean {

        private String uri;
        private String name;
        private String description;
        private String link;
        private int duration;
        private int width;
        private Object language;
        private int height;
        private String created_time;
        private String modified_time;
        private String release_time;
        private List<String> content_rating;
        private Object license;
        private PrivacyBean privacy;
        private PicturesBean pictures;
        private Category category;
        private List<TagsBean> tags;
        private User user;
        private MetadataBean metadata;
        private StatsBean stats;
        private AppBean app;
        private String status;
        private String resource_key;
        private UploadBean upload;
        private TranscodeBean transcode;
        private String type;
        private String time;

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

        public void setContent_rating(List<String> content_rating) {
            this.content_rating = content_rating;
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

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
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

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setStats(StatsBean stats) {
            this.stats = stats;
        }

        public StatsBean getStats() {
            return stats;
        }

        public void setApp(AppBean app) {
            this.app = app;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public void setUpload(UploadBean upload) {
            this.upload = upload;
        }

        public void setTranscode(TranscodeBean transcode) {
            this.transcode = transcode;
        }

        public AppBean getApp() {
            return app;
        }

        public String getStatus() {
            return status;
        }

        public String getResource_key() {
            return resource_key;
        }

        public UploadBean getUpload() {
            return upload;
        }

        public TranscodeBean getTranscode() {
            return transcode;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public String getTime() {
            return time;
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

            public String getUri() {
                return uri;
            }

            public boolean getActive() {
                return active;
            }

            public String getType() {
                return type;
            }

            public void setResource_key(String resource_key) {
                this.resource_key = resource_key;
            }

            public void setSizes(List<SizesBean> sizes) {
                this.sizes = sizes;
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

        public static class TagsBean {

            private String uri;
            private String name;
            private String tag;
            private String canonical;
            private MetadataBean metadata;
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

            public void setMetadata(MetadataBean metadata) {
                this.metadata = metadata;
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

            public MetadataBean getMetadata() {
                return metadata;
            }

            public String getResource_key() {
                return resource_key;
            }

            public static class MetadataBean {

                private ConnectionsBean connections;

                public void setConnections(ConnectionsBean connections) {
                    this.connections = connections;
                }

                public ConnectionsBean getConnections() {
                    return connections;
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

        public static class MetadataBean {

            private ConnectionsBean connections;
            private InteractionsBean interactions;

            public void setConnections(ConnectionsBean connections) {
                this.connections = connections;
            }

            public ConnectionsBean getConnections() {
                return connections;
            }

            public void setInteractions(InteractionsBean interactions) {
                this.interactions = interactions;
            }

            public InteractionsBean getInteractions() {
                return interactions;
            }


            public static class ConnectionsBean {

                private CommentsBean comments;
                private LikesBean likes;
                private Object related;
                private PicturesBean pictures;
                private TexttracksBean texttracks;
                private RecommendationsBean recommendations;
                private VersionsBean versions;
                private CreditsBean credits;

                public void setComments(CommentsBean comments) {
                    this.comments = comments;
                }

                public void setLikes(LikesBean likes) {
                    this.likes = likes;
                }

                public void setRelated(Object related) {
                    this.related = related;
                }

                public CommentsBean getComments() {
                    return comments;
                }

                public LikesBean getLikes() {
                    return likes;
                }

                public Object getRelated() {
                    return related;
                }

                public void setPictures(PicturesBean pictures) {
                    this.pictures = pictures;
                }

                public void setTexttracks(TexttracksBean texttracks) {
                    this.texttracks = texttracks;
                }

                public void setRecommendations(RecommendationsBean recommendations) {
                    this.recommendations = recommendations;
                }

                public PicturesBean getPictures() {
                    return pictures;
                }

                public TexttracksBean getTexttracks() {
                    return texttracks;
                }

                public RecommendationsBean getRecommendations() {
                    return recommendations;
                }

                public void setVersions(VersionsBean versions) {
                    this.versions = versions;
                }

                public VersionsBean getVersions() {
                    return versions;
                }

                public void setCredits(CreditsBean credits) {
                    this.credits = credits;
                }

                public CreditsBean getCredits() {
                    return credits;
                }


                public static class CommentsBean {

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

                public static class LikesBean {

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

                public static class PicturesBean {

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

                public static class TexttracksBean {

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

                public static class RecommendationsBean {

                    private String uri;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }

                public static class VersionsBean {

                    private String uri;
                    private int total;
                    private String current_uri;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setTotal(int total) {
                        this.total = total;
                    }

                    public void setCurrent_uri(String current_uri) {
                        this.current_uri = current_uri;
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

                    public String getCurrent_uri() {
                        return current_uri;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }

                public static class CreditsBean {

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

            public static class InteractionsBean {

                private WatchlaterBean watchlater;
                private LikeBean like;
                private ReportBean report;

                public void setWatchlater(WatchlaterBean watchlater) {
                    this.watchlater = watchlater;
                }

                public WatchlaterBean getWatchlater() {
                    return watchlater;
                }

                public void setLike(LikeBean like) {
                    this.like = like;
                }

                public LikeBean getLike() {
                    return like;
                }

                public void setReport(ReportBean report) {
                    this.report = report;
                }

                public ReportBean getReport() {
                    return report;
                }


                public static class WatchlaterBean {

                    private String uri;
                    private boolean added;
                    private Object added_time;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setAdded(boolean added) {
                        this.added = added;
                    }

                    public void setAdded_time(Object added_time) {
                        this.added_time = added_time;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public boolean getAdded() {
                        return added;
                    }

                    public Object getAdded_time() {
                        return added_time;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }

                public static class LikeBean {

                    private String uri;
                    private boolean added;
                    private Object added_time;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setAdded(boolean added) {
                        this.added = added;
                    }

                    public void setAdded_time(Object added_time) {
                        this.added_time = added_time;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public boolean getAdded() {
                        return added;
                    }

                    public Object getAdded_time() {
                        return added_time;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }

                public static class ReportBean {

                    private String uri;
                    private List<String> options;
                    private List<String> reason;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public void setReason(List<String> reason) {
                        this.reason = reason;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public List<String> getOptions() {
                        return options;
                    }

                    public List<String> getReason() {
                        return reason;
                    }
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

        public static class AppBean {

            private String name;
            private String uri;

            public void setName(String name) {
                this.name = name;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public String getName() {
                return name;
            }

            public String getUri() {
                return uri;
            }
        }

        public static class UploadBean {

            private String status;
            private Object link;
            private Object upload_link;
            private Object complete_uri;
            private Object form;
            private Object approach;
            private Object size;
            private Object redirect_url;

            public void setStatus(String status) {
                this.status = status;
            }

            public void setLink(Object link) {
                this.link = link;
            }

            public void setUpload_link(Object upload_link) {
                this.upload_link = upload_link;
            }

            public void setComplete_uri(Object complete_uri) {
                this.complete_uri = complete_uri;
            }

            public void setForm(Object form) {
                this.form = form;
            }

            public void setApproach(Object approach) {
                this.approach = approach;
            }

            public void setSize(Object size) {
                this.size = size;
            }

            public void setRedirect_url(Object redirect_url) {
                this.redirect_url = redirect_url;
            }

            public String getStatus() {
                return status;
            }

            public Object getLink() {
                return link;
            }

            public Object getUpload_link() {
                return upload_link;
            }

            public Object getComplete_uri() {
                return complete_uri;
            }

            public Object getForm() {
                return form;
            }

            public Object getApproach() {
                return approach;
            }

            public Object getSize() {
                return size;
            }

            public Object getRedirect_url() {
                return redirect_url;
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
    }
}
