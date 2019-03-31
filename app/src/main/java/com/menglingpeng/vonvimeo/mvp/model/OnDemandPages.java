package com.menglingpeng.vonvimeo.mvp.model;

import java.util.List;

public class OnDemandPages {

    private int total;
    private int page;
    private int per_page;
    private List<OnDemandPage> OnDemandPages;
    private PagingBean paging;

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

    public PagingBean getPaging() {
        return paging;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public List<OnDemandPage> getOnDemandPages() {
        return OnDemandPages;
    }

    public void setOnDemandPages(List<OnDemandPage> OnDemandPages) {
        this.OnDemandPages = OnDemandPages;
    }

    public static class PagingBean {

        private Object next;
        private Object previous;
        private String first;
        private String last;

        public Object getNext() {
            return next;
        }

        public void setNext(Object next) {
            this.next = next;
        }

        public Object getPrevious() {
            return previous;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

    }

}
