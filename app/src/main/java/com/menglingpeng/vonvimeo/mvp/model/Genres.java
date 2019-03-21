package com.menglingpeng.vonvimeo.mvp.model;


import java.util.List;

public class Genres {

    private int total;
    private List<Genre> genres;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public List<Genre> getOndemandGenres() {
        return genres;
    }

    public void setOndemandGenres(List<OnDemandGenre> ondemandGenres) {
        this.genres = genres;
    }
}
