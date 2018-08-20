package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Channel implements Serializable {

    private String uri;
    private String name;
    private String description;
    private String link;
    private String created_time;
    private String modified_time;
    private MetadataBean metadata;
    private String resource_key;
    private List<?> categories;

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

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public String getResource_key() {
        return resource_key;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public List<?> getCategories() {
        return categories;
    }

    public void setCategories(List<?> categories) {
        this.categories = categories;
    }



    public static class MetadataBean {

        private ConnectionsBean connections;
        private InteractionsBean interactions;

        public ConnectionsBean getConnections() {
            return connections;
        }

        public void setConnections(ConnectionsBean connections) {
            this.connections = connections;
        }

        public InteractionsBean getInteractions() {
            return interactions;
        }

        public void setInteractions(InteractionsBean interactions) {
            this.interactions = interactions;
        }

        public static class ConnectionsBean {

            private UsersBean users;
            private VideosBean videos;

            public UsersBean getUsers() {
                return users;
            }

            public void setUsers(UsersBean users) {
                this.users = users;
            }

            public VideosBean getVideos() {
                return videos;
            }

            public void setVideos(VideosBean videos) {
                this.videos = videos;
            }

            public static class UsersBean {

                private String uri;
                private int total;
                private List<String> options;

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public List<String> getOptions() {
                    return options;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }
            }

            public static class VideosBean {

                private String uri;
                private int total;
                private List<String> options;

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public List<String> getOptions() {
                    return options;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }
            }
        }

        public static class InteractionsBean {

            private FollowBean follow;

            public FollowBean getFollow() {
                return follow;
            }

            public void setFollow(FollowBean follow) {
                this.follow = follow;
            }

            public static class FollowBean {

                private boolean added;
                private Object added_time;
                private Object type;
                private String uri;

                public boolean isAdded() {
                    return added;
                }

                public void setAdded(boolean added) {
                    this.added = added;
                }

                public Object getAdded_time() {
                    return added_time;
                }

                public void setAdded_time(Object added_time) {
                    this.added_time = added_time;
                }

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }
            }
        }
    }
}
