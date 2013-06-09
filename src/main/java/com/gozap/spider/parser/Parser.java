package com.gozap.spider.parser;

import com.gozap.spider.conf.Configuration;
import com.gozap.spider.conf.Selector;
import com.gozap.spider.model.CrawlItem;
import com.gozap.spider.model.impl.Eduer;
import com.gozap.spider.utils.Common;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-7
 * Time: AM10:50
 * To change this template use File | Settings | File Templates.
 */
public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Parser.class);

    public Parser() {
    }

    public void parse(Document document, CrawlItem crawlItem){
        Eduer eduer = new Eduer();
        List<Selector> selectors = Configuration.getInstance().getSelectors();
        Iterator<Selector> iterator = selectors.iterator();
        while (iterator.hasNext()){
            Selector selector = iterator.next();
            LOGGER.info("Current selector:"+selector.getName() + " query:" + selector.getQuery());
            Elements elements = document.select(selector.getQuery());
            for (int i = 0; i < elements.size(); i++){
                Element element = elements.get(i);
                LOGGER.info("Ele "+i +" :"+element.toString());
                eduer.addValue(selector.getName(), element.ownText());
            }
        }

        try {
            LOGGER.info(eduer);
            Common.ES_SAVE_QUEUE.put(eduer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
