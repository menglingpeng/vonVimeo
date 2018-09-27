package com.menglingpeng.vonvimeo.mvp.model;

public class FeedVideos {

    private int page;
    private int per_page;
    private PagingBean paging;
    private FeedVideo feedVideo;

    public void setPage(int page) {
        this.page = page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public PagingBean getPaging() {
        return paging;
    }

    public FeedVideo getFeedVideo() {
        return feedVideo;
    }

    public void setFeedVideo(FeedVideo feedVideo) {
        this.feedVideo = feedVideo;
    }

    public static class PagingBean {

        private String next;
        private Object previous;
        private String first;

        public void setNext(String next) {
            this.next = next;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getNext() {
            return next;
        }

        public Object getPrevious() {
            return previous;
        }

        public String getFirst() {
            return first;
        }
    }
}
