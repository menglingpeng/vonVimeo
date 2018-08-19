package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable{

    private String uri;
    private String name;
    private String description;
    private String link;
    private String created_time;
    private String modified_time;
    private PrivacyBean privacy;
    private PicturesBean pictures;
    private HeaderBean header;
    private MetadataBean metadata;
    private User user;
    private String resource_key;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }

    public void setPictures(PicturesBean pictures) {
        this.pictures = pictures;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getModified_time() {
        return modified_time;
    }

    public PrivacyBean getPrivacy() {
        return privacy;
    }

    public PicturesBean getPictures() {
        return pictures;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public User getUser() {
        return user;
    }

    public String getResource_key() {
        return resource_key;
    }

    public static class PrivacyBean {

        private String view;
        private String join;
        private String videos;
        private String comment;
        private String invite;

        public void setView(String view) {
            this.view = view;
        }

        public void setJoin(String join) {
            this.join = join;
        }

        public void setVideos(String videos) {
            this.videos = videos;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setInvite(String invite) {
            this.invite = invite;
        }

        public String getView() {
            return view;
        }

        public String getJoin() {
            return join;
        }

        public String getVideos() {
            return videos;
        }

        public String getComment() {
            return comment;
        }

        public String getInvite() {
            return invite;
        }
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

            private UsersBean users;
            private VideosBean videos;

            public void setUsers(UsersBean users) {
                this.users = users;
            }

            public void setVideos(VideosBean videos) {
                this.videos = videos;
            }

            public UsersBean getUsers() {
                return users;
            }

            public VideosBean getVideos() {
                return videos;
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

            private JoinBean join;

            public void setJoin(JoinBean join) {
                this.join = join;
            }

            public JoinBean getJoin() {
                return join;
            }

            public static class JoinBean {

                private boolean added;
                private String added_time;
                private String type;
                private Object title;
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

                public void setTitle(Object title) {
                    this.title = title;
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

                public Object getTitle() {
                    return title;
                }

                public String getUri() {
                    return uri;
                }
            }
        }
    }
}
