package com.menglingpeng.vonvimeo.mvp.model;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable{

        public static class EmbedBean {

            private Object html;

            public Object getHtml() {
                return html;
            }

            public void setHtml(Object html) {
                this.html = html;
            }
        }
    }
}
