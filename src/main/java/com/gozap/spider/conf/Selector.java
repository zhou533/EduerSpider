package com.gozap.spider.conf;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-7
 * Time: PM4:21
 * To change this template use File | Settings | File Templates.
 */
public class Selector {
    private String name;
    private String query;

    public Selector(String name, String query) {
        this.name = name;
        this.query = query;
    }


    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }
}
