package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class Follow {

    private String uri;
    private String name;
    private String link;
    private String location;
    private String bio;
    private String created_time;
    private PicturesBean pictures;
    private MetadataBean metadata;
    private String resource_key;
    private String account;
    private List<WebsitesBean> websites;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setWebsites(List<WebsitesBean> websites) {
        this.websites = websites;
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

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public String getCreated_time() {
        return created_time;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public String getResource_key() {
        return resource_key;
    }

    public String getAccount() {
        return account;
    }

    public List<WebsitesBean> getWebsites() {
        return websites;
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

            private AlbumsBean albums;
            private AppearancesBean appearances;
            private ChannelsBean channels;
            private FeedBean feed;
            private FollowersBean followers;
            private FollowingBean following;
            private GroupsBean groups;
            private LikesBean likes;
            private ModeratedChannelsBean moderated_channels;
            private PortfoliosBean portfolios;
            private VideosBean videos;
            private SharedBean shared;
            private PicturesBean pictures;

            public void setAlbums(AlbumsBean albums) {
                this.albums = albums;
            }

            public void setAppearances(AppearancesBean appearances) {
                this.appearances = appearances;
            }

            public void setChannels(ChannelsBean channels) {
                this.channels = channels;
            }

            public void setFeed(FeedBean feed) {
                this.feed = feed;
            }

            public void setFollowers(FollowersBean followers) {
                this.followers = followers;
            }

            public void setFollowing(FollowingBean following) {
                this.following = following;
            }

            public void setGroups(GroupsBean groups) {
                this.groups = groups;
            }

            public void setLikes(LikesBean likes) {
                this.likes = likes;
            }

            public void setModerated_channels(ModeratedChannelsBean moderated_channels) {
                this.moderated_channels = moderated_channels;
            }

            public void setPortfolios(PortfoliosBean portfolios) {
                this.portfolios = portfolios;
            }

            public void setVideos(VideosBean videos) {
                this.videos = videos;
            }

            public void setShared(SharedBean shared) {
                this.shared = shared;
            }

            public void setPictures(PicturesBean pictures) {
                this.pictures = pictures;
            }

            public AlbumsBean getAlbums() {
                return albums;
            }

            public AppearancesBean getAppearances() {
                return appearances;
            }

            public ChannelsBean getChannels() {
                return channels;
            }

            public FeedBean getFeed() {
                return feed;
            }

            public FollowersBean getFollowers() {
                return followers;
            }

            public FollowingBean getFollowing() {
                return following;
            }

            public GroupsBean getGroups() {
                return groups;
            }

            public LikesBean getLikes() {
                return likes;
            }

            public ModeratedChannelsBean getModerated_channels() {
                return moderated_channels;
            }

            public PortfoliosBean getPortfolios() {
                return portfolios;
            }

            public VideosBean getVideos() {
                return videos;
            }

            public SharedBean getShared() {
                return shared;
            }

            public PicturesBean getPictures() {
                return pictures;
            }

            public static class AlbumsBean {

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

            public static class AppearancesBean {

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

            public static class FeedBean {

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

            public static class FollowersBean {

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

            public static class FollowingBean {

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

            public static class LikesBean {

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

            public static class ModeratedChannelsBean {

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

            public static class PortfoliosBean {

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

            public static class SharedBean {

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

            public static class PicturesBean {

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

        }

        public static class FollowBean {
            private boolean added;
            private String added_time;
            private String uri;

            public void setAdded(boolean added) {
                this.added = added;
            }

            public void setAdded_time(String added_time) {
                this.added_time = added_time;
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

            public String getUri() {
                return uri;
            }
        }

    }
    public static class WebsitesBean {

        private String name;
        private String link;
        private String description;

        public void setName(String name) {
            this.name = name;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        public String getDescription() {
            return description;
        }
    }
}
