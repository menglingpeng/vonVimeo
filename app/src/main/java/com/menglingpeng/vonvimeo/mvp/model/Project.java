package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {

    private String created_time;
    private String modified_time;
    private String name;
    private String resource_key;
    private String uri;
    private MetadataBean metadata;

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
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

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public String getCreated_time() {
        return created_time;
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

    public MetadataBean getMetadata() {
        return metadata;
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
