package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class LikeVideos {

    private int total;
    private int page;
    private int per_page;
    private List<LikeVideo> likeVideos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public List<LikeVideo> getLikeVideos() {
        return likeVideos;
    }

    public void setLikeVideos(List<LikeVideo> likeVideos) {
        this.likeVideos = likeVideos;
    }
}
