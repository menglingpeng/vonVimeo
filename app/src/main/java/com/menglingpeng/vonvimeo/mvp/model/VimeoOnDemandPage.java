package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class VimeoOnDemandPage implements Serializable {

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
    private PicturesBean pictures;
    private List<String> content_rating;
    private List<GenresBean> genres;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Object getDomain_link() {
        return domain_link;
    }

    public void setDomain_link(Object domain_link) {
        this.domain_link = domain_link;
    }

    public ColorsBean getColors() {
        return colors;
    }

    public void setColors(ColorsBean colors) {
        this.colors = colors;
    }

    public PublishBean getPublish() {
        return publish;
    }

    public void setPublish(PublishBean publish) {
        this.publish = publish;
    }

    public PreorderBean getPreorder() {
        return preorder;
    }

    public void setPreorder(PreorderBean preorder) {
        this.preorder = preorder;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<String> getContent_rating() {
        return content_rating;
    }

    public void setContent_rating(List<String> content_rating) {
        this.content_rating = content_rating;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
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

    public static class GenresBean {

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

        public static class BuyBean {

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

        public static class Episodes {

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

        public static class MetadataBean {

            private ConnectionsBean connections;

            public void setConnections(ConnectionsBean connections) {
                this.connections = connections;
            }

            public ConnectionsBean getConnections() {
                return connections;
            }

            public static class ConnectionsBean {

                private CommentsBean comments;
                private CreditsBean credits;

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
            }
        }
    }
}
