package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Season implements Serializable{

    private String uri;
    private String name;
    private String type;
    private String description;
    private int position;
    private User user;
    private MetadataBean metadata;
    private String resource_key;


    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getPosition() {
        return position;
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

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public String getResource_key() {
        return resource_key;
    }


    public static class MetadataBean {

        private InteractionsBean interactions;
        private ConnectionsBean connections;


        public void setInteractions(InteractionsBean interactions) {
            this.interactions = interactions;
        }

        public InteractionsBean getInteractions() {
            return interactions;
        }

        public void setConnections(ConnectionsBean connections) {
            this.connections = connections;
        }

        public ConnectionsBean getConnections() {
            return connections;
        }

        public static class InteractionsBean {

            private Object subscribe;
            private Object buy;
            private Object rent;

            public void setSubscribe(Object subscribe) {
                this.subscribe = subscribe;
            }

            public void setBuy(Object buy) {
                this.buy = buy;
            }

            public void setRent(Object rent) {
                this.rent = rent;
            }

            public Object getSubscribe() {
                return subscribe;
            }

            public Object getBuy() {
                return buy;
            }

            public Object getRent() {
                return rent;
            }
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
