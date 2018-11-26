package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;

public class Season implements Serializable{

    private String uri;
    private String name;
    private String type;
    private String description;
    private int position;
    private User user;
    /**
     * metadata : {"interactions":{"subscribe":null,"buy":null,"rent":null}}
     */

    private MetadataBean metadata;

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


    public static class MetadataBean {

        private InteractionsBean interactions;

        public void setInteractions(InteractionsBean interactions) {
            this.interactions = interactions;
        }

        public InteractionsBean getInteractions() {
            return interactions;
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
    }
}
