package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Portfolio implements Serializable {

    private String uri;
    private String name;
    private Object description;
    private String link;
    private String created_time;
    private String modified_time;
    private String sort;
    private MetadataBean metadata;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(Object description) {
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

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public Object getDescription() {
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

    public String getSort() {
        return sort;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }


}
