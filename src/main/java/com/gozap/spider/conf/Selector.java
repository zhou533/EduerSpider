package com.gozap.spider.conf;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> querys = new ArrayList<String>();

    public Selector(String name,
                    String type) {
        this.name = name;
        this.type = SelectorType.getType(type);
    }


    public String getName() {
        return name;
    }

    public List<String> getQuerys() {
        return querys;
    }

    public SelectorType getType() {
        return type;
    }

    public void addQuery(String query){
        querys.add(query);
    }
}
