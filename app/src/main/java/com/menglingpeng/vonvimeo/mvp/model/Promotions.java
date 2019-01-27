package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class Promotions {

    private int total;
    private int page;
    private int per_page;
    private PagingBean paging;
    private List<Promotion> promotions;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public int getTotal() {
        return total;
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

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public static class PagingBean {

        private Object next;
        private Object previous;
        private String first;
        private String last;

        public void setNext(Object next) {
            this.next = next;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public Object getNext() {
            return next;
        }

        public Object getPrevious() {
            return previous;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }
}
