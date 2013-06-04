package com.gozap.spider;

import com.gozap.spider.conf.Configuration;
import org.apache.log4j.Logger;

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

        LOGGER.info("Load configuration file: " + cfgFilename);

        Configuration.getInstance().load("src/conf/" + cfgFilename);
    }
}
