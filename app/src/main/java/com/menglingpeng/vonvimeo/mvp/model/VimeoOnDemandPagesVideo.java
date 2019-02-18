package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class VimeoOnDemandPagesVideo implements Serializable{

    private String uri;
    private String name;
    private String description;
    private String link;
    private int duration;
    private int width;
    private String language;
    private int height;
    private EmbedBean embed;
    private String created_time;
    private String modified_time;
    private String release_time;
    private Object license;
    private PrivacyBean privacy;
    private PicturesBean pictures;
    private StatsBean stats;
    private MetadataBean metadataBean;
    private TranscodeBean transcode;
    private AppBean app;
    private TagsBean tags;
    private SubcategoriesBean subcategories;
    private UploadBean upload;


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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public EmbedBean getEmbed() {
        return embed;
    }

    public void setEmbed(EmbedBean embed) {
        this.embed = embed;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getModified_time() {
        return modified_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    public PrivacyBean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public StatsBean getStats() {
        return stats;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public MetadataBean getMetadataBean() {
        return metadataBean;
    }

    public void setMetadataBean(MetadataBean metadataBean) {
        this.metadataBean = metadataBean;
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

    public TagsBean getTags() {
        return tags;
    }

    public void setTags(TagsBean tags) {
        this.tags = tags;
    }

    public SubcategoriesBean getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(SubcategoriesBean subcategories) {
        this.subcategories = subcategories;
    }

    public UploadBean getUpload() {
        return upload;
    }

    public void setUpload(UploadBean upload) {
        this.upload = upload;
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

            public void setComments(CommentsBean comments) {
                this.comments = comments;
            }

            public CommentsBean getComments() {
                return comments;
            }

            public void setCredits(CreditsBean credits) {
                this.credits = credits;
            }

            public CreditsBean getCredits() {
                return credits;
            }

            public LikesBean getLikes() {
                return likes;
            }

            public void setLikes(LikesBean likes) {
                this.likes = likes;
            }

            public PicturesBean getPictures() {
                return pictures;
            }

            public void setPictures(PicturesBean pictures) {
                this.pictures = pictures;
            }

            public TexttracksBean getTexttracks() {
                return texttracks;
            }

            public void setTexttracks(TexttracksBean texttracks) {
                this.texttracks = texttracks;
            }

            public RelatedBean getRelated() {
                return related;
            }

            public void setRelated(RelatedBean related) {
                this.related = related;
            }

            public RecommendationsBean getRecommendations() {
                return recommendations;
            }

            public void setRecommendations(RecommendationsBean recommendations) {
                this.recommendations = recommendations;
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
        }

        public static class InteractionsBean {

            private WatchlaterBean watchlater;
            private LikeBean like;
            private ReportBean report;
            private BuyBean buy;
            private RentBean rent;
            private SubscribeBean subscribe;
            private WatchedBean watched;


            public void setWatchlater(WatchlaterBean watchlater) {
                this.watchlater = watchlater;
            }

            public WatchlaterBean getWatchlater() {
                return watchlater;
            }

            public LikeBean getLike() {
                return like;
            }

            public void setLike(LikeBean like) {
                this.like = like;
            }

            public ReportBean getReport() {
                return report;
            }

            public void setReport(ReportBean report) {
                this.report = report;
            }

            public void setBuy(BuyBean buy) {
                this.buy = buy;
            }

            public void setRent(RentBean rent) {
                this.rent = rent;
            }

            public BuyBean getBuy() {
                return buy;
            }

            public RentBean getRent() {
                return rent;
            }

            public void setSubscribe(SubscribeBean subscribe) {
                this.subscribe = subscribe;
            }

            public void setWatched(WatchedBean watched) {
                this.watched = watched;
            }

            public SubscribeBean getSubscribe() {
                return subscribe;
            }

            public WatchedBean getWatched() {
                return watched;
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
}
