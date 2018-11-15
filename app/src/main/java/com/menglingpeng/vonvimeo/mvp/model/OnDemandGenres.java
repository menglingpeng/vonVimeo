package com.menglingpeng.vonvimeo.mvp.model;


import java.util.List;

public class OnDemandGenres {

    private int total;
    private List<OnDemandGenre> ondemandGenres;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public List<OnDemandGenre> getOndemandGenres() {
        return ondemandGenres;
    }

    public void setOndemandGenres(List<OnDemandGenre> ondemandGenres) {
        this.ondemandGenres = ondemandGenres;
    }
}
