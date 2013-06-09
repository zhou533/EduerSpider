package com.gozap.spider.model;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-6
 * Time: PM3:28
 * To change this template use File | Settings | File Templates.
 */
public class CrawlItem {
    private String url;
    private String saveId;

    public CrawlItem(String url, String saveId) {
        this.url = url;
        this.saveId = saveId;
    }

    public String getSaveId() {
        return saveId;
    }

    public String getUrl() {
        return url;
    }
}
