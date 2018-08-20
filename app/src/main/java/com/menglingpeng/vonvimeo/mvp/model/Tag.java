package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Tag implements Serializable{

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
