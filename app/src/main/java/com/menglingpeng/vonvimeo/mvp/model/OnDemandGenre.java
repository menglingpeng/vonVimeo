package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class OnDemandGenre implements Serializable {

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
}
