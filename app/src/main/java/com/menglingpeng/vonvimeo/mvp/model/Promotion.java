package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Promotion implements Serializable {

    private String access_type;
    private String discount_type;
    private String download;
    private String label;
    private MetadataBean metadata;
    private int percent_off;
    private String product_type;
    private String stream_period;
    private int total;
    private String type;
    private String uri;

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public void setPercent_off(int percent_off) {
        this.percent_off = percent_off;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setStream_period(String stream_period) {
        this.stream_period = stream_period;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAccess_type() {
        return access_type;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public String getDownload() {
        return download;
    }

    public String getLabel() {
        return label;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public int getPercent_off() {
        return percent_off;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getStream_period() {
        return stream_period;
    }

    public int getTotal() {
        return total;
    }

    public String getType() {
        return type;
    }

    public String getUri() {
        return uri;
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

            private CodesBean codes;

            public void setCodes(CodesBean codes) {
                this.codes = codes;
            }

            public CodesBean getCodes() {
                return codes;
            }

            public static class CodesBean {

                private int total;
                private String uri;
                private List<String> options;

                public void setTotal(int total) {
                    this.total = total;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public void setOptions(List<String> options) {
                    this.options = options;
                }

                public int getTotal() {
                    return total;
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
