package com.gozap.spider.thread;

import com.gozap.spider.Service.HttpService;
import com.gozap.spider.conf.Configuration;
import com.gozap.spider.model.CrawlItem;
import com.gozap.spider.parser.Parser;
import com.gozap.spider.utils.Common;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-6
 * Time: PM3:18
 * To change this template use File | Settings | File Templates.
 */
public class ParseThread implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ParseThread.class);

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        while (true){
            CrawlItem item = null;
            try {
                LOGGER.debug(Common.ES_WORK_QUEUE.size());
                item = Common.ES_WORK_QUEUE.poll(10, TimeUnit.MINUTES);
            }catch (InterruptedException e){
                LOGGER.error(e);
            }

            if (item == null && Common.URL_INPUT_END){
                LOGGER.info("crawl queue is empty for 10m, so exit");
                Common.URL_PARSE_END = true;
                return;
            }else if (item == null){
                continue;
            }

            byte[] bytes = HttpService.get(item.getUrl());
            if (bytes == null){
                continue;
            }

            if (Configuration.getInstance().isSaveHtml()){
                String fileName = Configuration.getInstance().getTaskName()+"_"+item.getSaveId()+".html";
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(fileName);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();
                }catch (IOException e){
                    LOGGER.error("save file error", e);
                }
            }

            Document document = Jsoup.parse(new String(bytes));
            Parser parser = new Parser();
            parser.parse(document, item);
        }
    }
}
