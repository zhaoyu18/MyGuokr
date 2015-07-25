package com.example.wangzhaoyu.myguokr.model.response;

/**
 * @author wangzhaoyu
 */
public class NotificationCount {

    /**
     * result : {"r":0,"n":1}
     * now : 2015-07-25T17:26:53.623038+08:00
     * ok : true
     */
    private ResultEntity result;
    private String now;
    private boolean ok;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getNow() {
        return now;
    }

    public boolean isOk() {
        return ok;
    }

    public static class ResultEntity {
        /**
         * r : 0
         * n : 1
         */
        private int r;
        private int n;

        public void setR(int r) {
            this.r = r;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getR() {
            return r;
        }

        public int getN() {
            return n;
        }
    }
}
