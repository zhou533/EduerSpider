package com.gozap.spider;

import com.gozap.spider.conf.Configuration;
import com.gozap.spider.model.CrawlItem;
import com.gozap.spider.thread.ParseThread;
import com.gozap.spider.thread.StoreThread;
import com.gozap.spider.utils.Common;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-5-31
 * Time: AM11:11
 * To change this template use File | Settings | File Templates.
 */
public class EduerSpider {

    private static final Logger LOGGER = Logger.getLogger(EduerSpider.class);

    public static void main(String[] args){
        if (args == null || args.length == 0){
            LOGGER.info("Please indicate configuration file.");
            return;
        }

        String cfgFilename = args[0];
        if (cfgFilename == null || !Configuration.hasValidExt(cfgFilename)){
            LOGGER.info("Invalid configuration file, please check it out.");
            return;
        }

        LOGGER.info("Loading configuration file: " + cfgFilename);

        Configuration configuration = Configuration.getInstance();
        if (!configuration.load(cfgFilename)){
            LOGGER.info("Load configuration file failed!! Please check it out.");
            return;
        }

        //init save thread
        Thread saveThread = new Thread(new StoreThread());
        saveThread.start();
        //init crawl threads
        for (int i = 0; i < configuration.getThreadCount(); i++){
            Thread workThread = new Thread(new ParseThread());
            workThread.start();
        }

        //prepare all urls which need to crawl
        int urlCount = configuration.getArguments().totalCount();
        for (int i = 0; i < urlCount; i++){
            List<String> argList = configuration.getArguments().argListAtIndex(i);
            String url = configuration.getUrl();

            StringBuilder crawlUrl = new StringBuilder();
            int idx = 0, loc = 0, argIdx = 0;
            while (idx < url.length() && (loc = url.indexOf("%s",idx)) >= 0){
                if (loc > idx)
                    crawlUrl.append(url.substring(idx,loc));
                crawlUrl.append(argList.get(argIdx));
                argIdx++;
                idx = loc;
                idx += "%s".length();
            }
            if (idx < url.length()){
                crawlUrl.append(url.substring(idx, url.length()));
            }

            //
            CrawlItem item = new CrawlItem(crawlUrl.toString(), String.valueOf(i));
            try {
                Common.ES_WORK_QUEUE.put(item);
            }catch (InterruptedException e){
                e.printStackTrace();;
            }

        }

        //
        Common.URL_INPUT_END = true;
    }
}
