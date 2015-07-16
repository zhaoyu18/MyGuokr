package com.example.wangzhaoyu.myguokr.model.response;

/**
 * @author wangzhaoyu
 */
public class ArticleSendComment {

    /**
     * result : <ArticleReply>
     * ok : true
     * offset : 0
     */
    private ArticleReply result;
    private boolean ok;
    private int offset;

    public void setResult(ArticleReply result) {
        this.result = result;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public ArticleReply getResult() {
        return result;
    }

    public boolean isOk() {
        return ok;
    }

    public int getOffset() {
        return offset;
    }
}
