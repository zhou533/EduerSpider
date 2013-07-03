package com.gozap.spider.parser;

import com.gozap.spider.conf.Configuration;
import com.gozap.spider.conf.Selector;
import com.gozap.spider.conf.SelectorType;
import com.gozap.spider.model.CrawlItem;
import com.gozap.spider.model.impl.Eduer;
import com.gozap.spider.utils.Common;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            List<String> querys = selector.getQuerys();
            Iterator<String> querysIter = querys.iterator();
            boolean found = false;
            while (querysIter.hasNext()){
                String query = querysIter.next();

                LOGGER.info("Current selector:"+selector.getName() + " query:" + query);
                Elements elements = document.select(query);
                if (null != elements && elements.size() > 0){
                    Element element = elements.get(0);
                    LOGGER.info("Ele:"+element.toString());
                    String value = element.ownText();
                    if (selector.getType() == SelectorType.SELECTOR_TYPE_NAME){
                        value = getMatcher("[:\\s]*[\\u4e00-\\u9fa5]{2,4}[;\\s]*", value);
                        value = StringUtils.remove(value,':');
                        value = StringUtils.remove(value,';');
                        value = StringUtils.deleteWhitespace(value);

                        if (value.isEmpty()){
                            value = element.ownText();
                        }
                    }else if (selector.getType() == SelectorType.SELECTOR_TYPE_EMAIL){
                        value = getMatcher("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}",value);

                    }
                    eduer.addValue(selector.getName(), value);
                    found = true;
                    break;
                }else {
                    //
                    continue;
                }
            }

            if (!found){
                eduer.addValue(selector.getName(), "");
            }

        }


        try {
            LOGGER.info(eduer);
            Common.ES_SAVE_QUEUE.put(eduer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String getMatcher(String regex, String source){
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source.toLowerCase());
        while (matcher.find()) {
            result = matcher.group();//只取第一组
        }
        return result;
    }
}
