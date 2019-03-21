package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class OnDemandGenre implements Serializable {

    private String canonical;
    private InteractionsBean interactions;
    private String link;
    private MetadataBean metadata;
    private String name;
    private String uri;

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    public void setInteractions(InteractionsBean interactions) {
        this.interactions = interactions;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCanonical() {
        return canonical;
    }

    public InteractionsBean getInteractions() {
        return interactions;
    }

    public String getLink() {
        return link;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public static class InteractionsBean {

        private PageBean page;

        public void setPage(PageBean page) {
            this.page = page;
        }

        public PageBean getPage() {
            return page;
        }

        public static class PageBean {

            private String added;
            private String uri;
            private List<String> options;

            public void setAdded(String added) {
                this.added = added;
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

            public String getUri() {
                return uri;
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
}
