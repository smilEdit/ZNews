package com.zzz.news.model.bean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 14:02
 */

public class TopNewsBean {


    private int error_code;
    private String reason;

    @Override
    public String toString() {
        return "TopNewsBean{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * stat : 1
     */

    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String stat;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }

        /**
         * author_name : 去啊
         * date : 2016-09-28 06:54
         * realtype : 国际
         * thumbnail_pic_s : http://09.imgmini.eastday.com/mobile/20160928/20160928065413_791873ae104716c455e2d1bf831e5125_1_mwpm_03200403.jpeg
         * thumbnail_pic_s02 : http://09.imgmini.eastday.com/mobile/20160928/20160928065413_791873ae104716c455e2d1bf831e5125_1_mwpl_05500201.jpeg
         * thumbnail_pic_s03 : http://09.imgmini.eastday.com/mobile/20160928/20160928065413_791873ae104716c455e2d1bf831e5125_1_mwpl_05500201.jpeg
         * title : 外国游客素质有多差：硬生生把朱丽叶的胸给摸平了
         * type : 头条
         * uniquekey : 160928065413513
         * url : http://mini.eastday.com/mobile/160928065413513.html?qid=juheshuju
         */

        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String author_name;
            private String date;
            private String realtype;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
            private String title;
            private String type;
            private String uniquekey;
            private String url;

            @Override
            public String toString() {
                return "DataBean{" +
                        "author_name='" + author_name + '\'' +
                        ", date='" + date + '\'' +
                        ", realtype='" + realtype + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        ", title='" + title + '\'' +
                        ", type='" + type + '\'' +
                        ", uniquekey='" + uniquekey + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getRealtype() {
                return realtype;
            }

            public void setRealtype(String realtype) {
                this.realtype = realtype;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
