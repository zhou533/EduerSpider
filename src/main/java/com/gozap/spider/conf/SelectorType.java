package com.gozap.spider.conf;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-7-2
 * Time: AM10:02
 * To change this template use File | Settings | File Templates.
 */
public enum SelectorType {
    SELECTOR_TYPE_NONE(""),
    SELECTOR_TYPE_NAME("name"),
    SELECTOR_TYPE_EMAIL("email");

    private String value;

    private SelectorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SelectorType getType(String type){
        if (type.equalsIgnoreCase("name")){
            return SELECTOR_TYPE_NAME;
        }else if (type.equalsIgnoreCase("email")){
            return SELECTOR_TYPE_EMAIL;
        }
        return SELECTOR_TYPE_NONE;
    }
}
