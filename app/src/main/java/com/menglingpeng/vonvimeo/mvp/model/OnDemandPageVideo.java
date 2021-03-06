package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class OnDemandPageVideo implements Serializable{

    private String uri;
    private String name;
    private String description;
    private String link;
    private int duration;
    private int width;
    private String language;
    private int height;
    private String created_time;
    private String modified_time;
    private String release_time;
    private Object license;
    private PrivacyBean privacy;
    private List<String> content_rating;
    private TranscodeBean transcode;
    private AppBean app;
    private PicturesBean pictures;
    private StatsBean stats;
    private MetadataBean metadata;
    private List<ParentProjectBean> parent_project;
    private SubcategoriesBean subcategories;
    private UploadBean upload;
    private List<TagsBean> tags;
    private List<ParentFolderBean> parent_folder;
    private SpatialBean spatial;
    private List<EmbedBean> embed;

    public UploadBean getUpload() {
        return upload;
    }

    public void setUpload(UploadBean upload) {
        this.upload = upload;
    }

    public SubcategoriesBean getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(SubcategoriesBean subcategories) {
        this.subcategories = subcategories;
    }

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public TranscodeBean getTranscode() {
        return transcode;
    }

    public void setTranscode(TranscodeBean transcode) {
        this.transcode = transcode;
    }

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

    public List<EmbedBean> getEmbed() {
        return embed;
    }

    public void setEmbed(List<EmbedBean> embed) {
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

    public void setLicense(Object license) {
        this.license = license;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }

    public void setContent_rating(List<String> content_rating) {
        this.content_rating = content_rating;
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

    public PrivacyBean getPrivacy() {
        return privacy;
    }

    public List<String> getContent_rating() {
        return content_rating;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public StatsBean getStats() {
        return stats;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public List<ParentProjectBean> getParent_project() {
        return parent_project;
    }

    public void setParent_project(List<ParentProjectBean> parent_project) {
        this.parent_project = parent_project;
    }

    public List<ParentFolderBean> getParent_folder() {
        return parent_folder;
    }

    public void setParent_folder(List<ParentFolderBean> parent_folder) {
        this.parent_folder = parent_folder;
    }

    public SpatialBean getSpatial() {
        return spatial;
    }

    public void setSpatial(SpatialBean spatial) {
        this.spatial = spatial;
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
        private List<SizesBean> sizes;
        private String resource_key;

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setType(String type) {
            this.type = type;
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

        public List<SizesBean> getSizes() {
            return sizes;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public String getResource_key() {
            return resource_key;
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

        private Object plays;

        public void setPlays(Object plays) {
            this.plays = plays;
        }

        public Object getPlays() {
            return plays;
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

        public InteractionsBean getInteractions() {
            return interactions;
        }

        public void setInteractions(InteractionsBean interactions) {
            this.interactions = interactions;
        }

        public static class ConnectionsBean {

            private CommentsBean comments;
            private CreditsBean credits;
            private LikesBean likes;
            private PicturesBean pictures;
            private TexttracksBean texttracks;
            private RelatedBean related;
            private RecommendationsBean recommendations;
            private OndemandBean ondemand;
            private PlaybackBean playback;
            private SeasonBean season;
            private UsersWithAccessBean users_with_access;
            private TrailerBean trailer;
            private VersionsBean versions;

            public void setComments(CommentsBean comments) {
                this.comments = comments;
            }

            public CommentsBean getComments() {
                return comments;
            }

            public void setCredits(CreditsBean credits) {
                this.credits = credits;
            }

            public void setLikes(LikesBean likes) {
                this.likes = likes;
            }

            public void setPictures(PicturesBean pictures) {
                this.pictures = pictures;
            }

            public CreditsBean getCredits() {
                return credits;
            }

            public LikesBean getLikes() {
                return likes;
            }

            public PicturesBean getPictures() {
                return pictures;
            }

            public void setTexttracks(TexttracksBean texttracks) {
                this.texttracks = texttracks;
            }

            public void setRelated(RelatedBean related) {
                this.related = related;
            }

            public void setRecommendations(RecommendationsBean recommendations) {
                this.recommendations = recommendations;
            }

            public OndemandBean getOndemand() {
                return ondemand;
            }

            public void setOndemand(OndemandBean ondemand) {
                this.ondemand = ondemand;
            }

            public TexttracksBean getTexttracks() {
                return texttracks;
            }

            public RelatedBean getRelated() {
                return related;
            }

            public RecommendationsBean getRecommendations() {
                return recommendations;
            }

            public PlaybackBean getPlayback() {
                return playback;
            }

            public void setPlayback(PlaybackBean playback) {
                this.playback = playback;
            }

            public SeasonBean getSeason() {
                return season;
            }

            public void setSeason(SeasonBean season) {
                this.season = season;
            }

            public UsersWithAccessBean getUsers_with_access() {
                return users_with_access;
            }

            public void setUsers_with_access(UsersWithAccessBean users_with_access) {
                this.users_with_access = users_with_access;
            }

            public TrailerBean getTrailer() {
                return trailer;
            }

            public void setTrailer(TrailerBean trailer) {
                this.trailer = trailer;
            }

            public VersionsBean getVersions() {
                return versions;
            }

            public void setVersions(VersionsBean versions) {
                this.versions = versions;
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

            public static class RelatedBean {

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

            public static class OndemandBean {

                private String resource_key;
                private String uri;
                private List<String> options;

                public void setResource_key(String resource_key) {
                    this.resource_key = resource_key;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public String getResource_key() {
                    return resource_key;
                }

                public String getUri() {
                    return uri;
                }

                public List<String> getOptions() {
                    return options;
                }
            }

            public static class PlaybackBean {

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

            public static class SeasonBean {

                private String name;
                private String uri;
                private List<String> options;

                public void setName(String name) {
                    this.name = name;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public String getName() {
                    return name;
                }

                public String getUri() {
                    return uri;
                }

                public List<String> getOptions() {
                    return options;
                }
            }

            public static class UsersWithAccessBean {

                private int total;
                private String uri;
                private List<String> options;

                public void setTotal(int total) {
                    this.total = total;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public int getTotal() {
                    return total;
                }

                public String getUri() {
                    return uri;
                }

                public List<String> getOptions() {
                    return options;
                }
            }

            public static class TrailerBean {

                private String resource_key;
                private String uri;
                private List<String> options;

                public void setResource_key(String resource_key) {
                    this.resource_key = resource_key;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public String getResource_key() {
                    return resource_key;
                }

                public String getUri() {
                    return uri;
                }

                public List<String> getOptions() {
                    return options;
                }
            }

            public static class VersionsBean {

                private String current_uri;
                private int total;
                private String uri;
                private List<String> options;

                public void setCurrent_uri(String current_uri) {
                    this.current_uri = current_uri;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public String getCurrent_uri() {
                    return current_uri;
                }

                public int getTotal() {
                    return total;
                }

                public String getUri() {
                    return uri;
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
            private BuyBean buy;
            private RentBean rent;
            private SubscribeBean subscribe;
            private WatchedBean watched;
            private ChannelBean channel;

            public LikeBean getLike() {
                return like;
            }

            public void setLike(LikeBean like) {
                this.like = like;
            }

            public void setWatchlater(WatchlaterBean watchlater) {
                this.watchlater = watchlater;
            }

            public WatchlaterBean getWatchlater() {
                return watchlater;
            }

            public ReportBean getReport() {
                return report;
            }

            public void setReport(ReportBean report) {
                this.report = report;
            }

            public BuyBean getBuy() {
                return buy;
            }

            public void setBuy(BuyBean buy) {
                this.buy = buy;
            }

            public RentBean getRent() {
                return rent;
            }

            public void setRent(RentBean rent) {
                this.rent = rent;
            }

            public SubscribeBean getSubscribe() {
                return subscribe;
            }

            public void setSubscribe(SubscribeBean subscribe) {
                this.subscribe = subscribe;
            }

            public WatchedBean getWatched() {
                return watched;
            }

            public void setWatched(WatchedBean watched) {
                this.watched = watched;
            }

            public ChannelBean getChannel() {
                return channel;
            }

            public void setChannel(ChannelBean channel) {
                this.channel = channel;
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
                private String added_time;
                private List<String> options;

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setAdded(boolean added) {
                    this.added = added;
                }

                public void setAdded_time(String added_time) {
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

                public String getAdded_time() {
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

            public static class BuyBean {

                private String currency;
                private String display_price;
                private String download;
                private String drm;
                private String link;
                private double price;
                private String purchase_time;
                private String stream;
                private String uri;

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public void setDisplay_price(String display_price) {
                    this.display_price = display_price;
                }

                public void setDownload(String download) {
                    this.download = download;
                }

                public void setDrm(String drm) {
                    this.drm = drm;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public void setPurchase_time(String purchase_time) {
                    this.purchase_time = purchase_time;
                }

                public void setStream(String stream) {
                    this.stream = stream;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getCurrency() {
                    return currency;
                }

                public String getDisplay_price() {
                    return display_price;
                }

                public String getDownload() {
                    return download;
                }

                public String getDrm() {
                    return drm;
                }

                public String getLink() {
                    return link;
                }

                public double getPrice() {
                    return price;
                }

                public String getPurchase_time() {
                    return purchase_time;
                }

                public String getStream() {
                    return stream;
                }

                public String getUri() {
                    return uri;
                }
            }

            public static class RentBean {

                private String currency;
                private String display_price;
                private String drm;
                private String expires_time;
                private String link;
                private double price;
                private String purchase_time;
                private String stream;
                private String uri;

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

                public void setDisplay_price(String display_price) {
                    this.display_price = display_price;
                }

                public void setDrm(String drm) {
                    this.drm = drm;
                }

                public void setExpires_time(String expires_time) {
                    this.expires_time = expires_time;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public void setPurchase_time(String purchase_time) {
                    this.purchase_time = purchase_time;
                }

                public void setStream(String stream) {
                    this.stream = stream;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getCurrency() {
                    return currency;
                }

                public String getDisplay_price() {
                    return display_price;
                }

                public String getDrm() {
                    return drm;
                }

                public String getExpires_time() {
                    return expires_time;
                }

                public String getLink() {
                    return link;
                }

                public double getPrice() {
                    return price;
                }

                public String getPurchase_time() {
                    return purchase_time;
                }

                public String getStream() {
                    return stream;
                }

                public String getUri() {
                    return uri;
                }
            }

            public static class SubscribeBean {

                private String drm;
                private String expires_time;
                private String purchase_time;
                private String stream;

                public void setDrm(String drm) {
                    this.drm = drm;
                }

                public void setExpires_time(String expires_time) {
                    this.expires_time = expires_time;
                }

                public void setPurchase_time(String purchase_time) {
                    this.purchase_time = purchase_time;
                }

                public void setStream(String stream) {
                    this.stream = stream;
                }

                public String getDrm() {
                    return drm;
                }

                public String getExpires_time() {
                    return expires_time;
                }

                public String getPurchase_time() {
                    return purchase_time;
                }

                public String getStream() {
                    return stream;
                }
            }

            public static class WatchedBean {

                private String added;
                private String added_time;
                private String uri;
                private List<String> options;

                public void setAdded(String added) {
                    this.added = added;
                }

                public void setAdded_time(String added_time) {
                    this.added_time = added_time;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public String getAdded() {
                    return added;
                }

                public String getAdded_time() {
                    return added_time;
                }

                public String getUri() {
                    return uri;
                }

                public List<String> getOptions() {
                    return options;
                }
            }

            public static class ChannelBean {

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

    public static class ParentProjectBean {

        private String created_time;
        private MetadataBean metadata;
        private String modified_time;
        private String name;
        private String resource_key;
        private String uri;
        private User user;


        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public String getCreated_time() {
            return created_time;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setModified_time(String modified_time) {
            this.modified_time = modified_time;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getModified_time() {
            return modified_time;
        }

        public String getName() {
            return name;
        }

        public String getResource_key() {
            return resource_key;
        }

        public String getUri() {
            return uri;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
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

                    private int total;
                    private String uri;
                    private List<String> options;

                    public void setTotal(int total) {
                        this.total = total;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public int getTotal() {
                        return total;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }
            }
        }
    }


    public static class SubcategoriesBean {

        private String uri;
        private String name;
        private String link;

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getUri() {
            return uri;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
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

    public static class TagsBean {
        private String canonical;
        private MetadataBean metadata;
        private String name;
        private String resource_key;
        private String uri;

        public void setCanonical(String canonical) {
            this.canonical = canonical;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getCanonical() {
            return canonical;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public String getName() {
            return name;
        }

        public String getResource_key() {
            return resource_key;
        }

        public String getUri() {
            return uri;
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

                    private int total;
                    private String uri;
                    private List<String> options;

                    public void setTotal(int total) {
                        this.total = total;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public int getTotal() {
                        return total;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }
            }
        }
    }

    public static class ParentFolderBean {

        private String created_time;
        private MetadataBean metadata;
        private String modified_time;
        private String name;
        private String resource_key;
        private String uri;
        private User user;

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public String getCreated_time() {
            return created_time;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setModified_time(String modified_time) {
            this.modified_time = modified_time;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getModified_time() {
            return modified_time;
        }

        public String getName() {
            return name;
        }

        public String getResource_key() {
            return resource_key;
        }

        public String getUri() {
            return uri;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
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

                    private int total;
                    private String uri;
                    private List<String> options;

                    public void setTotal(int total) {
                        this.total = total;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public int getTotal() {
                        return total;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }
            }
        }
    }

    public static class SpatialBean {

        private int field_of_view;
        private String projection;
        private String stereo_format;
        private List<DirectorTimelineBean> director_timeline;

        public void setField_of_view(int field_of_view) {
            this.field_of_view = field_of_view;
        }

        public void setProjection(String projection) {
            this.projection = projection;
        }

        public void setStereo_format(String stereo_format) {
            this.stereo_format = stereo_format;
        }

        public void setDirector_timeline(List<DirectorTimelineBean> director_timeline) {
            this.director_timeline = director_timeline;
        }

        public int getField_of_view() {
            return field_of_view;
        }

        public String getProjection() {
            return projection;
        }

        public String getStereo_format() {
            return stereo_format;
        }

        public List<DirectorTimelineBean> getDirector_timeline() {
            return director_timeline;
        }

        public static class DirectorTimelineBean {

            private int pitch;
            private int roll;
            private int time_code;
            private int yaw;

            public void setPitch(int pitch) {
                this.pitch = pitch;
            }

            public void setRoll(int roll) {
                this.roll = roll;
            }

            public void setTime_code(int time_code) {
                this.time_code = time_code;
            }

            public void setYaw(int yaw) {
                this.yaw = yaw;
            }

            public int getPitch() {
                return pitch;
            }

            public int getRoll() {
                return roll;
            }

            public int getTime_code() {
                return time_code;
            }

            public int getYaw() {
                return yaw;
            }
        }
    }

    public static class EmbedBean {

        private ButtonsBean buttons;
        private String color;
        private LogosBean logos;
        private String playbar;
        private String speed;
        private TitleBean title;
        private String uri;
        private String volume;


        public void setButtons(ButtonsBean buttons) {
            this.buttons = buttons;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public ButtonsBean getButtons() {
            return buttons;
        }

        public String getColor() {
            return color;
        }

        public void setLogos(LogosBean logos) {
            this.logos = logos;
        }

        public LogosBean getLogos() {
            return logos;
        }

        public void setPlaybar(String playbar) {
            this.playbar = playbar;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public void setTitle(TitleBean title) {
            this.title = title;
        }

        public String getPlaybar() {
            return playbar;
        }

        public String getSpeed() {
            return speed;
        }

        public TitleBean getTitle() {
            return title;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getUri() {
            return uri;
        }

        public String getVolume() {
            return volume;
        }

        public static class ButtonsBean {

            private String embed;
            private String fullscreen;
            private String hd;
            private String like;
            private String scaling;
            private String share;
            private String watchlater;

            public void setEmbed(String embed) {
                this.embed = embed;
            }

            public void setFullscreen(String fullscreen) {
                this.fullscreen = fullscreen;
            }

            public void setHd(String hd) {
                this.hd = hd;
            }

            public void setLike(String like) {
                this.like = like;
            }

            public void setScaling(String scaling) {
                this.scaling = scaling;
            }

            public void setShare(String share) {
                this.share = share;
            }

            public void setWatchlater(String watchlater) {
                this.watchlater = watchlater;
            }

            public String getEmbed() {
                return embed;
            }

            public String getFullscreen() {
                return fullscreen;
            }

            public String getHd() {
                return hd;
            }

            public String getLike() {
                return like;
            }

            public String getScaling() {
                return scaling;
            }

            public String getShare() {
                return share;
            }

            public String getWatchlater() {
                return watchlater;
            }
        }

        public static class LogosBean {

            private CustomBean custom;
            private String vimeo;

            public void setCustom(CustomBean custom) {
                this.custom = custom;
            }

            public void setVimeo(String vimeo) {
                this.vimeo = vimeo;
            }

            public CustomBean getCustom() {
                return custom;
            }

            public String getVimeo() {
                return vimeo;
            }

            public static class CustomBean {

                private String active;
                private String link;
                private String sticky;

                public void setActive(String active) {
                    this.active = active;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setSticky(String sticky) {
                    this.sticky = sticky;
                }

                public String getActive() {
                    return active;
                }

                public String getLink() {
                    return link;
                }

                public String getSticky() {
                    return sticky;
                }
            }
        }

        public static class TitleBean {

            private String name;
            private String owner;
            private String portrait;

            public void setName(String name) {
                this.name = name;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public void setPortrait(String portrait) {
                this.portrait = portrait;
            }

            public String getName() {
                return name;
            }

            public String getOwner() {
                return owner;
            }

            public String getPortrait() {
                return portrait;
            }
        }
    }

}
