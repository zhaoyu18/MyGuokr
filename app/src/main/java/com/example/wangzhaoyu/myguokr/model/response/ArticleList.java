package com.example.wangzhaoyu.myguokr.model.response;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class ArticleList {
    private boolean ok;
    private int limit;
    private int offset;
    private int total;
    private ArrayList<ArticleSnapShot> result;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ArticleSnapShot> getResult() {
        return result;
    }

    public void setResult(ArrayList<ArticleSnapShot> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ArticleList{" +
                "ok=" + ok +
                ", limit=" + limit +
                ", offset=" + offset +
                ", total=" + total +
                ", result=" + result +
                '}';
    }
}
