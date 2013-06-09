package com.gozap.spider.utils;

import com.gozap.spider.model.CrawlItem;
import com.gozap.spider.model.Saveable;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-6
 * Time: PM3:23
 * To change this template use File | Settings | File Templates.
 */
public class Common {

    public static final BlockingDeque<Saveable> ES_SAVE_QUEUE = new LinkedBlockingDeque<Saveable>(100);
    public static final BlockingDeque<CrawlItem> ES_WORK_QUEUE = new LinkedBlockingDeque<CrawlItem>(100);

    public static volatile boolean URL_INPUT_END = false;
    public static volatile boolean URL_PARSE_END = false;
}
