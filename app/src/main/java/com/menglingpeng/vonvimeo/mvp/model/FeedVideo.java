package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class FeedVideo implements Serializable {

    private Object uri;
    private ClipBean clip;

    public void setUri(Object uri) {
        this.uri = uri;
    }

    public void setClip(ClipBean clip) {
        this.clip = clip;
    }

    public Object getUri() {
        return uri;
    }

    public ClipBean getClip() {
        return clip;
    }


    public static class ClipBean {

        private String uri;
        private String name;
        private String description;
        private String link;
        private int duration;
        private int width;
        private Object language;
        private int height;
        private String created_time;
        private String modified_time;
        private String release_time;
        private List<String> content_rating;
        private Object license;
        private PrivacyBean privacy;
        private PicturesBean pictures;
        private List<TagsBean> tags;
        private User user;
        private MetadataBean metadata;

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

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setLanguage(Object language) {
            this.language = language;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public void setModified_time(String modified_time) {
            this.modified_time = modified_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }

        public void setContent_rating(List<String> content_rating) {
            this.content_rating = content_rating;
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

        public int getDuration() {
            return duration;
        }

        public int getWidth() {
            return width;
        }

        public Object getLanguage() {
            return language;
        }

        public int getHeight() {
            return height;
        }

        public String getCreated_time() {
            return created_time;
        }

        public String getModified_time() {
            return modified_time;
        }

        public String getRelease_time() {
            return release_time;
        }

        public List<String> getContent_rating() {
            return content_rating;
        }

        public void setLicense(Object license) {
            this.license = license;
        }

        public void setPrivacy(PrivacyBean privacy) {
            this.privacy = privacy;
        }

        public Object getLicense() {
            return license;
        }

        public PrivacyBean getPrivacy() {
            return privacy;
        }

        public void setPictures(PicturesBean pictures) {
            this.pictures = pictures;
        }

        public PicturesBean getPictures() {
            return pictures;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }


        public static class PrivacyBean {

            private String view;
            private String embed;
            private boolean download;
            private boolean add;
            private String comments;

            public void setView(String view) {
                this.view = view;
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

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getView() {
                return view;
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

            public String getComments() {
                return comments;
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

            public String getUri() {
                return uri;
            }

            public boolean getActive() {
                return active;
            }

            public String getType() {
                return type;
            }

            public void setResource_key(String resource_key) {
                this.resource_key = resource_key;
            }

            public void setSizes(List<SizesBean> sizes) {
                this.sizes = sizes;
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

        public static class MetadataBean {

            private ConnectionsBean connections;

            public void setConnections(ConnectionsBean connections) {
                this.connections = connections;
            }

            public ConnectionsBean getConnections() {
                return connections;
            }

            public static class ConnectionsBean {

                private CommentsBean comments;
                private LikesBean likes;
                private Object related;
                private PicturesBean pictures;
                private TexttracksBean texttracks;
                private RecommendationsBean recommendations;

                public void setComments(CommentsBean comments) {
                    this.comments = comments;
                }

                public void setLikes(LikesBean likes) {
                    this.likes = likes;
                }

                public void setRelated(Object related) {
                    this.related = related;
                }

                public CommentsBean getComments() {
                    return comments;
                }

                public LikesBean getLikes() {
                    return likes;
                }



                public Object getRelated() {
                    return related;
                }

                public void setPictures(PicturesBean pictures) {
                    this.pictures = pictures;
                }

                public void setTexttracks(TexttracksBean texttracks) {
                    this.texttracks = texttracks;
                }

                public void setRecommendations(RecommendationsBean recommendations) {
                    this.recommendations = recommendations;
                }

                public PicturesBean getPictures() {
                    return pictures;
                }

                public TexttracksBean getTexttracks() {
                    return texttracks;
                }

                public RecommendationsBean getRecommendations() {
                    return recommendations;
                }

                public static class CommentsBean {

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

                public static class TexttracksBean {

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

                public static class RecommendationsBean {

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
}
