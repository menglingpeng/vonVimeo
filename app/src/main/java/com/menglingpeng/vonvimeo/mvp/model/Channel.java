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
    private PicturesBean pictures;
    private HeaderBean header;
    private PrivacyBean privacy;
    private List<TagsBean> tags;

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

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }

    public PrivacyBean getPrivacy() {
        return privacy;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<TagsBean> getTags() {
        return tags;
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

    public static class HeaderBean {

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


    public static class PrivacyBean {

        private String view;

        public void setView(String view) {
            this.view = view;
        }

        public String getView() {
            return view;
        }
    }

    public static class TagsBean {

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

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public String getResource_key() {
            return resource_key;
        }

        public static class MetadataBean {

            private ConnectionsBean connections;
            /**
             * interactions : {"follow":{"added":true,"added_time":"2018-08-20T10:46:31+00:00","type":"subscriber",
             * "uri":"/users/86476471/channels/927"}}
             */

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

            public static class InteractionsBean {

                private FollowBean follow;

                public void setFollow(FollowBean follow) {
                    this.follow = follow;
                }

                public FollowBean getFollow() {
                    return follow;
                }

                public static class FollowBean {

                    private boolean added;
                    private String added_time;
                    private String type;
                    private String uri;

                    public void setAdded(boolean added) {
                        this.added = added;
                    }

                    public void setAdded_time(String added_time) {
                        this.added_time = added_time;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public boolean getAdded() {
                        return added;
                    }

                    public String getAdded_time() {
                        return added_time;
                    }

                    public String getType() {
                        return type;
                    }

                    public String getUri() {
                        return uri;
                    }
                }
            }
        }
    }
}
