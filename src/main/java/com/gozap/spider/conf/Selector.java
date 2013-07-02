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
    private SelectorType type;
    private String query;

    public Selector(String name,
                    String type,
                    String query) {
        this.name = name;
        this.type = SelectorType.getType(type);
        this.query = query;
    }


    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public SelectorType getType() {
        return type;
    }
}
