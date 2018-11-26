package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class Regions {


    private int total;
    private List<Region> regions;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
