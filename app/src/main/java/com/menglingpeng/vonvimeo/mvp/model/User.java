package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String uri;
    private String name;
    private String link;
    private Object location;
    private Object bio;
    private String created_time;
    private PicturesBean pictures;
    private MetadataBean metadata;
    private PreferencesBean preferences;
    private String resource_key;
    private String account;
    private List<?> websites;
    private List<String> content_filter;

    public PicturesBean getPictures() {
        return pictures;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public PreferencesBean getPreferences() {
        return preferences;
    }

    public void setPreferences(PreferencesBean preferences) {
        this.preferences = preferences;
    }


    public List<String> getContent_filter() {
        return content_filter;
    }

    public void setContent_filter(List<String> content_filter) {
        this.content_filter = content_filter;
    }

    public String getResource_key() {
        return resource_key;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }

    public List<?> getWebsites() {
        return websites;
    }

    public void setWebsites(List<?> websites) {
        this.websites = websites;
    }

    public static class PicturesBean {

        private String uri;
        private boolean active;
        private String type;
        private String resource_key;
        private List<SizesBean> sizes;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getResource_key() {
            return resource_key;
        }

        public void setResource_key(String resource_key) {
            this.resource_key = resource_key;
        }

        public List<SizesBean> getSizes() {
            return sizes;
        }

        public void setSizes(List<SizesBean> sizes) {
            this.sizes = sizes;
        }

        public static class SizesBean {


            private int width;
            private int height;
            private String link;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
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

            private AlbumsBean albums;
            private LikesBean likes;
            private WatchlaterBean watchlater;
            private ChannelsBean channels;
            private GroupsBean groups;
            private FollowingBean following;
            private FollowersBean followers;
            private WatchedVideosBean watched_videos;
            private AppearancesBean appearances;
            private FeedBean feed;
            private ModeratedChannelsBean moderated_channels;
            private PortfoliosBean portfolios;
            private SharedBean shared;
            private PicturesBean pictures;

            public void setAlbums(AlbumsBean albums) {
                this.albums = albums;
            }

            public void setLikes(LikesBean likes) {
                this.likes = likes;
            }

            public void setWatchlater(WatchlaterBean watchlater) {
                this.watchlater = watchlater;
            }

            public AlbumsBean getAlbums() {
                return albums;
            }

            public LikesBean getLikes() {
                return likes;
            }

            public WatchlaterBean getWatchlater() {
                return watchlater;
            }

            public void setChannels(ChannelsBean channels) {
                this.channels = channels;
            }

            public ChannelsBean getChannels() {
                return channels;
            }

            public GroupsBean getGroups() {
                return groups;
            }

            public void setGroups(GroupsBean groups) {
                this.groups = groups;
            }

            public void setFollowing(FollowingBean following) {
                this.following = following;
            }

            public FollowingBean getFollowing() {
                return following;
            }

            public void setFollowers(FollowersBean followers) {
                this.followers = followers;
            }

            public void setWatched_videos(WatchedVideosBean watched_videos) {
                this.watched_videos = watched_videos;
            }

            public FollowersBean getFollowers() {
                return followers;
            }

            public WatchedVideosBean getWatched_videos() {
                return watched_videos;
            }

            public void setAppearances(AppearancesBean appearances) {
                this.appearances = appearances;
            }

            public void setFeed(FeedBean feed) {
                this.feed = feed;
            }

            public AppearancesBean getAppearances() {
                return appearances;
            }

            public FeedBean getFeed() {
                return feed;
            }

            public void setModerated_channels(ModeratedChannelsBean moderated_channels) {
                this.moderated_channels = moderated_channels;
            }

            public void setPortfolios(PortfoliosBean portfolios) {
                this.portfolios = portfolios;
            }

            public ModeratedChannelsBean getModerated_channels() {
                return moderated_channels;
            }

            public PortfoliosBean getPortfolios() {
                return portfolios;
            }

            public void setShared(SharedBean shared) {
                this.shared = shared;
            }

            public void setPictures(PicturesBean pictures) {
                this.pictures = pictures;
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

            public static class WatchlaterBean {

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

            public static class WatchedVideosBean {

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

    public static class PreferencesBean{

        private VideosXBean videosxBean;

        public VideosXBean getVideosxBean() {
            return videosxBean;
        }

        public void setVideosxBean(VideosXBean videosxBean) {
            this.videosxBean = videosxBean;
        }

        public static class VideosXBean {

            private PrivacyBean privacy;

            public void setPrivacy(PrivacyBean privacy) {
                this.privacy = privacy;
            }

            public PrivacyBean getPrivacy() {
                return privacy;
            }

            public static class PrivacyBean {

                private String view;
                private String comments;
                private String embed;
                private boolean download;
                private boolean add;

                public void setView(String view) {
                    this.view = view;
                }

                public void setComments(String comments) {
                    this.comments = comments;
                }

                public void setEmbed(String embed) {
                    this.embed = embed;
                }

                public void setDownload(boolean download) {
                    this.download = download;
                }

                public void setAdd(boolean add) {
                    this.add = add;
                }

                public String getView() {
                    return view;
                }

                public String getComments() {
                    return comments;
                }

                public String getEmbed() {
                    return embed;
                }

                public boolean getDownload() {
                    return download;
                }

                public boolean getAdd() {
                    return add;
                }
            }
        }
    }

}
