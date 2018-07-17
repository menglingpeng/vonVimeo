package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;

public class User implements Serializable {


    /**
     * uri : /users/86476471
     * name : fish meng
     * link : https://vimeo.com/user86476471
     * location : null
     * bio : null
     * created_time : 2018-06-21T14:25:17+00:00
     * pictures : {"uri":"/users/86476471/pictures/26035821","active":true,"type":"custom","sizes":[{"width":30,
     * "height":30,"link":"https://i.vimeocdn.com/portrait/26035821_30x30"},{"width":75,"height":75,"link":"https://i
     * .vimeocdn.com/portrait/26035821_75x75"},{"width":100,"height":100,"link":"https://i.vimeocdn
     * .com/portrait/26035821_100x100"},{"width":300,"height":300,"link":"https://i.vimeocdn
     * .com/portrait/26035821_300x300"},{"width":72,"height":72,"link":"https://i.vimeocdn
     * .com/portrait/26035821_72x72"},{"width":144,"height":144,"link":"https://i.vimeocdn
     * .com/portrait/26035821_144x144"},{"width":216,"height":216,"link":"https://i.vimeocdn
     * .com/portrait/26035821_216x216"},{"width":288,"height":288,"link":"https://i.vimeocdn
     * .com/portrait/26035821_288x288"},{"width":360,"height":360,"link":"https://i.vimeocdn
     * .com/portrait/26035821_360x360"}],"resource_key":"9e260756960d5a117f9b2aa4fd155c5e584f7dc7"}
     * websites : []
     * metadata : {"connections":{"albums":{"uri":"/users/86476471/albums","options":["GET"],"total":1},
     * "appearances":{"uri":"/users/86476471/appearances","options":["GET"],"total":0},
     * "categories":{"uri":"/users/86476471/categories","options":["GET"],"total":0},
     * "channels":{"uri":"/users/86476471/channels","options":["GET"],"total":0},
     * "feed":{"uri":"/users/86476471/feed","options":["GET"]},"followers":{"uri":"/users/86476471/followers",
     * "options":["GET"],"total":0},"following":{"uri":"/users/86476471/following","options":["GET"],"total":0},
     * "groups":{"uri":"/users/86476471/groups","options":["GET"],"total":0},"likes":{"uri":"/users/86476471/likes",
     * "options":["GET"],"total":1},"moderated_channels":{"uri":"/users/86476471/channels?filter=moderated",
     * "options":["GET"],"total":0},"portfolios":{"uri":"/users/86476471/portfolios","options":["GET"],"total":0},
     * "videos":{"uri":"/users/86476471/videos","options":["GET"],"total":0},
     * "watchlater":{"uri":"/users/86476471/watchlater","options":["GET"],"total":0},
     * "shared":{"uri":"/users/86476471/shared/videos","options":["GET"],"total":0},
     * "pictures":{"uri":"/users/86476471/pictures","options":["GET","POST"],"total":1},
     * "watched_videos":{"uri":"/me/watched/videos","options":["GET"],"total":0},"block":{"uri":"/me/block",
     * "options":["GET"],"total":0}}}
     * preferences : {"videos":{"privacy":{"view":"anybody","comments":"anybody","embed":"public","download":true,
     * "add":true}}}
     * content_filter : ["language","drugs","violence","nudity","safe","unrated"]
     * resource_key : cfcecff7d315744ab7be7f502cd5c03db15e27bb
     * account : basic
     */

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
    private java.util.List<?> websites;
    private java.util.List<String> content_filter;

    public static class PicturesBean {

        private String uri;
        private boolean active;
        private String type;
        private String resource_key;
        private java.util.List<SizesBean> sizes;

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
}
