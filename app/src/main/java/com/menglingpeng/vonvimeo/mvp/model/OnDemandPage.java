package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class OnDemandPage {

    private String uri;
    private String name;
    private String description;
    private String type;
    private String link;
    private Object domain_link;
    private String theme;
    private ColorsBean colors;
    private PublishBean publish;
    private PreorderBean preorder;
    private Object rating;
    private PicturesBean pictures;
    private List<String> content_rating;
    private List<GenresBean> genres;
    private List<MetadataBean> metadatas;
    private Object rent;
    private FilmBean film;
    private Category category;
    private User user;
    private TrailerBean trailer;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDomain_link(Object domain_link) {
        this.domain_link = domain_link;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setColors(ColorsBean colors) {
        this.colors = colors;
    }

    public void setPublish(PublishBean publish) {
        this.publish = publish;
    }

    public void setPreorder(PreorderBean preorder) {
        this.preorder = preorder;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public void setContent_rating(List<String> content_rating) {
        this.content_rating = content_rating;
    }

    public List<MetadataBean> getMetadatas() {
        return metadatas;
    }

    public void setMetadatas(List<MetadataBean> metadatas) {
        this.metadatas = metadatas;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
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

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public Object getDomain_link() {
        return domain_link;
    }

    public String getTheme() {
        return theme;
    }

    public ColorsBean getColors() {
        return colors;
    }

    public PublishBean getPublish() {
        return publish;
    }

    public PreorderBean getPreorder() {
        return preorder;
    }

    public Object getRating() {
        return rating;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public List<String> getContent_rating() {
        return content_rating;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setRent(Object rent) {
        this.rent = rent;
    }

    public Object getRent() {
        return rent;
    }

    public void setFilm(FilmBean film) {
        this.film = film;
    }

    public FilmBean getFilm() {
        return film;
    }

    public void setTrailer(TrailerBean trailer) {
        this.trailer = trailer;
    }

    public TrailerBean getTrailer() {
        return trailer;
    }


    public static class ColorsBean {

        private String primary;
        private String secondary;

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public void setSecondary(String secondary) {
            this.secondary = secondary;
        }

        public String getPrimary() {
            return primary;
        }

        public String getSecondary() {
            return secondary;
        }
    }

    public static class PublishBean {

        private boolean active;
        private String time;

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean getActive() {
            return active;
        }

        public String getTime() {
            return time;
        }
    }

    public static class PreorderBean {

        private boolean active;
        private String time;
        private Object cancel_time;
        private String publish_time;

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setCancel_time(Object cancel_time) {
            this.cancel_time = cancel_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public boolean getActive() {
            return active;
        }

        public String getTime() {
            return time;
        }

        public Object getCancel_time() {
            return cancel_time;
        }

        public String getPublish_time() {
            return publish_time;
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

            public void setWidth(int width) {
                this.width = width;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public void setLink(String link) {
                this.link = link;
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
        }
    }

    public static class GenresBean{

        private String uri;
        private String name;
        private String canonical;
        private String link;
        private ConnectionsBean connections;

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCanonical(String canonical) {
            this.canonical = canonical;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setConnections(ConnectionsBean connections) {
            this.connections = connections;
        }

        public String getUri() {
            return uri;
        }

        public String getName() {
            return name;
        }

        public String getCanonical() {
            return canonical;
        }

        public String getLink() {
            return link;
        }

        public ConnectionsBean getConnections() {
            return connections;
        }

        public static class ConnectionsBean {

            private PagesBean pages;

            public void setPages(PagesBean pages) {
                this.pages = pages;
            }

            public PagesBean getPages() {
                return pages;
            }

            public static class PagesBean {

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

        public static class BuyBean{

            private boolean active;
            private PriceBean price;
            private String link;

            public void setActive(boolean active) {
                this.active = active;
            }

            public void setPrice(PriceBean price) {
                this.price = price;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public boolean getActive() {
                return active;
            }

            public PriceBean getPrice() {
                return price;
            }

            public String getLink() {
                return link;
            }

            public static class PriceBean {

                private double USD;
                private double GBP;
                private double EUR;
                private int CAD;
                private double AUD;
                private double DKK;
                private int JPY;
                private double NOK;
                private double PLN;
                private int KRW;
                private double SEK;
                private double CHF;

                public void setUSD(double USD) {
                    this.USD = USD;
                }

                public void setGBP(double GBP) {
                    this.GBP = GBP;
                }

                public void setEUR(double EUR) {
                    this.EUR = EUR;
                }

                public void setCAD(int CAD) {
                    this.CAD = CAD;
                }

                public void setAUD(double AUD) {
                    this.AUD = AUD;
                }

                public void setDKK(double DKK) {
                    this.DKK = DKK;
                }

                public void setJPY(int JPY) {
                    this.JPY = JPY;
                }

                public void setNOK(double NOK) {
                    this.NOK = NOK;
                }

                public void setPLN(double PLN) {
                    this.PLN = PLN;
                }

                public void setKRW(int KRW) {
                    this.KRW = KRW;
                }

                public void setSEK(double SEK) {
                    this.SEK = SEK;
                }

                public void setCHF(double CHF) {
                    this.CHF = CHF;
                }

                public double getUSD() {
                    return USD;
                }

                public double getGBP() {
                    return GBP;
                }

                public double getEUR() {
                    return EUR;
                }

                public int getCAD() {
                    return CAD;
                }

                public double getAUD() {
                    return AUD;
                }

                public double getDKK() {
                    return DKK;
                }

                public int getJPY() {
                    return JPY;
                }

                public double getNOK() {
                    return NOK;
                }

                public double getPLN() {
                    return PLN;
                }

                public int getKRW() {
                    return KRW;
                }

                public double getSEK() {
                    return SEK;
                }

                public double getCHF() {
                    return CHF;
                }
            }

        }

        public static class Episodes{

            private RentBean rent;
            private BuyBean buy;

            public void setRent(RentBean rent) {
                this.rent = rent;
            }

            public void setBuy(BuyBean buy) {
                this.buy = buy;
            }

            public RentBean getRent() {
                return rent;
            }

            public BuyBean getBuy() {
                return buy;
            }

            public static class RentBean {

                private boolean active;
                private Object price;
                private Object period;

                public void setActive(boolean active) {
                    this.active = active;
                }

                public void setPrice(Object price) {
                    this.price = price;
                }

                public void setPeriod(Object period) {
                    this.period = period;
                }

                public boolean getActive() {
                    return active;
                }

                public Object getPrice() {
                    return price;
                }

                public Object getPeriod() {
                    return period;
                }
            }

            public static class BuyBean {

                private boolean active;
                private Object price;

                public void setActive(boolean active) {
                    this.active = active;
                }

                public void setPrice(Object price) {
                    this.price = price;
                }

                public boolean getActive() {
                    return active;
                }

                public Object getPrice() {
                    return price;
                }
            }
        }
    }

    public static class InteractionsBean{

        private Object subscribe;
        private BuyBean buy;
        private Object rent;

        public void setSubscribe(Object subscribe) {
            this.subscribe = subscribe;
        }

        public void setBuy(BuyBean buy) {
            this.buy = buy;
        }

        public void setRent(Object rent) {
            this.rent = rent;
        }

        public Object getSubscribe() {
            return subscribe;
        }

        public BuyBean getBuy() {
            return buy;
        }

        public Object getRent() {
            return rent;
        }

        public static class BuyBean {

            private Object expires_time;
            private Object purchase_time;
            private Object uri;
            private String download;
            private String stream;
            private String link;
            private boolean drm;
            private List<String> options;

            public void setExpires_time(Object expires_time) {
                this.expires_time = expires_time;
            }

            public void setPurchase_time(Object purchase_time) {
                this.purchase_time = purchase_time;
            }

            public void setUri(Object uri) {
                this.uri = uri;
            }

            public void setDownload(String download) {
                this.download = download;
            }

            public void setStream(String stream) {
                this.stream = stream;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public void setDrm(boolean drm) {
                this.drm = drm;
            }

            public void setOptions(List<String> options) {
                this.options = options;
            }

            public Object getExpires_time() {
                return expires_time;
            }

            public Object getPurchase_time() {
                return purchase_time;
            }

            public Object getUri() {
                return uri;
            }

            public String getDownload() {
                return download;
            }

            public String getStream() {
                return stream;
            }

            public String getLink() {
                return link;
            }

            public boolean getDrm() {
                return drm;
            }

            public List<String> getOptions() {
                return options;
            }
        }
    }

    public static class MetadataBean{

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
            private CreditsBean credits;
            private VideosBean videos;
            private GenresBean genres;
            private PicturesBean pictures;
            private LikesBean likes;
            private SeasonsBean seasons;

            public void setComments(CommentsBean comments) {
                this.comments = comments;
            }

            public void setCredits(CreditsBean credits) {
                this.credits = credits;
            }

            public CommentsBean getComments() {
                return comments;
            }

            public CreditsBean getCredits() {
                return credits;
            }

            public void setVideos(VideosBean videos) {
                this.videos = videos;
            }

            public void setGenres(GenresBean genres) {
                this.genres = genres;
            }

            public VideosBean getVideos() {
                return videos;
            }

            public GenresBean getGenres() {
                return genres;
            }

            public void setPictures(PicturesBean pictures) {
                this.pictures = pictures;
            }

            public void setLikes(LikesBean likes) {
                this.likes = likes;
            }

            public PicturesBean getPictures() {
                return pictures;
            }

            public LikesBean getLikes() {
                return likes;
            }

            public void setSeasons(SeasonsBean seasons) {
                this.seasons = seasons;
            }

            public SeasonsBean getSeasons() {
                return seasons;
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

            public static class VideosBean {

                private String uri;
                private int total;
                private int main_total;
                private int extra_total;
                private int viewable_total;
                private List<String> options;

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public void setMain_total(int main_total) {
                    this.main_total = main_total;
                }

                public void setExtra_total(int extra_total) {
                    this.extra_total = extra_total;
                }

                public void setViewable_total(int viewable_total) {
                    this.viewable_total = viewable_total;
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

                public int getMain_total() {
                    return main_total;
                }

                public int getExtra_total() {
                    return extra_total;
                }

                public int getViewable_total() {
                    return viewable_total;
                }

                public List<String> getOptions() {
                    return options;
                }
            }

            public static class GenresBean {

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

            public static class SeasonsBean {

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

            private Object subscribe;
            private BuyBean buy;
            private Object rent;

            public void setSubscribe(Object subscribe) {
                this.subscribe = subscribe;
            }

            public void setBuy(BuyBean buy) {
                this.buy = buy;
            }

            public void setRent(Object rent) {
                this.rent = rent;
            }

            public Object getSubscribe() {
                return subscribe;
            }

            public BuyBean getBuy() {
                return buy;
            }

            public Object getRent() {
                return rent;
            }

            public static class BuyBean {

                private Object expires_time;
                private Object purchase_time;
                private Object uri;
                private String download;
                private String stream;
                private String link;
                private boolean drm;
                private List<String> options;

                public void setExpires_time(Object expires_time) {
                    this.expires_time = expires_time;
                }

                public void setPurchase_time(Object purchase_time) {
                    this.purchase_time = purchase_time;
                }

                public void setUri(Object uri) {
                    this.uri = uri;
                }

                public void setDownload(String download) {
                    this.download = download;
                }

                public void setStream(String stream) {
                    this.stream = stream;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setDrm(boolean drm) {
                    this.drm = drm;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public Object getExpires_time() {
                    return expires_time;
                }

                public Object getPurchase_time() {
                    return purchase_time;
                }

                public Object getUri() {
                    return uri;
                }

                public String getDownload() {
                    return download;
                }

                public String getStream() {
                    return stream;
                }

                public String getLink() {
                    return link;
                }

                public boolean getDrm() {
                    return drm;
                }

                public List<String> getOptions() {
                    return options;
                }
            }
        }
    }


    public static class FilmBean {

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
        private StatsBean stats;
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

        public static class StatsBean {

            private int plays;

            public void setPlays(int plays) {
                this.plays = plays;
            }

            public int getPlays() {
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

            public void setInteractions(InteractionsBean interactions) {
                this.interactions = interactions;
            }

            public InteractionsBean getInteractions() {
                return interactions;
            }


            public static class ConnectionsBean {
                private OndemandBean ondemand;
                private TrailerBean trailer;
                private SeasonBean season;
                private CommentsBean comments;
                private LikesBean likes;
                private TexttracksBean texttracks;
                private RelatedBean related;
                private RecommendationsBean recommendations;
                private CreditsBean credits;
                private PicturesBean pictures;


                public void setOndemand(OndemandBean ondemand) {
                    this.ondemand = ondemand;
                }

                public void setTrailer(TrailerBean trailer) {
                    this.trailer = trailer;
                }

                public void setSeason(SeasonBean season) {
                    this.season = season;
                }

                public OndemandBean getOndemand() {
                    return ondemand;
                }

                public TrailerBean getTrailer() {
                    return trailer;
                }

                public SeasonBean getSeason() {
                    return season;
                }

                public CommentsBean getComments() {
                    return comments;
                }

                public void setComments(CommentsBean comments) {
                    this.comments = comments;
                }

                public LikesBean getLikes() {
                    return likes;
                }

                public void setLikes(LikesBean likes) {
                    this.likes = likes;
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

                public TexttracksBean getTexttracks() {
                    return texttracks;
                }

                public RelatedBean getRelated() {
                    return related;
                }

                public RecommendationsBean getRecommendations() {
                    return recommendations;
                }

                public void setCredits(CreditsBean credits) {
                    this.credits = credits;
                }

                public CreditsBean getCredits() {
                    return credits;
                }

                public void setPictures(PicturesBean pictures) {
                    this.pictures = pictures;
                }

                public PicturesBean getPictures() {
                    return pictures;
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

                public static class OndemandBean {

                    private String uri;
                    private String resource_key;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setResource_key(String resource_key) {
                        this.resource_key = resource_key;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public String getResource_key() {
                        return resource_key;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }

                public static class TrailerBean {

                    private String uri;
                    private String resource_key;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setResource_key(String resource_key) {
                        this.resource_key = resource_key;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public String getResource_key() {
                        return resource_key;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }

                public static class SeasonBean {

                    private String uri;
                    private String name;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public String getName() {
                        return name;
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
            }

            public static class InteractionsBean {
                private RentBean rent;
                private BuyBean buy;
                private WatchlaterBean watchlater;
                private LikeBean like;
                private Object subscribe;
                private ReportBean report;


                public void setRent(RentBean rent) {
                    this.rent = rent;
                }

                public RentBean getRent() {
                    return rent;
                }

                public void setBuy(BuyBean buy) {
                    this.buy = buy;
                }

                public BuyBean getBuy() {
                    return buy;
                }

                public void setSubscribe(Object subscribe) {
                    this.subscribe = subscribe;
                }

                public void setReport(ReportBean report) {
                    this.report = report;
                }

                public Object getSubscribe() {
                    return subscribe;
                }

                public ReportBean getReport() {
                    return report;
                }

                public LikeBean getLike() {
                    return like;
                }

                public void setLike(LikeBean like) {
                    this.like = like;
                }

                public WatchlaterBean getWatchlater() {
                    return watchlater;
                }

                public void setWatchlater(WatchlaterBean watchlater) {
                    this.watchlater = watchlater;
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

                public static class RentBean {

                    private Object expires_time;
                    private Object purchase_time;
                    private Object uri;
                    private String stream;
                    private Object link;
                    private Object price;
                    private Object currency;
                    private Object display_price;
                    private boolean drm;

                    public void setExpires_time(Object expires_time) {
                        this.expires_time = expires_time;
                    }

                    public void setPurchase_time(Object purchase_time) {
                        this.purchase_time = purchase_time;
                    }

                    public void setUri(Object uri) {
                        this.uri = uri;
                    }

                    public void setStream(String stream) {
                        this.stream = stream;
                    }

                    public void setLink(Object link) {
                        this.link = link;
                    }

                    public void setPrice(Object price) {
                        this.price = price;
                    }

                    public void setCurrency(Object currency) {
                        this.currency = currency;
                    }

                    public void setDisplay_price(Object display_price) {
                        this.display_price = display_price;
                    }

                    public void setDrm(boolean drm) {
                        this.drm = drm;
                    }

                    public Object getExpires_time() {
                        return expires_time;
                    }

                    public Object getPurchase_time() {
                        return purchase_time;
                    }

                    public Object getUri() {
                        return uri;
                    }

                    public String getStream() {
                        return stream;
                    }

                    public Object getLink() {
                        return link;
                    }

                    public Object getPrice() {
                        return price;
                    }

                    public Object getCurrency() {
                        return currency;
                    }

                    public Object getDisplay_price() {
                        return display_price;
                    }

                    public boolean getDrm() {
                        return drm;
                    }
                }

                public static class BuyBean {

                    private Object expires_time;
                    private Object purchase_time;
                    private Object uri;
                    private String download;
                    private String stream;
                    private Object link;
                    private Object price;
                    private Object currency;
                    private Object display_price;
                    private boolean drm;

                    public void setExpires_time(Object expires_time) {
                        this.expires_time = expires_time;
                    }

                    public void setPurchase_time(Object purchase_time) {
                        this.purchase_time = purchase_time;
                    }

                    public void setUri(Object uri) {
                        this.uri = uri;
                    }

                    public void setDownload(String download) {
                        this.download = download;
                    }

                    public void setStream(String stream) {
                        this.stream = stream;
                    }

                    public void setLink(Object link) {
                        this.link = link;
                    }

                    public void setPrice(Object price) {
                        this.price = price;
                    }

                    public void setCurrency(Object currency) {
                        this.currency = currency;
                    }

                    public void setDisplay_price(Object display_price) {
                        this.display_price = display_price;
                    }

                    public void setDrm(boolean drm) {
                        this.drm = drm;
                    }

                    public Object getExpires_time() {
                        return expires_time;
                    }

                    public Object getPurchase_time() {
                        return purchase_time;
                    }

                    public Object getUri() {
                        return uri;
                    }

                    public String getDownload() {
                        return download;
                    }

                    public String getStream() {
                        return stream;
                    }

                    public Object getLink() {
                        return link;
                    }

                    public Object getPrice() {
                        return price;
                    }

                    public Object getCurrency() {
                        return currency;
                    }

                    public Object getDisplay_price() {
                        return display_price;
                    }

                    public boolean getDrm() {
                        return drm;
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
    }


    public static class TrailerBean {
        private String name;
        private String description;
        private String link;
        private int width;
        private String language;
        private int height;
        private EmbedBean embed;
        private String created_time;
        private String modified_time;
        private String release_time;
        private Object license;
        private List<String> content_rating;
        private PrivacyBean privacy;
        private PicturesBean pictures;
        private Tag tag;
        private String resource_key;
        private MetadataBean metadata;


        public Tag getTag() {
            return tag;
        }

        public void setTag(Tag tag) {
            this.tag = tag;
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

        public void setWidth(int width) {
            this.width = width;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setEmbed(EmbedBean embed) {
            this.embed = embed;
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

        public int getWidth() {
            return width;
        }

        public String getLanguage() {
            return language;
        }

        public int getHeight() {
            return height;
        }

        public EmbedBean getEmbed() {
            return embed;
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

        public List<String> getContent_rating() {
            return content_rating;
        }

        public void setPrivacy(PrivacyBean privacy) {
            this.privacy = privacy;
        }

        public void setPictures(PicturesBean pictures) {
            this.pictures = pictures;
        }

        public PrivacyBean getPrivacy() {
            return privacy;
        }

        public PicturesBean getPictures() {
            return pictures;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public String getResource_key() {
            return resource_key;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public static class EmbedBean {

            private String html;
            private BadgesBean badges;
            private StaffPickBean staff_pick;
            private boolean vod;
            private boolean weekend_challenge;


            public void setHtml(String html) {
                this.html = html;
            }

            public String getHtml() {
                return html;
            }

            public void setBadges(BadgesBean badges) {
                this.badges = badges;
            }

            public BadgesBean getBadges() {
                return badges;
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

            public StaffPickBean getStaff_pick() {
                return staff_pick;
            }

            public boolean getVod() {
                return vod;
            }

            public boolean getWeekend_challenge() {
                return weekend_challenge;
            }

            public static class BadgesBean {

                private boolean hdr;
                private LiveBean live;

                public void setHdr(boolean hdr) {
                    this.hdr = hdr;
                }

                public void setLive(LiveBean live) {
                    this.live = live;
                }

                public boolean getHdr() {
                    return hdr;
                }

                public LiveBean getLive() {
                    return live;
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
                private CreditsBean credits;
                private LikesBean likes;
                private PicturesBean pictures;
                private TexttracksBean texttracks;
                private RelatedBean related;
                private RecommendationsBean recommendations;
                private OndemandBean ondemand;


                public void setComments(CommentsBean comments) {
                    this.comments = comments;
                }

                public void setCredits(CreditsBean credits) {
                    this.credits = credits;
                }

                public CommentsBean getComments() {
                    return comments;
                }

                public CreditsBean getCredits() {
                    return credits;
                }

                public void setLikes(LikesBean likes) {
                    this.likes = likes;
                }

                public void setPictures(PicturesBean pictures) {
                    this.pictures = pictures;
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

                public TexttracksBean getTexttracks() {
                    return texttracks;
                }

                public RelatedBean getRelated() {
                    return related;
                }

                public RecommendationsBean getRecommendations() {
                    return recommendations;
                }

                public void setOndemand(OndemandBean ondemand) {
                    this.ondemand = ondemand;
                }

                public OndemandBean getOndemand() {
                    return ondemand;
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

                    private String uri;
                    private String resource_key;
                    private List<String> options;

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public void setResource_key(String resource_key) {
                        this.resource_key = resource_key;
                    }

                    public void setOptions(List<String> options) {
                        this.options = options;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public String getResource_key() {
                        return resource_key;
                    }

                    public List<String> getOptions() {
                        return options;
                    }
                }
            }

            public static class InteractionsBean {


                private WatchlaterBean watchlater;
                private LikeBean like;
                private Object rent;
                private Object buy;
                private Object subscribe;
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

                public void setRent(Object rent) {
                    this.rent = rent;
                }

                public void setBuy(Object buy) {
                    this.buy = buy;
                }

                public void setSubscribe(Object subscribe) {
                    this.subscribe = subscribe;
                }

                public void setReport(ReportBean report) {
                    this.report = report;
                }

                public Object getRent() {
                    return rent;
                }

                public Object getBuy() {
                    return buy;
                }

                public Object getSubscribe() {
                    return subscribe;
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
    }
}
