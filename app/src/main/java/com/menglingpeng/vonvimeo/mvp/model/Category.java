package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    private String uri;
    private String name;
    private String link;
    private boolean top_level;
    private PicturesBean pictures;
    private String last_video_featured_time;
    private Object parent;
    private MetadataBean metadata;
    private IconBean icon;
    private String resource_key;
    private List<SubcategoriesBean> subcategories;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTop_level(boolean top_level) {
        this.top_level = top_level;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public void setLast_video_featured_time(String last_video_featured_time) {
        this.last_video_featured_time = last_video_featured_time;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public void setIcon(IconBean icon) {
        this.icon = icon;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public void setSubcategories(List<SubcategoriesBean> subcategories) {
        this.subcategories = subcategories;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public boolean getTop_level() {
        return top_level;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public String getLast_video_featured_time() {
        return last_video_featured_time;
    }

    public Object getParent() {
        return parent;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public IconBean getIcon() {
        return icon;
    }

    public String getResource_key() {
        return resource_key;
    }

    public List<SubcategoriesBean> getSubcategories() {
        return subcategories;
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

        public void setInteractions(InteractionsBean interactions) {
            this.interactions = interactions;
        }

        public ConnectionsBean getConnections() {
            return connections;
        }

        public InteractionsBean getInteractions() {
            return interactions;
        }

        public static class ConnectionsBean {

            private ChannelsBean channels;
            private GroupsBean groups;
            private UsersBean users;
            private VideosBean videos;

            public void setChannels(ChannelsBean channels) {
                this.channels = channels;
            }

            public void setGroups(GroupsBean groups) {
                this.groups = groups;
            }

            public void setUsers(UsersBean users) {
                this.users = users;
            }

            public void setVideos(VideosBean videos) {
                this.videos = videos;
            }

            public ChannelsBean getChannels() {
                return channels;
            }

            public GroupsBean getGroups() {
                return groups;
            }

            public UsersBean getUsers() {
                return users;
            }

            public VideosBean getVideos() {
                return videos;
            }

            public static class ChannelsBean {

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

            public static class GroupsBean {

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

            public static class UsersBean {

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
                private Object added_time;
                private String uri;

                public void setAdded(boolean added) {
                    this.added = added;
                }

                public void setAdded_time(Object added_time) {
                    this.added_time = added_time;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public boolean getAdded() {
                    return added;
                }

                public Object getAdded_time() {
                    return added_time;
                }

                public String getUri() {
                    return uri;
                }
            }
        }
    }

    public static class IconBean {

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

    public static class SubcategoriesBean {

        private String uri;
        private String name;
        private String link;

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getUri() {
            return uri;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }
    }
}
